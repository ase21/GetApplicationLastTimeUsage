package com.asefactory.domain.usecases

import com.asefactory.domain.models.ApplicationInfoWithInstallationDate
import com.asefactory.domain.repositories.AppsRepository

class GetApplicationInstallTimeUseCase(
    private val appsRepository: AppsRepository,
) {
    fun execute(): List<ApplicationInfoWithInstallationDate> {
        return appsRepository.getApplicationsList()
            .map { currentInfo ->
                ApplicationInfoWithInstallationDate(
                    icon = currentInfo.icon,
                    packageName = currentInfo.packageName,
                    label = currentInfo.label,
                    installationDate = currentInfo.installationDate
                )
            }
    }
}