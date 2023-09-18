package com.asefactory.getapplicationlasttimeusage.di

import com.asefactory.getapplicationlasttimeusage.ui.fragments.installDate.InstallDatesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { InstallDatesViewModel(useCase = get()) }
}