package com.asefactory.getapplicationlasttimeusage.ui.fragments.lastUsage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asefactory.domain.usecases.lastUsage.LastUsageTime
import com.asefactory.getapplicationlasttimeusage.ui.model.AppInfoDisplayingDataObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class LastUsageViewModel(
    private val useCase: LastUsageTime,
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
                    technicalInfo = appInfo.lastUsageDate,
                )
            }
            _appListSharedFlow.emit(value)
        }
    }
}