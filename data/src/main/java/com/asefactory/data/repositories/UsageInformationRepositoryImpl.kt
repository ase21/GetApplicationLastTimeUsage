package com.asefactory.data.repositories

import com.asefactory.data.packageInfoProvider.PackagesInformationProvider
import com.asefactory.data.usageStatsProvider.UsageStatsInformationProvider
import com.asefactory.domain.repositories.UsageInformationRepository

class UsageInformationRepositoryImpl(
    private val packagesInformationProvider: PackagesInformationProvider,
    private val usageStatsInformationProvider: UsageStatsInformationProvider
) : UsageInformationRepository {
    override fun getAppsListWithLastUsageInformation() {
        TODO("Not yet implemented")
    }

    override fun getAppsListWithTotalUsageTime() {
        TODO("Not yet implemented")
    }
}