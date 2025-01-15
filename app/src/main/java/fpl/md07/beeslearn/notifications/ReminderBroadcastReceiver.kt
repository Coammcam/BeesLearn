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
        val action = intent?.action
        val timePrefs = TimePreferences(context)

//        if (timePrefs.getDailyLearningTime() > 0L) {
//            return
//        }
//
//        showDailyReminder(context)
        when (action) {
            "ACTION_MANUAL_NOTIFICATION" -> {
                showDailyReminder(context)
            }
            "ACTION_CUSTOM_NOTIFICATION" -> {
                showCustomReminder(context)
            }
            else -> {
                if (timePrefs.getDailyLearningTime() > 0L) {
                    return
                }
                showDailyReminder(context)
            }
        }
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

    private fun showCustomReminder(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            context.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED
        ) {
            Toast.makeText(context, "Vui lòng cấp quyền thông báo!", Toast.LENGTH_SHORT).show()
            return
        }

        val notification = NotificationCompat.Builder(context, "learning_channel")
            .setSmallIcon(R.drawable.beeds)
            .setContentTitle("Thông báo đặc biệt!")
            .setContentText("Hãy vào học để cải thiện kỹ năng!")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify((System.currentTimeMillis() % 10000).toInt(), notification)
    }
}

