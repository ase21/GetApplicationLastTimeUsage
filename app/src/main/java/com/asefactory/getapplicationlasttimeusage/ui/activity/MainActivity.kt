package com.asefactory.getapplicationlasttimeusage.ui.activity

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageInfo.REQUESTED_PERMISSION_GRANTED
import android.content.pm.PackageManager
import android.content.pm.PermissionInfo.PROTECTION_DANGEROUS
import android.content.pm.PermissionInfo.PROTECTION_SIGNATURE
import android.net.Uri
import android.os.BatteryManager
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asefactory.getapplicationlasttimeusage.ui.adapters.AppListAdapter
import com.asefactory.getapplicationlasttimeusage.R
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        val appList = getApplicationsList().map { packageInfo -> packageInfo.packageName }
        val appUsageStats = getApplicationUsageStats(appList)
        val dangerousPermissionApps = checkIfAppsAreDangerous(getApplicationsList())
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AppListAdapter(appUsageStats, dangerousPermissionApps) { appPackage ->
                openPermissionSettings(
                    appPackage
                )
            }
        }
        checkBattery()
        Log.d("MainActivity",  "onCreate: $appUsageStats")
    }

    private fun openPermissionSettings(appPackageName : String) {
        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
        val uri: Uri = Uri.fromParts("package", appPackageName, null)
        intent.data = uri
        startActivity(intent)
    }

    private fun checkIfAppsAreDangerous(appList: List<PackageInfo>): List<String> {
        val result = mutableListOf<String>()
        appList.forEach { packageInfo ->
            if (packageInfo.requestedPermissions.isNullOrEmpty()){
                return@forEach
            }
            packageInfo.requestedPermissions.forEachIndexed { index, requestedPermission ->
                var protLevel: Int = 0
                protLevel = try {
                    packageManager.getPermissionInfo(requestedPermission, 0).protectionLevel
                } catch (e: PackageManager.NameNotFoundException) {
                    return@forEachIndexed
                }
                val system = protLevel == PROTECTION_SIGNATURE
                val dangerous = protLevel == PROTECTION_DANGEROUS
                val granted = (packageInfo.requestedPermissionsFlags[index]
                        and REQUESTED_PERMISSION_GRANTED) != 0
                if (dangerous and granted){
                    result.add(packageInfo.packageName)
                    return@forEach
                }
            }
        }
        return result
    }

    private fun checkBattery() {
        val batteryManager = getSystemService(BATTERY_SERVICE) as BatteryManager
        batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
    }

    private fun getApplicationsList(): List<PackageInfo> {
        val packages = packageManager
            .getInstalledPackages(PackageManager.GET_PERMISSIONS)
            .filter { packageInfo -> !isSystem(packageInfo) }
        for (app in packages) {
            Log.d("MainActivity", "getApplicationsList: ${app.packageName}")
        }
        return packages
    }

    fun isSystem(info: PackageInfo): Boolean {
        return try {
            info.applicationInfo.flags and (ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0
        } catch (ignore: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun getApplicationUsageStats(appList: List<String>): List<Triple<String, UsageStats, String>> {
        val beginCal = Calendar.getInstance()
        beginCal.add(Calendar.DAY_OF_YEAR, -365)

        val endCal = Calendar.getInstance()
        val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
        val queryUsageStats = usageStatsManager
            .queryAndAggregateUsageStats(
                beginCal.timeInMillis, endCal.timeInMillis
            )
        val resultPair = queryUsageStats
            .filter { usageStats -> appList.any{packageName -> packageName.equals(usageStats.key) } }
            .toList()
            .sortedByDescending { pair -> pair.second.totalTimeInForeground }

        var total = 0L
        val resultTriple = mutableListOf<Triple<String, UsageStats, String>>()
        resultPair.forEach { pair ->
            total = total + pair.second.totalTimeInForeground
        }
        resultPair.forEach { pair ->
            resultTriple.add(Triple(pair.first, pair.second, (pair.second.totalTimeInForeground*100/total).toString()))
        }
        return resultTriple

    }
}