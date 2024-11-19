package fpl.md07.beeslearn.screens.auth

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.CustomPasswordField
import fpl.md07.beeslearn.components.CustomTextField
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.LoginViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    val context = LocalContext.current

    // Lấy trạng thái thông báo từ ViewModel
    val registerMessage by viewModel.registerMessage.collectAsState()


    Column(
        modifier = Modifier
            .background(Color(0xffffffff))
            .padding(horizontal = 50.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(267.dp)
                .padding(bottom = 50.dp)
        )

        CustomTextField(
            labelText = "Tên",
            value = name,
            onValueChange = { name = it },
            placeholder = "Nhập tên"
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            labelText = "Email",
            value = email,
            onValueChange = { email = it },
            placeholder = "Nhập email"
        )

        Spacer(modifier = Modifier.height(20.dp))

        // TextField cho mật khẩu
        CustomPasswordField(
            labelText = "Password",
            value = password,
            onValueChange = { password = it },
            placeholder = "Nhập mật khẩu"
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomPasswordField(
            labelText = "Password",
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            placeholder = "Nhập lại mật khẩu"
        )

        Button(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
                .shadow(4.dp, shape = RoundedCornerShape(12.dp)),
            onClick = {
                when {
                    name.isBlank() || email.isBlank() || password.isBlank() || confirmPassword.isBlank() -> {
                        Toast.makeText(context, "Vui lòng nhập đầy đủ dữ liệu!", Toast.LENGTH_SHORT).show()
                    }
                    password != confirmPassword -> {
                        Toast.makeText(context, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        viewModel.register(email, password, name)
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFFFD528)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "ĐĂNG KÝ", color = Color.White, fontFamily = Nunito_Bold
            )
        }

        // Lắng nghe thông báo từ ViewModel
        LaunchedEffect(registerMessage) {
            registerMessage?.let { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                if (message == "Đăng ký thành công!") {
                    navController.navigate("chaoHoiScreen") {
                        popUpTo("SignUpScreen") { inclusive = true }
                    }
                }
            }
        }

//        LaunchedEffect(registerResponse) {
//            if (registerResponse == null) {
//                Toast.makeText(context, "Không thể kết nối tới máy chủ. Vui lòng thử lại sau.", Toast.LENGTH_LONG).show()
//            } else {
//                if (registerResponse!!.isSuccessful) {
//                    Toast.makeText(context, "Đăng ký thành công!", Toast.LENGTH_SHORT).show()
//                    navController.navigate("chaoHoiScreen")
//                } else {
//                    val errorMessage =
//                        registerResponse!!.errorBody()?.string() ?: "Đăng ký thất bại."
//                    Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
//                }
//            }
//        }

        Row(modifier = Modifier.padding(top = 50.dp)) {
            Text(
                "Bạn đã có tài khoản?",
                fontFamily = Nunito_Bold,
                color = Color(0xFF777777)
            )
            Text(
                " Đăng Nhập",
                fontFamily = Nunito_Bold,
                color = Color(0xFFF8A724),
                modifier = Modifier
                    .clickable { navController.popBackStack() }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SignUpScreenPreview() {
    var navController = rememberNavController()
    SignUpScreen(navController)
}
