package com.asefactory.data

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

class PackagesInformationProvider(context: Context) {

    private val packageManager = context.packageManager

    fun getPackagesInformation(): List<PackageInfo> {
        return packageManager
            .getInstalledPackages(PackageManager.GET_PERMISSIONS).toList()
    }
}