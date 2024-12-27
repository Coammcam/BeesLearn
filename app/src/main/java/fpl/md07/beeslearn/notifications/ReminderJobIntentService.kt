package fpl.md07.beeslearn.notifications

import android.content.Context
import android.content.Intent
import androidx.core.app.JobIntentService

class ReminderJobIntentService : JobIntentService() {
    override fun onHandleWork(intent: Intent) {
        val reminderUtils = ReminderUtils() // Tạo đối tượng ReminderUtils
        // Gọi các hàm thiết lập nhắc nhở và đặt lại trạng thái học
        reminderUtils.scheduleMultipleDailyRemindersWithMinutes(applicationContext)
        reminderUtils.scheduleResetLearningStatus(applicationContext)
    }
}
