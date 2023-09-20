package com.asefactory.domain.models

data class ApplicationInfoWithInstallationDate(
    val icon: String,
    val packageName: String,
    val label: String,
    val installationDateInMillis: Long,
    val installationDate: String,
)