package fpl.md07.beeslearn.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.api.HttpRequest
import fpl.md07.beeslearn.requests.ChangePasswordRequest
import kotlinx.coroutines.launch
import org.json.JSONObject

class ChangePasswordViewModel : ViewModel() {
    // Hàm gọi API đổi mật khẩu
    fun changePassword(
        request: ChangePasswordRequest,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response = HttpRequest.getInstance().ChangePassword(request)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    // Parse lỗi từ phản hồi
                    val errorBody = response.errorBody()?.string()
                    val errorMessage = try {
                        val jsonObject = JSONObject(errorBody)
                        jsonObject.getString("message") // Lấy thông điệp lỗi từ phản hồi
                    } catch (e: Exception) {
                        errorBody ?: "Lỗi không xác định"
                    }

                    // Kiểm tra lỗi cụ thể từ thông báo API
                    when {
                        errorMessage.contains("Email not found") -> {
                            onError("Email không tồn tại trong hệ thống!")
                        }
                        errorMessage.contains("Old password is incorrect") -> {
                            onError("Mật khẩu cũ không đúng!")
                        }
                        else -> {
                            onError(errorMessage)
                        }
                    }
                }
            } catch (e: Exception) {
                onError("Exception: ${e.message}")
            }
        }
    }


}
