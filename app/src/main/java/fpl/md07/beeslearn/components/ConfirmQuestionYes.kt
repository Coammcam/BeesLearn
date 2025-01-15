package fpl.md07.beeslearn.components

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import fpl.md07.beeslearn.GlobalVariable.UserSession
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.UserDataViewModel

@Composable
fun ConfirmQuestionYes (Continue: ()->Unit) {
//    val userDataViewModel: UserDataViewModel = viewModel()
//    val currencyData = userDataViewModel.currencyData.value

    LaunchedEffect(Unit) {
        UserSession.bonusHoneyJar += 10
        UserSession.bonusScore += 10
        UserSession.bonusExp += 5
    }

    Box(
        modifier = Modifier
            .padding(top = 15.dp)
//            .shadow(
//                elevation = 36.dp, // Tăng độ cao để tạo bóng rõ hơn
//                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
//                clip = false // Không cắt để bóng hiển thị mượt
//            )
//            .background(Color(0xFFB8B8B8))
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFD6FEB8),
                    shape = RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 8.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    )
                )
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ){
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFF5CB85C), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "✓",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Good job!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Nunito_Bold,
                    color = Color(0xFF56A704)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { Continue() },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF59CB01)),
                shape = RoundedCornerShape(8.dp), // Rounded button
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "CONTINUE",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }
        }
    }
}

@Preview (showBackground = true, showSystemUi = false)
@Composable
fun PreviewConfirmQuestionYes () {
    ConfirmQuestionYes(){

    }
}