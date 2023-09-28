package com.asefactory.data.model

import android.app.usage.UsageStats
import android.content.pm.PackageInfo

data class GeneralAppInfo(
    val packageName: String,
    val packageInfo: PackageInfo,
    val usageStats: UsageStats,
)