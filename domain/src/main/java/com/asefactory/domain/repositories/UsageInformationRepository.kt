package com.asefactory.domain.repositories

import com.asefactory.domain.models.ApplicationWithLastUsageDate
import com.asefactory.domain.models.ApplicationWithTotalUsageTime

interface UsageInformationRepository {

    fun getAppsListWithLastUsageInformation(): List<ApplicationWithLastUsageDate>

    fun getAppsListWithTotalUsageTime():MutableList<ApplicationWithTotalUsageTime>
}