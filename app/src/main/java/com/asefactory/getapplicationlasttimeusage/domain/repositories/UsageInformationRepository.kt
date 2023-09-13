package com.asefactory.getapplicationlasttimeusage.domain.repositories

import android.app.usage.UsageStats

interface UsageInformationRepository {
    fun getUsageInformationAboutApplications(): Set<UsageStats>
}