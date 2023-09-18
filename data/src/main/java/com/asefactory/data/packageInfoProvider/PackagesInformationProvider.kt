package com.asefactory.data.packageInfoProvider

import android.content.pm.PackageInfo

interface PackagesInformationProvider {
    fun getPackagesInformation(): List<PackageInfo>
}