package fpl.md07.beeslearn.notifications

object TimeTrackingManager {
    private var startTime: Long = 0
    private var elapsedTime: Long = 0
    private var isTracking = false
    private var currentLessonId: String? = null

    fun startTracking(lessonId: String) {
        if (!isTracking) {
            startTime = System.currentTimeMillis()
            isTracking = true
            currentLessonId = lessonId
        }
    }

    fun pauseTracking() {
        if (isTracking) {
            elapsedTime += System.currentTimeMillis() - startTime
            isTracking = false
        }
    }
/// tiep tục
    fun resumeTracking() {
        if (!isTracking) {
            startTime = System.currentTimeMillis()
            isTracking = true
        }
    }

    // Stop tracking the lesson
    fun stopTracking(): Long {
        if (isTracking) {
            elapsedTime += System.currentTimeMillis() - startTime // Add time until stop
            isTracking = false
        }
        val duration = elapsedTime / 60000 // Convert to minutes
        currentLessonId = null
        return duration
    }
    // Lấy thời gian đã học (dạng phút)
    fun getElapsedTime(): Long {
        return elapsedTime / 60000
    }
}
