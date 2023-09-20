package com.asefactory.getapplicationlasttimeusage.ui.activity

import android.Manifest
import android.app.AppOpsManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.asefactory.getapplicationlasttimeusage.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class MainActivity : AppCompatActivity() {

    private val settingsActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            setupMainScreen()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!checkIfPermissionGranted()) {
            showRequestAlertDialog()
        } else {
            setupMainScreen()
        }
    }

    private fun showRequestAlertDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle(resources.getString(R.string.permission_dialog_title))
            .setMessage(resources.getString(R.string.permission_dialog_description))
            .setNeutralButton(resources.getString(R.string.permission_dialog_neutral_button)) { _, _ ->
                setupMainScreen()
            }
            .setPositiveButton(resources.getString(R.string.permission_dialog_positive_button)) { _, _ ->
                openUsagePermissionsScreen()
            }
            .show()
    }

    private fun checkIfPermissionGranted(): Boolean {
        val appOpsManager = getSystemService(Context.APP_OPS_SERVICE) as AppOpsManager
        val mode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            appOpsManager.unsafeCheckOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(),
                packageName
            )
        } else {
            appOpsManager.checkOpNoThrow(
                AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(),
                packageName
            )
        }
        return if (mode == AppOpsManager.MODE_DEFAULT) {
            checkCallingOrSelfPermission(Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED
        } else {
            mode == AppOpsManager.MODE_ALLOWED
        }
    }

    private fun openUsagePermissionsScreen() {
        try {
            val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
            settingsActivityResult.launch(intent)
        } catch (e: Exception) {
            val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
            settingsActivityResult.launch(intent)
        }
    }

    private fun setupMainScreen() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.nav_graph)
        navController.graph = navGraph
        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_nav)
        setupBottomMenu(bottomMenu)
        bottomMenu.setupWithNavController(navController)
        bottomMenu.isVisible = true
    }


    override fun onResume() {
        super.onResume()
        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottom_nav)
        setupBottomMenu(bottomMenu)
    }

    private fun setupBottomMenu(bottomMenu: BottomNavigationView) {
        if (!checkIfPermissionGranted()) {
            bottomMenu.menu.findItem(R.id.lastUsingDateFragment)?.let {
                bottomMenu.menu.removeItem(R.id.lastUsingDateFragment)
            }
            bottomMenu.menu.findItem(R.id.usingTimeFragment)?.let {
                bottomMenu.menu.removeItem(R.id.usingTimeFragment)
            }
        } else {
            bottomMenu.menu.findItem(R.id.lastUsingDateFragment)?.let {} ?: run {
                bottomMenu.menu.add(
                    Menu.NONE,
                    R.id.lastUsingDateFragment,
                    1,
                    resources.getString(R.string.last_using_date_bottom_menu)
                )
            }
            bottomMenu.menu.findItem(R.id.usingTimeFragment)?.let {} ?: run {
                bottomMenu.menu.add(
                    Menu.NONE,
                    R.id.usingTimeFragment,
                    2,
                    resources.getString(R.string.using_time_bottom_menu)
                )
            }
        }
    }
}