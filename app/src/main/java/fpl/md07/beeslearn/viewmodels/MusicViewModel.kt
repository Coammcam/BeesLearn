package fpl.md07.beeslearn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.api.HttpRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MusicViewModel : ViewModel() {

    val musicList = liveData(Dispatchers.IO) {

        val response = HttpRequest.getInstance().getMusicList()
        if (response.isSuccessful) {
            emit(response.body() ?: emptyList())
        } else {
            emit(emptyList())
        }
    }

    fun fetchMusicList() {
        viewModelScope.launch {

        }
    }
}
