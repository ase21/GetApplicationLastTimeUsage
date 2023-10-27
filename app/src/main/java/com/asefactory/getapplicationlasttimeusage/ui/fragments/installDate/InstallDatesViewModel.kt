package com.asefactory.getapplicationlasttimeusage.ui.fragments.installDate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asefactory.domain.usecases.getinstalltime.GetInstallTime
import com.asefactory.getapplicationlasttimeusage.ui.model.AppInfoDisplayingDataObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class InstallDatesViewModel(
    private val useCase: GetInstallTime,
) : ViewModel() {

    private val _appListSharedFlow = MutableSharedFlow<List<AppInfoDisplayingDataObject>>()
    val appListSharedFlow = _appListSharedFlow.asSharedFlow()

    fun getAppList() {
        viewModelScope.launch(Dispatchers.IO) {
            val value = useCase.execute().map { appInfo ->
                AppInfoDisplayingDataObject(
                    icon = appInfo.icon,
                    label = appInfo.label,
                    packageName = appInfo.packageName,
                    technicalInfo = appInfo.installationDate,
                )
            }
            _appListSharedFlow.emit(value)
        }
    }
}