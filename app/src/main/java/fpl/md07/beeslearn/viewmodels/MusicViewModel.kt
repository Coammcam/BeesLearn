package fpl.md07.beeslearn.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.api.HttpRequest
import fpl.md07.beeslearn.models.Music
import kotlinx.coroutines.launch


class MusicViewModel : ViewModel() {
    private val _musics = mutableStateOf<List<Music>>(emptyList())
    val musics: State<List<Music>> = _musics

    private val _loading = mutableStateOf(true)
    val loading: State<Boolean> = _loading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    init {
        loadMusics()
    }

    private fun loadMusics() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val apiService = HttpRequest.getInstance()
                val response = apiService.getMusicList()
                if (response.isSuccessful) {
                    _musics.value = (response.body() ?: emptyList())
                    Log.d("data", _musics.value.toString())
                } else {
                    _error.value = "Failed to load podcasts: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

}
