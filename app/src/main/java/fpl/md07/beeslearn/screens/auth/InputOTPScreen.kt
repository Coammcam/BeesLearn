package fpl.md07.beeslearn.screens.auth

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputOTPScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Mã bảo mật đã được gửi vào số của bạn",
            fontFamily = Nunito_Bold,
            color = Color.Gray,
            modifier = Modifier
                .padding(top = 163.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "+84 123 456 78",
            fontFamily = Nunito_Bold,
            color = Color(0xFFB71C1C)
        )

        Spacer(modifier = Modifier.height(46 .dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            for (i in 1..5) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier
                        .width(48.dp)
                        .height(56.dp),
                    textStyle = TextStyle(fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontFamily = Nunito_Bold),
                    visualTransformation = VisualTransformation.None,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    shape = RoundedCornerShape(8.dp),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Gray
                    )
                )
            }
        }

        Spacer(modifier = Modifier.height(66.dp))

        Button(
            onClick = { /* TODO: handle login */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .shadow(elevation = 10.dp,
                    shape = RoundedCornerShape(12.dp),
                    clip = false
                ),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFC107))
        ) {
            Text(
                text = "ĐĂNG NHẬP",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                color = Color.White,
                modifier = Modifier
                    .clickable {navController.navigate("loginScreen")}
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InputOTPScreenPreview() {
    var navController = rememberNavController()
    InputOTPScreen(navController)
}