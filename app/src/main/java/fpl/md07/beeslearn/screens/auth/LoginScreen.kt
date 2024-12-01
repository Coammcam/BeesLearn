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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.CustomPasswordField
import fpl.md07.beeslearn.components.CustomTextField
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    navController: NavController,
    viewModel: LoginViewModel = viewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isRememberMeChecked by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val loginMessage by viewModel.loginMessage.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadRememberedCredentials(context)
        email = viewModel.rememberedEmail
        password = viewModel.rememberedPassword
        isRememberMeChecked = viewModel.isRemembered

        if (isRememberMeChecked && email.isNotBlank() && password.isNotBlank()) {
            navController.navigate("HomeScreen") {
                popUpTo("LoginScreen") { inclusive = true }
            }
        }
    }

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
            labelText = "Email",
            value = email,
            onValueChange = { email = it },
            placeholder = "Nhập email của bạn"
        )

        Spacer(modifier = Modifier.height(20.dp))

        // TextField cho mật khẩu
        CustomPasswordField(
            labelText = "Password",
            value = password,
            onValueChange = { password = it },
            placeholder = "Nhập mật khẩu của bạn"
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Remember Me",
                color = if (isRememberMeChecked) Color(0xFFFFD528) else Color(0xFF777777), // Vàng hoặc xám
                fontFamily = Nunito_Bold,
                fontSize = 13.sp,
                modifier = Modifier.clickable {
                    isRememberMeChecked = !isRememberMeChecked // Đổi trạng thái
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Text(
                "Quên mật khẩu?",
                color = Color(0xFF777777),
                fontFamily = Nunito_Bold,
                fontSize = 13.sp,
                modifier = Modifier.clickable { navController.navigate("quenMatKhauScreen") }
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

        }

        Button(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth()
                .shadow(4.dp, shape = RoundedCornerShape(12.dp)),
            onClick = {
                if (email.isNotBlank() && password.isNotBlank()) {
                    viewModel.login(context, email, password, isRememberMeChecked)
                } else {
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT)
                        .show()
                }
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFFFD528)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "ĐĂNG NHẬP", color = Color.White)
        }

        // Hiển thị thông báo nếu có
        LaunchedEffect(loginMessage) {
            loginMessage?.let { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                if (message == "Đăng nhập thành công!") {
                    navController.navigate("HomeScreen") {
                        popUpTo("LoginScreen") { inclusive = true }
                    }
                }
            }
        }

        Row(modifier = Modifier.padding(top = 50.dp)) {
            Text(
                "Không có tài khoản?",
                fontFamily = Nunito_Bold,
                color = Color(0xFF777777)
            )
            Text(
                " Đăng Ký",
                fontFamily = Nunito_Bold,
                color = Color(0xFFF8A724),
                modifier = Modifier
                    .clickable { navController.navigate("signUpScreen") }
            )
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    var navController = rememberNavController()
    LoginScreen(navController)
}
