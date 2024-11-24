package fpl.md07.beeslearn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.api.ApiService
import fpl.md07.beeslearn.api.HttpRequest
import fpl.md07.beeslearn.models.UserModel
import fpl.md07.beeslearn.requests.UpdateUserRequest
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class EditProfileViewModel(
    private val apiService: ApiService = HttpRequest.getInstance()
) : ViewModel() {

    fun updateProfile(
        updatedUser: UpdateUserRequest,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = apiService.EditProfile(updatedUser)
                if (response.isSuccessful) {
                    val updatedUserModel = response.body()
                    if (updatedUserModel != null) {
                        onSuccess(updatedUserModel)
                    } else {
                        onError("Dữ liệu trả về rỗng.")
                    }
                } else {
                    val errorBody = response.errorBody()?.string() ?: "Lỗi không xác định"
                    onError("Cập nhật thất bại: $errorBody (${response.code()})")
                }
            } catch (e: HttpException) {
                onError("Lỗi mạng: ${e.message}")
            } catch (e: IOException) {
                onError("Lỗi kết nối. Vui lòng kiểm tra internet.")
            } catch (e: Exception) {
                onError("Lỗi không mong muốn: ${e.message}")
            }
        }

    }
}


