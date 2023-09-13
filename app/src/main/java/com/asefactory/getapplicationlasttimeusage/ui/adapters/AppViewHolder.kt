package com.asefactory.getapplicationlasttimeusage.ui.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asefactory.getapplicationlasttimeusage.R

class AppViewHolder(view : View): RecyclerView.ViewHolder(view) {
    val imageView: ImageView = itemView.findViewById(R.id.application_icon_image_view)
    val appName: TextView = itemView.findViewById(R.id.appName)
    val lastUsage: TextView = itemView.findViewById(R.id.lastUsage)
    val usagePercent: TextView = itemView.findViewById(R.id.usagePercent)
}