package com.asefactory.getapplicationlasttimeusage

import android.app.usage.UsageStatsManager
import android.content.Context
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

//        запуск настроек для доступа
//        startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))

//открывает настройки конкретного приложения
//    private fun openPermissionSettings(appPackageName : String) {
//        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//        val uri: Uri = Uri.fromParts("package", appPackageName, null)
//        intent.data = uri
//        startActivity(intent)
//    }

//чекает опасные пермиты, использовалось для пометки прилы как Dangerous
//    private fun checkIfAppsAreDangerous(appList: List<PackageInfo>): List<String> {
//        val result = mutableListOf<String>()
//        appList.forEach { packageInfo ->
//            if (packageInfo.requestedPermissions.isNullOrEmpty()){
//                return@forEach
//            }
//            packageInfo.requestedPermissions.forEachIndexed { index, requestedPermission ->
//                var protLevel: Int = 0
//                protLevel = try {
//                    packageManager.getPermissionInfo(requestedPermission, 0).protectionLevel
//                } catch (e: PackageManager.NameNotFoundException) {
//                    return@forEachIndexed
//                }
//                val system = protLevel == PROTECTION_SIGNATURE
//                val dangerous = protLevel == PROTECTION_DANGEROUS
//                val granted = (packageInfo.requestedPermissionsFlags[index]
//                        and REQUESTED_PERMISSION_GRANTED) != 0
//                if (dangerous and granted){
//                    result.add(packageInfo.packageName)
//                    return@forEach
//                }
//            }
//        }
//        return result
//    }

//ничего не делает
//    private fun checkBattery() {
//        val batteryManager = getSystemService(BATTERY_SERVICE) as BatteryManager
//        batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
//    }



//private fun getApplicationUsageStats(appList: List<String>): List<Triple<String, String, String>> {
//    val beginCal = Calendar.getInstance()
//    beginCal.add(Calendar.DAY_OF_YEAR, -365)
//
//    val endCal = Calendar.getInstance()
//    val usageStatsManager = getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager
//    val queryUsageStats = usageStatsManager
//        .queryAndAggregateUsageStats(
//            beginCal.timeInMillis, endCal.timeInMillis
//        )
//    val resultPair = queryUsageStats
//        .filter { usageStats -> appList.any { packageName -> packageName.equals(usageStats.key) } }
//        .toList()
//        .sortedByDescending { pair -> pair.second.totalTimeInForeground }
//
//    var total = 0L
//    val resultTriple = mutableListOf<Triple<String, String, String>>()
//    resultPair.forEach { pair ->
//        total = total + pair.second.totalTimeInForeground
//    }
//    resultPair.forEach { pair ->
//        resultTriple.add(
//            Triple(
//                pair.first,
//                convertLongToTime(pair.second.lastTimeVisible),
//                (pair.second.totalTimeInForeground * 100 / total).toString()
//            )
//        )
//    }
//    return resultTriple
//
//}