package com.asefactory.getapplicationlasttimeusage.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.asefactory.domain.models.ApplicationInfoWithInstallationDate
import com.asefactory.getapplicationlasttimeusage.R

class AppListAdapter(
    private val apps: List<ApplicationInfoWithInstallationDate>,
) : RecyclerView.Adapter<AppViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        return AppViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.imageView.load(apps[position].icon)
        holder.appName.text = apps[position].label
        holder.lastUsage.text = apps[position].packageName
        holder.usagePercent.text = apps[position].installationDate
    }

    override fun getItemCount(): Int {
        return apps.size
    }
}