package com.asefactory.getapplicationlasttimeusage.domain.usecases

import android.content.Context
import android.content.pm.PackageInfo
import com.asefactory.getapplicationlasttimeusage.domain.models.ApplicationInfoWithInstallationDate
import com.asefactory.getapplicationlasttimeusage.domain.repositories.AppsRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GetApplicationInstallTimeUseCase(
    private val context: Context,
    private val appsRepository: AppsRepository,
) {
    fun execute(): List<ApplicationInfoWithInstallationDate> {
        return appsRepository.getApplicationsList()
            .sortedByDescending { packageInfo -> packageInfo.firstInstallTime }
            .map { packageInfo ->
            ApplicationInfoWithInstallationDate(
                icon = getApplicationIcon(packageInfo),
                packageName = packageInfo.packageName,
                label = packageInfo.applicationInfo?.loadLabel(context.packageManager).toString(),
                installationDate = convertLongToTime(packageInfo.firstInstallTime)
            )
        }
    }

    fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        return format.format(date)
    }

    private fun getApplicationIcon(info: PackageInfo): String {
        return "android.resource://" + info.packageName + "/" + info.applicationInfo.icon
    }
}