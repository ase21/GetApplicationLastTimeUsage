package com.asefactory.domain.usecases.lastUsage

import com.asefactory.domain.models.ApplicationInfoWithInstallationDate
import com.asefactory.domain.models.ApplicationWithLastUsageDate

interface LastUsageTime {

    fun execute(): List<ApplicationWithLastUsageDate>
}