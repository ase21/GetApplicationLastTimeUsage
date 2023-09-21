package com.asefactory.domain.repositories

interface UsageInformationRepository {

    fun getAppsListWithLastUsageInformation()

    fun getAppsListWithTotalUsageTime()
}