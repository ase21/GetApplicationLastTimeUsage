package com.asefactory.getapplicationlasttimeusage.domain.usecases

import com.asefactory.getapplicationlasttimeusage.domain.models.ApplicationInfoWithUsageTimeByPeriod

class GetApplicationUsageTimeByPeriodUseCase {

    fun execute(period: Int): List<ApplicationInfoWithUsageTimeByPeriod> {
        return emptyList()
    }
}