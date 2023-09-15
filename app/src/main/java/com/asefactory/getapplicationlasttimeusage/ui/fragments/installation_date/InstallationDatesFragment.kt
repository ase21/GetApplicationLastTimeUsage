package com.asefactory.getapplicationlasttimeusage.ui.fragments.installation_date

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asefactory.data.AppsListWithDateRepositoryImpl
import com.asefactory.data.PackagesInformationProvider
import com.asefactory.domain.usecases.GetApplicationInstallTimeUseCase
import com.asefactory.getapplicationlasttimeusage.R
import com.asefactory.getapplicationlasttimeusage.ui.adapters.AppListAdapter

class InstallationDatesFragment : Fragment(R.layout.fragment_installation_dates) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appsRepository = AppsListWithDateRepositoryImpl(
            context = requireContext(),
            packagesInformationProvider = PackagesInformationProvider(requireContext())
        )

        val useCase = GetApplicationInstallTimeUseCase(
            appsRepository
        )

        val appsList = useCase.execute()

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = AppListAdapter(appsList)
        }
    }
}