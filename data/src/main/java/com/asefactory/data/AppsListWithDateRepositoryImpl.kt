package com.asefactory.data

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.asefactory.data.packageInfoProvider.PackagesInformationProvider
import com.asefactory.domain.models.ApplicationInfoWithInstallationDate
import com.asefactory.domain.repositories.AppsRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class AppsListWithDateRepositoryImpl(
    private val context: Context,
    private val packagesInformationProvider: PackagesInformationProvider,
) : AppsRepository {

    override fun getApplicationsList(): List<ApplicationInfoWithInstallationDate> {
        return packagesInformationProvider.getPackagesInformation()
            .filter { packageInfo -> !isSystem(packageInfo) }
            .map { packageInfo ->
                ApplicationInfoWithInstallationDate(
                    icon = getApplicationIcon(packageInfo),
                    installationDateInMillis = packageInfo.firstInstallTime,
                    installationDate = convertLongToTime(packageInfo.firstInstallTime),
                    packageName = packageInfo.packageName,
                    label = packageInfo.applicationInfo.loadLabel(context.packageManager).toString()
                )
            }
    }

    private fun isSystem(info: PackageInfo): Boolean {
        return try {
            info.applicationInfo.flags and (ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0
        } catch (ignore: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        return format.format(date)
    }

    private fun getApplicationIcon(info: PackageInfo): String {
        return "android.resource://" + info.packageName + "/" + info.applicationInfo.icon
    }
}