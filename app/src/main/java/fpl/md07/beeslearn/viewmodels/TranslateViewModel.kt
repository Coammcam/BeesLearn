package fpl.md07.beeslearn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TranslateViewModel : ViewModel() {
    private val _translationHistory = MutableStateFlow<List<Pair<String, String>>>(emptyList())
    val translationHistory: StateFlow<List<Pair<String, String>>> = _translationHistory

    val sourceText = mutableStateOf("")
    val translatedText = mutableStateOf("Kết quả sẽ hiển thị ở đây")
    val translationError = mutableStateOf<String?>(null)
    val isLoading = mutableStateOf(false)

    fun addToHistory(source: String, translated: String) {
        _translationHistory.value = _translationHistory.value + (source to translated)
    }

    fun clearHistory() {
        _translationHistory.value = mutableListOf()
    }
}

