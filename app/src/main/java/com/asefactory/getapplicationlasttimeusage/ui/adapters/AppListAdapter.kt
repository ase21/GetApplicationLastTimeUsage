package com.asefactory.getapplicationlasttimeusage.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.asefactory.getapplicationlasttimeusage.R
import com.asefactory.getapplicationlasttimeusage.ui.model.AppInfoDisplayingDataObject

class AppListAdapter(
    private val apps: List<AppInfoDisplayingDataObject>,
) : RecyclerView.Adapter<AppViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        return AppViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.setData(apps[position])
    }

    override fun getItemCount(): Int {
        return apps.size
    }
}