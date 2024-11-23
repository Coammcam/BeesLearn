package fpl.md07.beeslearn.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fpl.md07.beeslearn.GlobalVariable.UserSession
import fpl.md07.beeslearn.api.HttpRequest
import fpl.md07.beeslearn.models.UserModel
import fpl.md07.beeslearn.requests.LoginRequest
import fpl.md07.beeslearn.requests.RegisterRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginMessage = MutableStateFlow<String?>(null)
    val loginMessage: StateFlow<String?> = _loginMessage

    private val _registerMessage = MutableStateFlow<String?>(null)
    val registerMessage: StateFlow<String?> = _registerMessage

    private val _loginResponse = MutableStateFlow<Response<UserModel>?>(null)
    val loginResponse: StateFlow<Response<UserModel>?> = _loginResponse

    private val _registerResponse = MutableStateFlow<Response<UserModel>?>(null)
    val registerResponse: StateFlow<Response<UserModel>?> = _registerResponse

    private val _userData = MutableStateFlow<UserModel?>(null)
    val userData: StateFlow<UserModel?> = _userData

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                _loginResponse.value = null
                _loginMessage.value = null

                val response = HttpRequest.getInstance().Login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    _loginResponse.value = response
                    _loginMessage.value = "Đăng nhập thành công!"
                    _userData.value = response.body()
                    Log.d("LoginViewModel", "User data: ${_userData.value}")

                    UserSession.currentUser = response.body()
                    Log.d("currentUser", "User data: ${_userData.value}")

                } else {
                    if (response.code() == 404) {
                        _loginMessage.value = "Tài khoản hoặc mật khẩu không chính xác."
                    } else {
                        _loginMessage.value = "Đã xảy ra lỗi: ${response.message()}"
                    }
                }
            } catch (e: Exception) {
                _loginMessage.value = "Không thể kết nối đến server: ${e.message}"
            }
        }
    }

    fun register(email: String, password: String, username: String) {
        viewModelScope.launch {
            try {
                _registerResponse.value = null
                _registerMessage.value = null
                val response =
                    HttpRequest.getInstance().Register(RegisterRequest(username, email, password))
                if (response.isSuccessful) {
                    _registerMessage.value = "Đăng ký thành công!"
                } else {
                    if (response.code() == 409) {
                        _registerMessage.value = "Email này đã tồn tại!"
                    } else {
                        _registerMessage.value = "Đã xảy ra lỗi: ${response.message()}"
                    }
                }
            } catch (e: Exception) {
                _registerMessage.value = "Không thể kết nối đến server: ${e.message}"
            }
        }
    }
}
