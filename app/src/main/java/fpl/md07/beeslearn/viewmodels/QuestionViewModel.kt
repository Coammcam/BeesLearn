package fpl.md07.beeslearn.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.api.ApiService
import fpl.md07.beeslearn.api.HttpRequest
import fpl.md07.beeslearn.models.responseModel.QuestionResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class QuestionViewModel : ViewModel() {

    private val apiService = HttpRequest.getInstance()

    fun getRandomQuestions(
        total: Int, onSuccess: (QuestionResponseModel) -> Unit, onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getRandomQuestions(total)
//                    Log.d("data", response)
                }
                Log.d("getRandomQuestions data", response.toString())
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    } ?: onError("No data received.")
                } else {
                    onError("Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                onError("Exception: ${e.message}")
            }
        }
    }
}