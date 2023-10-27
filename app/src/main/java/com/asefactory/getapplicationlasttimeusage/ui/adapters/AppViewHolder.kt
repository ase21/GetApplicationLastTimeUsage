package com.asefactory.getapplicationlasttimeusage.ui.adapters

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.asefactory.getapplicationlasttimeusage.R
import com.asefactory.getapplicationlasttimeusage.ui.model.AppInfoDisplayingDataObject

class AppViewHolder(view : View): RecyclerView.ViewHolder(view) {
    private val imageView: ImageView = itemView.findViewById(R.id.application_icon_image_view)
    private val appName: TextView = itemView.findViewById(R.id.appName)
    private val packageName: TextView = itemView.findViewById(R.id.packageName)
    private val otherInfo: TextView = itemView.findViewById(R.id.otherInfo)

    fun setData(appInfo: AppInfoDisplayingDataObject) {
        imageView.load(appInfo.icon)
        appName.text = appInfo.label
        packageName.text = appInfo.packageName
        otherInfo.text = appInfo.technicalInfo
    }
}