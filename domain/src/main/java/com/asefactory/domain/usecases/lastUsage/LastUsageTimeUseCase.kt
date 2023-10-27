package com.asefactory.domain.usecases.lastUsage

import com.asefactory.domain.models.ApplicationWithLastUsageDate
import com.asefactory.domain.repositories.UsageInformationRepository

class LastUsageTimeUseCase(
    private val usageRepository: UsageInformationRepository
):LastUsageTime {
    override fun execute(): List<ApplicationWithLastUsageDate> {
        return usageRepository.getAppsListWithLastUsageInformation().sortedByDescending { it.lastUsageDate }
    }
}