package com.asefactory.getapplicationlasttimeusage.domain.repositories

import android.content.pm.PackageInfo

interface AppsRepository {
    fun getApplicationsList(): Set<PackageInfo>
}