package com.asefactory.getapplicationlasttimeusage.di

import com.asefactory.data.repositories.AppsListWithDateRepositoryImpl
import com.asefactory.data.packageInfoProvider.PackagesInformationProvider
import com.asefactory.data.packageInfoProvider.PackagesInformationProviderImplementation
import com.asefactory.data.usageStatsProvider.UsageStatsInformationProvider
import com.asefactory.data.usageStatsProvider.UsageStatsInformationProviderImplementation
import com.asefactory.domain.repositories.AppsRepository
import org.koin.dsl.module

val dataModule = module {

    single<UsageStatsInformationProvider> { UsageStatsInformationProviderImplementation(context = get()) }

    single<PackagesInformationProvider> { PackagesInformationProviderImplementation(context = get()) }

    factory<AppsRepository> {
        AppsListWithDateRepositoryImpl(
            context = get(),
            packagesInformationProvider = get()
        )
    }
}