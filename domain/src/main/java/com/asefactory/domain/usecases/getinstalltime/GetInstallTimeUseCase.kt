package com.asefactory.domain.usecases.getinstalltime

import com.asefactory.domain.models.ApplicationInfoWithInstallationDate
import com.asefactory.domain.repositories.AppsRepository

class GetInstallTimeUseCase(
    private val appsRepository: AppsRepository,
): GetInstallTime {
    override fun execute(): List<ApplicationInfoWithInstallationDate> {
        return appsRepository.getApplicationsList()
            .sortedByDescending { it.installationDate }
    }
}