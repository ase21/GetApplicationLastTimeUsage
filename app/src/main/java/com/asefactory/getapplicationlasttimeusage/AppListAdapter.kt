package com.asefactory.getapplicationlasttimeusage

import android.app.usage.UsageStats
import android.content.pm.PackageInfo
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*

class AppListAdapter(
    val apps: List<Triple<String, UsageStats, String>>,
    val dangerousPermissionApps: List<String>,
    val callback: (appPackage: String) -> Unit
): RecyclerView.Adapter<AppViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_app, parent, false)
        return AppViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        holder.appName.text = apps[position].first
        holder.appName.setOnClickListener { callback.invoke(apps[position].first) }
        holder.lastUsage.text = convertLongToTime(apps[position].second.lastTimeVisible)
        holder.usagePercent.text = "${apps[position].third}%"
        if (dangerousPermissionApps.contains(apps[position].second.packageName)){
            holder.isDangerous.visibility = View.VISIBLE
        } else {
            holder.isDangerous.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return apps.size
    }
}

class AppViewHolder(view : View): RecyclerView.ViewHolder(view) {
    val appName: TextView = itemView.findViewById(R.id.appName)
    val lastUsage: TextView = itemView.findViewById(R.id.lastUsage)
    val usagePercent: TextView = itemView.findViewById(R.id.usagePercent)
    val isDangerous: TextView = itemView.findViewById(R.id.isDangerous)
}

fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("yyyy.MM.dd HH:mm")
    return format.format(date)
}