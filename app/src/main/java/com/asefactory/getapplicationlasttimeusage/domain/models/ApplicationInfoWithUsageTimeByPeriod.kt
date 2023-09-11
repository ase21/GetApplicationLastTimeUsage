package com.asefactory.getapplicationlasttimeusage.domain.models

data class ApplicationInfoWithUsageTimeByPeriod(
    val icon: String,
    val packageName: String,
    val label: String,
    val usageTimeByPeriod: Long,
)