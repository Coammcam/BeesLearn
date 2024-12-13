package fpl.md07.beeslearn.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(top = 150.dp, start = 50.dp, end = 50.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .padding(bottom = 80.dp)
        )

        Spacer(modifier = Modifier.height(25.dp))

        Text(
            "Nhập Email:",
            fontFamily = Nunito_Bold,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Start,
            color = Color(0xFF777777)
        )

        Spacer(modifier = Modifier.height(5.dp))

        Box(
            modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.CenterEnd
        ) {
            val textFieldHeight = 56.dp

            TextField(value = email,
                onValueChange = { email = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(textFieldHeight)
                    .clip(RoundedCornerShape(50.dp))
                    .border(
                        width = 2.dp, color = Color.Black, shape = RoundedCornerShape(50.dp)
                    ),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent
                ),
                textStyle = TextStyle(
                    fontFamily = Nunito_Bold, fontSize = 16.sp, color = Color.Black
                ),
                trailingIcon = {
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .height(textFieldHeight)
                            .clip(RoundedCornerShape(50.dp)),
                        colors = ButtonDefaults.buttonColors(Color(0xFFFFD528))
                    ) {
                        Text(
                            "Tiếp tục",
                            fontFamily = Nunito_Bold,
                            color = colorResource(id = R.color.secondary_color),
                            modifier = Modifier
                                .clickable {navController.navigate("nhapMaCodeScreen")}
                        )
                    }
                })
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun QuenMatKhauScreenPreview() {
    var navController = rememberNavController()
    ForgotPasswordScreen(navController)
}
