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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import kotlinx.coroutines.delay

@Composable
fun HomePageComponent() {
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

    LaunchedEffect(key1 = currentHeartIndex.value) {
        if (currentHeartIndex.value < heartImages.size) {
            delay(3000) // 5 giây
            heartImages[currentHeartIndex.value] = R.drawable.heart
            currentHeartIndex.value++
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
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

            Text(
                text = "30:30",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painterResource(id = R.drawable.crybaby),
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
                        text = "Bạn đã hết\ntrái tim rồi!",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(10.dp),
                        color = Color.Black,
                        fontFamily = Nunito_Bold
                    )
                }
            }

            Button(
                onClick = { /* TODO: Xử lý logic */ },
                colors = ButtonDefaults.buttonColors(Color(0xFF8C5437)),
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .padding(horizontal = 8.dp)
            ) {
                Text(
                    text = "REFILL",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomePageComponent() {
    HomePageComponent()
}
