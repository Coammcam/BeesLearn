package fpl.md07.beeslearn.notifications

import android.content.Context
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TimePreferences(private val context: Context) {
    private val prefs = context.getSharedPreferences("learning_time_prefs", Context.MODE_PRIVATE)

    fun saveSelectedTime(minutes: Int) {
        prefs.edit().putInt("selected_time", minutes).apply()
    }

    fun getSelectedTime(): Int = prefs.getInt("selected_time", 2)

    fun saveDailyLearningTime(timeInMinutes: Long) {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val currentTime = prefs.getLong("daily_time_$currentDate", 0)
        prefs.edit().putLong("daily_time_$currentDate", currentTime + timeInMinutes).apply()
    }

    fun getDailyLearningTime(): Long {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        return prefs.getLong("daily_time_$currentDate", 0)
    }
    fun hasShownTodayAchievement(): Boolean {
        return prefs.getBoolean("shown_today_achievement", false)
    }

    fun setTodayAchievementShown() {
        prefs.edit().putBoolean("shown_today_achievement", true).apply()
    }

    // Reset vào đầu ngày mới
    fun resetDailyStatus() {
        prefs.edit().putBoolean("shown_today_achievement", false).apply()
    }
    fun getLastResetDate(): String {
        return prefs.getString("last_reset_date", "") ?: ""
    }

    fun setLastResetDate(date: String) {
        prefs.edit().putString("last_reset_date", date).apply()
    }
    fun checkAndResetDaily() {
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val lastResetDate = getLastResetDate()

        if (lastResetDate != currentDate) {
            resetDailyStatus()
            setLastResetDate(currentDate)
        }
    }

}