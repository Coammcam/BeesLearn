package fpl.md07.beeslearn.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.CustomButtonLogin


@Composable
fun WelcomeScreen() {
    Box(
        modifier = Modifier
            .background(color = colorResource(id = R.color.primary_color))
            .padding(horizontal = 20.dp)
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center), // Căn giữa cả cột trong box
            horizontalAlignment = Alignment.CenterHorizontally, // Căn giữa theo chiều ngang
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                "Bắt đầu học cùng \nBee !",
                fontSize = 35.sp,
                color = Color(0xFF591429),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(30.dp))

            CustomButtonLogin(
                imageResId = R.drawable.email,
                title = "Tiếp tục với Email",
                backgroundColor = Color(0xFF591429),
                textColor = Color.White,
                onClick = {}
            )

            Spacer(modifier = Modifier.height(15.dp))

            CustomButtonLogin(
                imageResId = R.drawable.apple,
                title = "Tiếp tục với Apple",
                backgroundColor = Color(0xFF130101),
                textColor = Color.White,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(15.dp))

            CustomButtonLogin(
                imageResId = R.drawable.facebook,
                title = "Tiếp tục với Facebook",
                backgroundColor = Color(0xFF475993),
                textColor = Color.White,
                onClick = {}
            )
            Spacer(modifier = Modifier.height(15.dp))

            CustomButtonLogin(
                imageResId = R.drawable.google,
                title = "Tiếp tục với Google",
                backgroundColor = Color(0xFFFFFFFF),
                textColor = Color.Black,
                onClick = {}
            )

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}