package fpl.md07.beeslearn.screens.auth

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.CustomPasswordField
import fpl.md07.beeslearn.components.CustomTextField
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

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
            onClick = {navController.navigate("chaoHoiScreen")},
            colors = ButtonDefaults.buttonColors(Color(0xFFFFD528)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(
                text = "ĐĂNG KÝ", color = Color.White, fontFamily = Nunito_Bold
            )
        }

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
                    .clickable {navController.popBackStack()}
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
