package fpl.md07.beeslearn.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.api.HttpRequest
import fpl.md07.beeslearn.models.Podcast
import fpl.md07.beeslearn.viewmodels.data.podcastList
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

//    private fun loadPodcasts() {
//        viewModelScope.launch {
//            _loading.value = true
//            _error.value = null
//            try {
//                val apiService = HttpRequest.getInstance()
//                val response = apiService.getPodcasts()
//                if (response.isSuccessful) {
////                    _podcasts.value = response.body() ?: emptyList()
//                    _podcasts.value = podcastList
//                } else {
//                    _error.value = "Failed to load podcasts: ${response.message()}"
//
//                }
//            } catch (e: Exception) {
//                _error.value = e.message
//            } finally {
//                _loading.value = false
//            }
//        }
//    }

    private fun loadPodcasts() {
        _loading.value = true
        _error.value = null
        try {
            // Fake data
            _podcasts.value = podcastList
        } catch (e: Exception) {
            _error.value = "Error loading sample data: ${e.message}"
        } finally {
            _loading.value = false
        }
    }

}
