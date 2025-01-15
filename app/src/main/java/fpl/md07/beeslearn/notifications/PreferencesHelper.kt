package fpl.md07.beeslearn.notifications

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("NotifyPrefs", Context.MODE_PRIVATE)

    fun saveTime(hour: Int, minute: Int) {
        sharedPreferences.edit().apply {
            putInt("HOUR", hour)
            putInt("MINUTE", minute)
            apply()
        }
    }

    fun getTime(): Pair<Int, Int> {
        val hour = sharedPreferences.getInt("HOUR", 10)
        val minute = sharedPreferences.getInt("MINUTE", 0)
        return Pair(hour, minute)
    }
}
