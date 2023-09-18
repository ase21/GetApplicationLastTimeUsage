package com.asefactory.getapplicationlasttimeusage.di

import com.asefactory.data.AppsListWithDateRepositoryImpl
import com.asefactory.data.packageInfoProvider.PackagesInformationProvider
import com.asefactory.data.packageInfoProvider.PackagesInformationProviderImpl
import com.asefactory.domain.repositories.AppsRepository
import org.koin.dsl.module

val dataModule = module {

    single<PackagesInformationProvider> { PackagesInformationProviderImpl(context = get()) }

    factory<AppsRepository> {
        AppsListWithDateRepositoryImpl(
            context = get(),
            packagesInformationProvider = get()
        )
    }
}