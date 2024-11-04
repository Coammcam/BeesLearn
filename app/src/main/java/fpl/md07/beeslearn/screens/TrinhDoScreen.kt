package fpl.md07.beeslearn.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.CustomButtonWithText
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@Composable
fun TrinhDoScreen(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_color)),
    ) {
        Column {
            Spacer(modifier = Modifier.height(100.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painterResource(id = R.drawable.logo_without_text),
                    contentDescription = "Placeholder",
                    modifier = Modifier
                        .width(170.dp)
                        .height(210.dp)
                )

                Box(
                    modifier = Modifier
                        .width(230.dp)
                        .height(130.dp)
                ) {
                    Image(
                        painterResource(id = R.drawable.cloudshape),
                        contentDescription = "Placeholder",
                        modifier = Modifier.fillMaxSize()
                    )

                    Text(
                        text = "Bạn đang ở\n" +
                                "trình độ nào?",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(10.dp),
                        color = Color.Black,
                        fontFamily = Nunito_Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(55.dp))

            CustomButtonWithText(
                onClick = { navController.navigate("tanSuatScreen")},
                buttonText = "Người mới bắt đầu"
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomButtonWithText(
                onClick = { navController.navigate("tanSuatScreen")},
                buttonText = "Đã biết một chút"
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomButtonWithText(
                onClick = { navController.navigate("tanSuatScreen")},
                buttonText = "Đã nghe nói ở mức khá tốt"
            )

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TrinhDoScreenPreview() {
    var navController = rememberNavController()
    TrinhDoScreen(navController)
}