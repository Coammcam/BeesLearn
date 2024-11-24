package fpl.md07.beeslearn.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import fpl.md07.beeslearn.models.Music
import fpl.md07.beeslearn.viewmodels.data.musicItem1

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

    private fun loadMusics() {
        _loading.value = true
        _error.value = null
        try {
            // Fake data
            _musics.value = musicItem1
        } catch (e: Exception) {
            _error.value = "Error loading sample data: ${e.message}"
        } finally {
            _loading.value = false
        }
    }

}
