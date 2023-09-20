package com.asefactory.data.packageInfoProvider

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager

class PackagesInformationProviderImplementation(context: Context): PackagesInformationProvider {

    private val packageManager = context.packageManager

    override fun getPackagesInformation(): List<PackageInfo> {
        return packageManager
            .getInstalledPackages(PackageManager.GET_PERMISSIONS)
    }
}