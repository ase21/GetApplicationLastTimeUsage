package com.asefactory.getapplicationlasttimeusage.domain.models

data class ApplicationInfoWithInstallationDate(
    val icon: String,
    val packageName: String,
    val label: String,
    val installationDate: Long,
)