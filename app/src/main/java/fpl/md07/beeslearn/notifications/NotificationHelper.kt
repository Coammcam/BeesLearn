package fpl.md07.beeslearn.notifications

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import android.widget.Toast
import fpl.md07.beeslearn.R

class NotificationHelper(private val context: Context) {

    fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "learning_channel",
                "Learning Notifications",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager = context.getSystemService(NotificationManager::class.java)
            notificationManager?.createNotificationChannel(channel)
        }
    }

    fun showTimeAchievementNotification() {
        // Kiểm tra quyền trước khi hiển thị
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "Vui lòng cấp quyền thông báo!", Toast.LENGTH_SHORT).show()
            return
        }

        val notification = NotificationCompat.Builder(context, "learning_channel")
            .setSmallIcon(R.drawable.beeds)
            .setContentTitle("Chúc mừng!")
            .setContentText("Bạn đã hoàn thành mục tiêu hôm nay. Hãy tiếp tục cố gắng!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(System.currentTimeMillis().toInt(), notification)
    }
    fun showTimeAchievementNotification1() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "Vui lòng cấp quyền thông báo!", Toast.LENGTH_SHORT).show()
            return
        }

        val notification = NotificationCompat.Builder(context, "learning_channel")
            .setSmallIcon(R.drawable.beeds)
            .setContentTitle("Beeslearn!")
            .setContentText("Hãy tiếp tục hoàn thành các bài học để đạt được mục tiêu bạn nhé!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(System.currentTimeMillis().toInt(), notification)
    }

}
