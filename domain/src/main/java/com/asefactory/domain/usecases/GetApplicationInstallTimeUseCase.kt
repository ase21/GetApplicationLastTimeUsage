package com.asefactory.domain.usecases

import com.asefactory.domain.models.ApplicationInfoWithInstallationDate
import com.asefactory.domain.repositories.AppsRepository
import com.asefactory.domain.repositories.UsageInformationRepository

class GetApplicationInstallTimeUseCase(
    private val appsRepository: AppsRepository,
) {
    fun execute(): List<ApplicationInfoWithInstallationDate> {
        return appsRepository.getApplicationsList()
            .sortedByDescending { it.installationDate }
    }
}