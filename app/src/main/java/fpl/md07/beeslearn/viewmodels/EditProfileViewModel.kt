package fpl.md07.beeslearn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.api.ApiService
import fpl.md07.beeslearn.api.HttpRequest
import fpl.md07.beeslearn.models.UserModel
import fpl.md07.beeslearn.requests.UpdateUserRequest
import kotlinx.coroutines.launch
import okhttp3.RequestBody.Companion.toRequestBody

class EditProfileViewModel : ViewModel() {
    private val apiService: ApiService = HttpRequest.getInstance()


    fun updateProfile(updatedUser: UpdateUserRequest, onSuccess: (UserModel) -> Unit, onError: (String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.EditProfile(
                    email = updatedUser.email.toRequestBody(),
                    username = updatedUser.username.toRequestBody(),
                    phoneNumber = updatedUser.phoneNumber?.toRequestBody(),
                    dateOfBirth = updatedUser.dateOfBirth?.toRequestBody(),
                    file = updatedUser.file
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        onSuccess(it)
                    }
                } else {
                    onError("Lỗi server: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Lỗi: ${e.localizedMessage}")
            }
        }
    }
}
