package fpl.md07.beeslearn.notifications

import android.app.Service
import android.content.Intent
import android.os.IBinder

class ReminderService : Service() {
    private val reminderUtils = ReminderUtils() // Tạo đối tượng của lớp ReminderUtils

    override fun onCreate() {
        super.onCreate()
        reminderUtils.scheduleMultipleDailyRemindersWithMinutes(applicationContext)
        reminderUtils.scheduleResetLearningStatus(applicationContext)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // Khi service bắt đầu, bạn có thể xử lý thêm nếu cần
        return START_STICKY // Cho phép service tiếp tục chạy nếu bị hủy
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null // Không cần bind cho mục đích này
    }
}
