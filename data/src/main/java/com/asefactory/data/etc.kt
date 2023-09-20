package com.asefactory.data


//        запуск настроек для доступа
//        startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))

//открывает настройки конкретного приложения
//    private fun openPermissionSettings(appPackageName : String) {
//        val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
//        val uri: Uri = Uri.fromParts("package", appPackageName, null)
//        intent.data = uri
//        startActivity(intent)
//    }

//чекает опасные пермиты, использовалось для пометки прилы как Dangerous
//    private fun checkIfAppsAreDangerous(appList: List<PackageInfo>): List<String> {
//        val result = mutableListOf<String>()
//        appList.forEach { packageInfo ->
//            if (packageInfo.requestedPermissions.isNullOrEmpty()){
//                return@forEach
//            }
//            packageInfo.requestedPermissions.forEachIndexed { index, requestedPermission ->
//                var protLevel: Int = 0
//                protLevel = try {
//                    packageManager.getPermissionInfo(requestedPermission, 0).protectionLevel
//                } catch (e: PackageManager.NameNotFoundException) {
//                    return@forEachIndexed
//                }
//                val system = protLevel == PROTECTION_SIGNATURE
//                val dangerous = protLevel == PROTECTION_DANGEROUS
//                val granted = (packageInfo.requestedPermissionsFlags[index]
//                        and REQUESTED_PERMISSION_GRANTED) != 0
//                if (dangerous and granted){
//                    result.add(packageInfo.packageName)
//                    return@forEach
//                }
//            }
//        }
//        return result
//    }

//ничего не делает
//    private fun checkBattery() {
//        val batteryManager = getSystemService(BATTERY_SERVICE) as BatteryManager
//        batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
//    }



