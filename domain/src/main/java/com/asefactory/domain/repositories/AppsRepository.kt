package com.asefactory.domain.repositories

import com.asefactory.domain.models.ApplicationInfoWithInstallationDate

interface AppsRepository {
    fun getApplicationsList(): List<ApplicationInfoWithInstallationDate>
}