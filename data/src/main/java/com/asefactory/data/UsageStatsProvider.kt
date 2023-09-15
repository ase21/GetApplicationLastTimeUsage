package com.asefactory.data

import android.app.usage.UsageStats
import android.app.usage.UsageStatsManager
import android.content.Context
import java.util.Calendar

class UsageStatsProvider(context: Context) {

    private val usageStatsManager =
        context.getSystemService(Context.USAGE_STATS_SERVICE) as UsageStatsManager

    private fun getApplicationUsageStatsByLastYear(): Map<String, UsageStats> {
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