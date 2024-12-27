package fpl.md07.beeslearn.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import kotlinx.coroutines.delay
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext


@Composable
fun HomePageComponent(navController: NavController) {
    val heartImages = remember {
        mutableStateListOf(
            R.drawable.heart2,
            R.drawable.heart2,
            R.drawable.heart2,
            R.drawable.heart2,
            R.drawable.heart2
        )
    }
    val currentHeartIndex = remember { mutableStateOf(0) }

    // Thời gian hồi tính bằng mili giây
    val delayTimes = listOf(
//        5 * 60 * 1000L,   // 5 phút
//        10 * 60 * 1000L,  // 10 phút
//        15 * 60 * 1000L,  // 15 phút
//        30 * 60 * 1000L,  // 30 phút
//        60 * 60 * 1000L   // 60 phút
        5000L,
        5000L,
        5000L,
        5000L,
        5000L,
    )

    val remainingTime = remember { mutableStateOf(delayTimes[0]) } // Thời gian còn lại
    val isTimerRunning = remember { mutableStateOf(true) } // Trạng thái chạy bộ đếm

    LaunchedEffect(key1 = currentHeartIndex.value) {
        if (currentHeartIndex.value < heartImages.size) {
            remainingTime.value = delayTimes[currentHeartIndex.value]

            while (remainingTime.value > 0 && isTimerRunning.value) {
                delay(1000L) // Giảm mỗi giây
                remainingTime.value -= 1000L
            }

            if (remainingTime.value <= 0) {
                heartImages[currentHeartIndex.value] = R.drawable.heart // Đổi màu trái tim
                currentHeartIndex.value++ // Tăng index để xử lý trái tim tiếp theo
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6)),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFFF192))
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Hiển thị các trái tim
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                heartImages.forEach { heart ->
                    Image(
                        painter = painterResource(id = heart),
                        contentDescription = "Heart",
                        modifier = Modifier.size(40.dp)
                    )
                }
            }

            // Hiển thị thời gian hồi trái tim tiếp theo
            val minutes = remainingTime.value / 60000
            val seconds = (remainingTime.value % 60000) / 1000
            Text(
                text = "Thời gian hồi tim tiếp theo còn: $minutes phút $seconds giây",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painterResource(
                        id = if (currentHeartIndex.value == 0) {
                            R.drawable.crybaby
                        } else {
                            R.drawable.beeds
                        }
                    ),
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
                        text = if (currentHeartIndex.value == 0) {
                            "Bạn đã hết\ntrái tim rồi!"
                        } else {
                            "Bạn đang có \n ${currentHeartIndex.value} trái tim"
                        },
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(10.dp),
                        color = Color.Black,
                        fontFamily = Nunito_Bold
                    )
                }
            }

            Button(
                onClick = {
                    navController.navigate("paymentComponent")
                },
                colors = ButtonDefaults.buttonColors(Color(0xFF8C5437)),
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "Mua Premium",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Button(
                onClick = { navController.popBackStack()},
                colors = ButtonDefaults.buttonColors(Color(0xFFFFF192)),
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp)
                    .background(Color(0xFFFFF192))
            ) {
                Text(
                    text = "Trở lại",
                    color = Color(0xFF8C5437),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewHomePageComponent() {
    var navController = rememberNavController()
    HomePageComponent(navController)
}
