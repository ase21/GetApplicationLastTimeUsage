package com.asefactory.domain.models

data class ApplicationWithLastUsageDate(
    val icon: String,
    val packageName: String,
    val label: String,
    val lastUsageDateInMillis: Long,
    val lastUsageDate: String,
)
