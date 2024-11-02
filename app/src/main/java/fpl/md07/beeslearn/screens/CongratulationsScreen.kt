package fpl.md07.beeslearn.screens
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.GlideImage
// Tạo FontFamily cho Nunito Bold
val NunitoBold = FontFamily(
    Font(R.font.nunito_bold, FontWeight.Bold)
)
@Composable
fun CongratulationsScreen() {
    Column(
        modifier = Modifier
            .padding(1.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp),
                 contentAlignment = Alignment.Center
        ) {
            GlideImage(
                imageUrl = R.drawable.phaohoa,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterStart)

            )
            GlideImage(
                imageUrl = R.drawable.phaohoa,
                modifier = Modifier
                    .size(300.dp)
                    .align(Alignment.CenterEnd)
                    .graphicsLayer(scaleX = -1f) //lật

            )
//            Image(
//                painter = painterResource(id = R.drawable.elip),
//                contentDescription = "Shadow",
//                modifier = Modifier
//                    .align(Alignment.BottomCenter)
//            )

            Image(
                painter = painterResource(id = R.drawable.beeds),
                contentDescription = "Main Image",
                modifier = Modifier.size(200.dp),
                Alignment.Center
                
            )
        }
        Text(
            text = "Congratulations!",
            fontSize = 33.sp,
            fontFamily = NunitoBold,
            color = Color(0xFFFFD527)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = "You have done your test very well",
            fontSize = 14.sp,
            fontFamily = NunitoBold,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(60.dp))

        InfoBoxes()
    }
}

@Composable
fun InfoBoxes() {
    Row(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        InfoBox(title = "Total XP", value = "+30", icon = null)


        InfoBox(
            title = "Reward",
            value = "+5",
            icon = R.drawable.honey,
        )
    }
}
@Composable
fun InfoBox(title: String, value: String, icon: Int?) {
    Box(
        modifier = Modifier
            .width(130.dp)
            .height(100.dp)
            .background(
                Color(0xFFFFF192),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(2.dp), // Khoảng cách bên trong Box vàng
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                fontSize = 16.sp,
                fontFamily = NunitoBold,
                color = Color(0xFF6A1B9A),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(8.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = value,
                        fontSize = 16.sp,
                        color = Color(0xFF6A1B9A),
                        fontFamily = NunitoBold
                    )
                    icon?.let {
                        Spacer(modifier = Modifier
                            .height(10.dp))
                        Image(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCongratulationsScreen() {
    CongratulationsScreen()
}
