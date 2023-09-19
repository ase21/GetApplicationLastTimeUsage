package com.asefactory.data.usageStatsProvider

import android.app.usage.UsageStats

interface UsageStatsInformationProvider {

    fun getApplicationUsageStatsByLastYear(): Map<String, UsageStats>
}