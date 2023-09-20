package com.asefactory.getapplicationlasttimeusage.ui.fragments.installDate

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asefactory.getapplicationlasttimeusage.R
import com.asefactory.getapplicationlasttimeusage.ui.adapters.AppListAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class InstallationDatesFragment : Fragment(R.layout.fragment_installation_dates) {

    private val viewModel: InstallDatesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.appListSharedFlow.collect { currentList ->
                    val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
                    recyclerView.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = AppListAdapter(currentList)
                    }
                }
            }
        }
        viewModel.getAppList()
    }
}