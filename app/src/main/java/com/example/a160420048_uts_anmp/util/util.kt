package com.example.a160420048_uts_anmp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.example.a160420048_uts_anmp.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso


val DB_NAME = "anmp_160420048_uts"


fun ImageView.loadImage(url: String?, progressBar:ProgressBar){
    Picasso.get()
        .load(url)
        .resize(400, 400)
        .centerCrop()
        .error(R.drawable.baseline_error_24)
        .into(this, object: Callback {
            override fun onSuccess() {
                progressBar.visibility = View.GONE
            }
            override fun onError(e: Exception?) {
            }
        })
}

@BindingAdapter("android:imageUrl","android:progressBar")
fun loadPhotoUrl(view: ImageView, url: String, pb:ProgressBar){
    view.loadImage(url,pb)
}
fun createNotificationChannel(context: Context, importance: Int, showBadge: Boolean, name: String, description: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channelId = "${context.packageName}-$name"
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description
        channel.setShowBadge(showBadge)

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.createNotificationChannel(channel)
    }
}
