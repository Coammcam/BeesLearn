package fpl.md07.beeslearn.notifications

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import java.util.Calendar
import java.util.concurrent.TimeUnit
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.work.*

class LessonViewModels : ViewModel() {
    private var timePreferences: TimePreferences? = null
    private var notificationHelper: NotificationHelper? = null

    fun initialize(context: Context) {
        timePreferences = TimePreferences(context)
        notificationHelper = NotificationHelper(context)
        notificationHelper?.createNotificationChannel()
    }

    fun setSelectedTime(minutes: Int) {
        timePreferences?.saveSelectedTime(minutes)
    }

    fun startLesson(lessonId: String) {
        TimeTrackingManager.startTracking(lessonId)
    }

    fun endLesson(context: Context) {
        val duration = TimeTrackingManager.stopTracking()
        timePreferences?.saveDailyLearningTime(duration)

        val selectedTime = timePreferences?.getSelectedTime()
        var totalTime = timePreferences?.getDailyLearningTime() ?: 0
        // Cộng thời gian học vào tổng thời gian
        totalTime += duration
        timePreferences?.saveDailyLearningTime(totalTime)


        // Show completion status
//        when {
//            totalTime < selectedTime -> {
//
//                Toast.makeText(
//                    context,
//                    "Bạn đã học $duration phút. Hãy cố gắng học đủ thời gian lần tới!",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//
//            else -> {
//
//                Toast.makeText(
//                    context,
//                    "Bạn đã hoàn thành mục tiêu học hôm nay!",
//                    Toast.LENGTH_LONG
//                ).show()
//            }
//        }
    }

    fun pauseLesson() {
        TimeTrackingManager.pauseTracking()
    }

    fun resumeLesson() {
        TimeTrackingManager.resumeTracking()
    }

    // Lấy thời gian đã học từ TimeTrackingManager
    fun getElapsedTime(): Long {
        return TimeTrackingManager.getElapsedTime()
    }

}