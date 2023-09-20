package com.asefactory.data.usageStatsProvider

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import java.util.Calendar

class UsageStatsInformationProviderImplementation(context: Context) : UsageStatsInformationProvider{

    private val usageStatsManager =
        context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

    override fun getApplicationUsageStatsByLastYear(): Map<String, UsageStats> {
        val beginCal = Calendar.getInstance()
        beginCal.add(Calendar.DAY_OF_YEAR, -365)

        val endCal = Calendar.getInstance()

        return usageStatsManager
            .queryAndAggregateUsageStats(
                beginCal.timeInMillis, endCal.timeInMillis
            )
            .toMap()
    }
}