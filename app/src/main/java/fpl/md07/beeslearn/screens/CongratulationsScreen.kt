package fpl.md07.beeslearn.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import fpl.md07.beeslearn.GlobalVariable.UserSession
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.GlideImage
import fpl.md07.beeslearn.viewmodels.UserDataViewModel

// Tạo FontFamily cho Nunito Bold
val NunitoBold = FontFamily(
    Font(R.font.nunito_bold, FontWeight.Bold)
)

@Composable
fun CongratulationsScreen(goBack: () -> Unit) {
    val userDataViewModel: UserDataViewModel = viewModel()
    val currencyData = userDataViewModel.currencyData.value

    LaunchedEffect(Unit) {
        currencyData!!.honeyJar += UserSession.bonusHoneyJar
        currencyData!!.level += 1
        println(currencyData!!.level)
        userDataViewModel.updateCurrencyData(currencyData!!)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        // Main content box for overlapping images
        Box(
            modifier = Modifier
                .align(Alignment.Center) // Center the main content
        ) {

            Image(
                painter = painterResource(id = R.drawable.beeds),
                contentDescription = "Main Image",
                modifier = Modifier
                    .size(200.dp)
                    .offset(y = (-80).dp)
            )


            GlideImage(
                imageUrl = R.drawable.firework,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterStart)
                    .offset(x = (-80).dp)
                    .offset(y = (-50).dp)
            )


            GlideImage(
                imageUrl = R.drawable.firework,
                modifier = Modifier
                    .size(150.dp)
                    .align(Alignment.CenterEnd)
                    .offset(x = 80.dp)
                    .offset(y = (-50).dp)
                    .graphicsLayer(scaleX = -1f)
            )
        }


        Column(
            modifier = Modifier
                .align(Alignment.Center) // Center the text below the images
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(160.dp))

            // Text Section
            Spacer(modifier = Modifier.height(100.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Chúc mừng!",
                    fontSize = 33.sp,
                    fontFamily = NunitoBold,
                    color = Color(0xFFFFA000)
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Bạn đã làm bài kiểm tra rất tốt",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(60.dp))

            // Info Boxes for "Reward"
            InfoBoxes()
        }
    }
}




@Composable
fun InfoBoxes() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Box đầu tiên cho "Total XP"
//        InfoBox(title = "Total XP", value = "+30", icon = null)

        // Box thứ hai cho "Reward" với biểu tượng
        InfoBox(title = "Reward", value = "+ ${UserSession.bonusHoneyJar}", icon = R.drawable.honey_picture)
    }
}


@Composable
fun InfoBox(title: String, value: String, icon: Int?) {
    Box(
        modifier = Modifier
            .width(130.dp)
            .height(100.dp)
            .background(Color(0xFFFFF176), shape = RoundedCornerShape(12.dp)) // Nền màu vàng và bo góc
            .padding(8.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // Text "Total XP" hoặc "Reward" ở trên cùng của Box vàng
            Text(
                text = title,
                fontSize = 14.sp,
                fontFamily = NunitoBold,
                color = Color(0xFF6A1B9A)

            )

            Spacer(modifier = Modifier.height(8.dp))


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight() // Chiều cao của Box màu trắng
                    .background(Color.White, shape = RoundedCornerShape(8.dp)), // Nền trắng và bo góc
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    // Hiển thị giá trị "+30" hoặc "+5"
                    Text(
                        text = value,
                        fontSize = 16.sp,
                        color = Color(0xFF6A1B9A),
                        fontWeight = FontWeight.Bold
                    )

                    // Nếu có biểu tượng, hiển thị biểu tượng bên cạnh giá trị
                    icon?.let {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            tint = Color(0xFF6A1B9A),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = false)
@Composable
fun PreviewCongratulationsScreen() {
    CongratulationsScreen(){

    }
}
