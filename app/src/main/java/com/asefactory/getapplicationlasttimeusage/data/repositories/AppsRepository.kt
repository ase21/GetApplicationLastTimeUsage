package com.asefactory.getapplicationlasttimeusage.data.repositories

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

class AppsRepository(private val context: Context) {

    fun getApplicationsList(): List<PackageInfo> {
        return context.packageManager
            .getInstalledPackages(PackageManager.GET_PERMISSIONS)
            .filter { packageInfo -> !isSystem(packageInfo) }
    }

    private fun isSystem(info: PackageInfo): Boolean {
        return try {
            info.applicationInfo.flags and (ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0
        } catch (ignore: PackageManager.NameNotFoundException) {
            false
        }
    }
}