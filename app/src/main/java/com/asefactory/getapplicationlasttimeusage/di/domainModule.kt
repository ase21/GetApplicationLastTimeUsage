package com.asefactory.getapplicationlasttimeusage.di

import com.asefactory.domain.usecases.GetApplicationInstallTimeUseCase
import org.koin.dsl.module

val domainModule = module {

    factory { GetApplicationInstallTimeUseCase(appsRepository = get()) }

}