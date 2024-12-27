package fpl.md07.beeslearn.main

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        // Tạo kênh thông báo nếu là Android O trở lên
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = "default_channel"
            val channelName = "Default Notifications"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, channelName, importance)

            // Đăng ký kênh thông báo
            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Tạo một thông báo
        val notification = NotificationCompat.Builder(context, "default_channel")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setContentTitle("Thông báo từ BeesLearn")
            .setContentText("Ứng dụng đã bị đóng 5 giây.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        // Hiển thị thông báo
        val notificationManagerCompat = NotificationManagerCompat.from(context)
        notificationManagerCompat.notify(0, notification)
    }
}
