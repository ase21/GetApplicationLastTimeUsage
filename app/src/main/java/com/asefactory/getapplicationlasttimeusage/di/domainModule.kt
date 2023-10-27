package com.asefactory.getapplicationlasttimeusage.di

import com.asefactory.domain.usecases.getinstalltime.GetInstallTime
import com.asefactory.domain.usecases.getinstalltime.GetInstallTimeUseCase
import com.asefactory.domain.usecases.lastUsage.LastUsageTime
import com.asefactory.domain.usecases.lastUsage.LastUsageTimeUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetInstallTime> { GetInstallTimeUseCase(appsRepository = get()) }

    factory<LastUsageTime> { LastUsageTimeUseCase(usageRepository = get()) }

}