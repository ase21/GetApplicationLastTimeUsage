package com.asefactory.getapplicationlasttimeusage.domain.models

data class ApplicationInfoWithLastUsageTime(
    val icon: String,
    val packageName: String,
    val label: String,
    val lastUsageTime: Long,
)