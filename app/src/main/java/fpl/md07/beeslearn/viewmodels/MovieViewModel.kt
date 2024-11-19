package fpl.md07.beeslearn.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.models.Movie
import fpl.md07.beeslearn.api.HttpRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieViewModel : ViewModel() {

    // State to track loading, movie list, and errors
    val movieList = mutableStateOf<List<Movie>>(emptyList())
    val isLoading = mutableStateOf(true)
    val errorMessage = mutableStateOf("")

    init {
        fetchMovies()
    }

    // Function to fetch movies from the API
    private fun fetchMovies() {
        // Set loading state to true
        isLoading.value = true
        errorMessage.value = ""

        // Use coroutine to make the API call
        viewModelScope.launch {
            try {
                // Make the API call in a coroutine context to fetch movies
                val response = HttpRequest.getInstance().getMovies()

                // Handle the response on the main thread after the background call
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        // If the response is successful, update the movie list
                        movieList.value = response.body() ?: emptyList()
                    } else {
                        // If the response is not successful, set an error message
                        errorMessage.value = "Failed to load movies"
                    }
                    isLoading.value = false
                }

            } catch (e: Exception) {
                // Catch any exceptions (e.g., network errors) and set an error message
                withContext(Dispatchers.Main) {
                    errorMessage.value = "Network error: ${e.message}"
                    isLoading.value = false
                }
            }
        }
    }
}
