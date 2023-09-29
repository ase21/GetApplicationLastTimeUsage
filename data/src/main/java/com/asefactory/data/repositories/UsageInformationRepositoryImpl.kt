package com.asefactory.data.repositories

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import com.asefactory.data.model.GeneralAppInfo
import com.asefactory.data.packageInfoProvider.PackagesInformationProvider
import com.asefactory.data.usageStatsProvider.UsageStatsInformationProvider
import com.asefactory.domain.models.ApplicationWithLastUsageDate
import com.asefactory.domain.models.ApplicationWithTotalUsageTime
import com.asefactory.domain.repositories.UsageInformationRepository
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UsageInformationRepositoryImpl(
    private val context: Context,
    private val packagesInformationProvider: PackagesInformationProvider,
    private val usageStatsInformationProvider: UsageStatsInformationProvider,
) : UsageInformationRepository {
    override fun getAppsListWithLastUsageInformation(): List<ApplicationWithLastUsageDate> {
        val resultList = mutableListOf<ApplicationWithLastUsageDate>()
        resultList.addAll(
            getGeneralInformation()
                .map { generalAppInfo ->
                    ApplicationWithLastUsageDate(
                        icon = getApplicationIcon(generalAppInfo.packageInfo),
                        packageName = generalAppInfo.packageName,
                        label = generalAppInfo.packageInfo.applicationInfo.loadLabel(context.packageManager).toString(),
                        lastUsageDate = convertLongToTime(generalAppInfo.usageStats.lastTimeUsed),
                        lastUsageDateInMillis = generalAppInfo.usageStats.lastTimeUsed
                    )
                }
        )

        return resultList
    }

    override fun getAppsListWithTotalUsageTime(): MutableList<ApplicationWithTotalUsageTime> {
        val resultList = mutableListOf<ApplicationWithTotalUsageTime>()
        resultList.addAll(
            getGeneralInformation()
                .map { generalAppInfo ->
                    ApplicationWithTotalUsageTime(
                        icon = getApplicationIcon(generalAppInfo.packageInfo),
                        packageName = generalAppInfo.packageName,
                        label = generalAppInfo.packageInfo.applicationInfo.loadLabel(context.packageManager).toString(),
                        totalUsageTime = convertLongToTime(generalAppInfo.usageStats.totalTimeInForeground),
                        totalUsageTimeInMillis = generalAppInfo.usageStats.totalTimeInForeground
                    )
                }
        )

        return resultList
    }



    private fun convertLongToTime(time: Long): String {
        val date = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        return format.format(date)
    }

    private fun getGeneralInformation(): List<GeneralAppInfo> {
        val resultList = mutableListOf<GeneralAppInfo>()
        val packages = packagesInformationProvider.getPackagesInformation().filter { !isSystem(it) }
        val usageStats =
            usageStatsInformationProvider.getApplicationUsageStatsByLastYear()
        usageStats.forEach { (currentPackageName, currentUsageStats) ->
            packages.find { it.packageName == currentPackageName }?.let { currentPackageInfo ->
                resultList.add(
                    GeneralAppInfo(
                        packageName = currentPackageName,
                        packageInfo = currentPackageInfo,
                        usageStats = currentUsageStats
                    )
                )
            }
        }
        return resultList
    }

    private fun isSystem(info: PackageInfo): Boolean {
        return try {
            info.applicationInfo.flags and (ApplicationInfo.FLAG_SYSTEM or ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0
        } catch (ignore: PackageManager.NameNotFoundException) {
            false
        }
    }

    private fun getApplicationIcon(info: PackageInfo): String {
        return "android.resource://" + info.packageName + "/" + info.applicationInfo.icon
    }
}