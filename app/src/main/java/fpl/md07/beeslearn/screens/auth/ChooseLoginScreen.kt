package fpl.md07.beeslearn.screens.auth

import androidx.compose.foundation.Image
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.CustomButtonLogin
import fpl.md07.beeslearn.screens.NunitoBold


@Composable
fun ChooseLoginScreen(navController: NavController) {
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
            Image(
                painter = painterResource(R.drawable.beeds),
                contentDescription = null,
                modifier = Modifier
                    .padding(bottom = 80.dp)
            )
            Text(
                "Xin Chào! Bee",
                fontSize = 35.sp,
                color = Color(0xFF591429),
                textAlign = TextAlign.Center,
                fontFamily = NunitoBold
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomButtonLogin(
                imageResId = R.drawable.beeds,
                title = "Bắt Đầu Học",
                backgroundColor = Color(0xFFFFFFFF),
                textColor = Color.Black,
                onClick = { navController.navigate("loginScreen")}
            )

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    var navController = rememberNavController()
    ChooseLoginScreen(navController)
}