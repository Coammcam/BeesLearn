package fpl.md07.beeslearn.viewmodels

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.api.HttpRequest
import fpl.md07.beeslearn.models.Podcast
import kotlinx.coroutines.launch

class PodcastViewModel : ViewModel() {
    private val _podcasts = mutableStateOf<List<Podcast>>(emptyList())
    val podcasts: State<List<Podcast>> = _podcasts

    private val _loading = mutableStateOf(true)
    val loading: State<Boolean> = _loading

    private val _error = mutableStateOf<String?>(null)
    val error: State<String?> = _error

    init {
        loadPodcasts()
    }

    private fun loadPodcasts() {
        viewModelScope.launch {
            _loading.value = true
            _error.value = null
            try {
                val apiService = HttpRequest.getInstance()
                val response = apiService.getPodcasts()
                if (response.isSuccessful) {
                    _podcasts.value = response.body() ?: emptyList()
                    Log.d("data podcast", _podcasts.value.toString())
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