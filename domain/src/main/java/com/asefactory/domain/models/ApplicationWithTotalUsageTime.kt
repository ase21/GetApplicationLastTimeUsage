package com.asefactory.domain.models

data class ApplicationWithTotalUsageTime(
    val icon: String,
    val packageName: String,
    val label: String,
    val totalUsageTimeInMillis: Long,
    val totalUsageTime: String,
)