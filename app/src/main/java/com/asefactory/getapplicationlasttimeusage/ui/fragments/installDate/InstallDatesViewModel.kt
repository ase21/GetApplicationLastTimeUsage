package com.asefactory.getapplicationlasttimeusage.ui.fragments.installDate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asefactory.domain.models.ApplicationInfoWithInstallationDate
import com.asefactory.domain.usecases.GetApplicationInstallTimeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class InstallDatesViewModel(
    val useCase: GetApplicationInstallTimeUseCase,
) : ViewModel() {

    private val _appListSharedFlow = MutableSharedFlow<List<ApplicationInfoWithInstallationDate>>()
    val appListSharedFlow = _appListSharedFlow.asSharedFlow()

    fun getAppList() {
        viewModelScope.launch(Dispatchers.IO) {
            val value = useCase.execute()
            _appListSharedFlow.emit(value)
        }
    }
}