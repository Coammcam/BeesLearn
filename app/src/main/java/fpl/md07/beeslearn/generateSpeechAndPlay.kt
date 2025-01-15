package fpl.md07.beeslearn

import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioTrack
import android.media.MediaPlayer
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream

fun generateSpeechAndPlay(apiKey: String, text: String, cacheDir: File) {
    val client = OkHttpClient()
    val url = "https://api.openai.com/v1/audio/speech"

    // Tạo nội dung request
    val requestBody = JSONObject().apply {
        put("input", text)
        put("voice", "nova")
        put("model", "tts-1")
    }.toString().toRequestBody("application/json".toMediaType())

    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .addHeader("Authorization", "Bearer $apiKey")
        .build()

    // Gửi yêu cầu API và xử lý phản hồi
    CoroutineScope(Dispatchers.IO).launch {
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("TTS", "API call failed: ${e.message}")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    response.body?.byteStream()?.let { audioStream ->
                        val audioFile = File(cacheDir, "temp_audio.mp3")
                        saveAudioToFile(audioStream, audioFile)
                        playAudioFromFile(audioFile)
                    } ?: Log.e("TTS", "Response body is null")
                } else {
                    Log.e("TTS", "Error: ${response.code} - ${response.message}")
                }
            }
        })
    }
}

// Hàm lưu dữ liệu âm thanh từ InputStream vào file
fun saveAudioToFile(inputStream: InputStream, outputFile: File) {
    try {
        FileOutputStream(outputFile).use { fos ->
            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                fos.write(buffer, 0, bytesRead)
            }
        }
        Log.d("TTS", "Audio saved to ${outputFile.absolutePath}")
    } catch (e: IOException) {
        Log.e("TTS", "Error saving audio file: ${e.message}")
    }
}

// Hàm phát âm thanh từ file
fun playAudioFromFile(audioFile: File) {
    try {
        val mediaPlayer = MediaPlayer().apply {
            setDataSource(audioFile.absolutePath)
            prepare()
            start()
        }
        mediaPlayer.setOnCompletionListener {
            it.release() // Giải phóng MediaPlayer sau khi hoàn tất
        }
    } catch (e: IOException) {
        Log.e("TTS", "Error playing audio file: ${e.message}")
    }
}
