package fpl.md07.beeslearn.notifications

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import fpl.md07.beeslearn.R

class ReminderBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val timePrefs = TimePreferences(context)

        // Kiểm tra nếu người dùng đã học trong ngày
        if (timePrefs.getDailyLearningTime() > 0L) {
            // Người dùng đã học, không gửi thông báo
            return
        }

        // Người dùng chưa học, gửi thông báo
        showDailyReminder(context)
    }

    private fun showDailyReminder(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "Vui lòng cấp quyền thông báo!", Toast.LENGTH_SHORT).show()
            return
        }

        val notification = NotificationCompat.Builder(context, "learning_channel")
            .setSmallIcon(R.drawable.beeds)
            .setContentTitle("Thông điệp hôm nay!")
          //  .setContentText("Mở app ra và học ngay thằng l")
            .setContentText("Bạn chưa học hôm nay! Đừng quên tiếp tục học để cải thiện tiếng Anh.")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify((System.currentTimeMillis() % 10000).toInt(), notification)
    }
}
