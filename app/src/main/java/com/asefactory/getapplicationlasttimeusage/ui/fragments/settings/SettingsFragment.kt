package com.asefactory.getapplicationlasttimeusage.ui.fragments.settings

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.asefactory.getapplicationlasttimeusage.BuildConfig
import com.asefactory.getapplicationlasttimeusage.R

class SettingsFragment : Fragment(R.layout.fragment_settings) {

    private val settingsActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val shareLayout = view.findViewById<View>(R.id.share_layout)
        shareLayout.setOnClickListener {
            shareApp()
        }

        val openPermissionsLayout = view.findViewById<View>(R.id.open_permissions_layout)
        openPermissionsLayout.setOnClickListener {
            openUsagePermissionsScreen()
        }
    }

    private fun shareApp() {
        val shareIntent = Intent()
        shareIntent.action = Intent.ACTION_SEND
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            resources.getString(R.string.app_name) + " https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}"
        )
        shareIntent.type = "text/plain"
        startActivity(Intent.createChooser(shareIntent, resources.getString(R.string.share)))
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
}