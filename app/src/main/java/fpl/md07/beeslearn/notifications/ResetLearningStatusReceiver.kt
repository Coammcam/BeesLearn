package fpl.md07.beeslearn.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ResetLearningStatusReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val timePrefs = TimePreferences(context)
        timePrefs.saveDailyLearningTime(0L) // Đặt lại thời gian học về 0
    }
}
