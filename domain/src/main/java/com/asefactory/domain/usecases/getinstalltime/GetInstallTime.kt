package com.asefactory.domain.usecases.getinstalltime

import com.asefactory.domain.models.ApplicationInfoWithInstallationDate

interface GetInstallTime {
    fun execute(): List<ApplicationInfoWithInstallationDate>
}