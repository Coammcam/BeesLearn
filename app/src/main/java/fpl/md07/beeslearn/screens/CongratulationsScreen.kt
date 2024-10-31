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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R

// Tạo FontFamily cho Nunito Bold
val NunitoBold = FontFamily(
    Font(R.font.nunito_bold, FontWeight.Bold)
)

@Composable
fun CongratulationsScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Nút Back ở góc trên bên trái
        IconButton(
            onClick = { /* Xử lý sự kiện back */ },
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back",
                tint = Color.Black
            )
        }

        // Nội dung chính ở giữa màn hình
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Hình ảnh ở giữa màn hình
            Image(
                painter = painterResource(id = R.drawable.beeds), // Sử dụng hình ảnh bạn muốn
                contentDescription = "Main Image",
                modifier = Modifier.size(200.dp)
            )

            Spacer(modifier = Modifier.height(16.dp)) // Khoảng cách giữa hình ảnh và text

            // Text "Congratulations" và "You have done your test very well"
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Congratulations!",
                    fontSize = 33.sp,
                    fontFamily = NunitoBold,
                    color = Color(0xFFFFA000)
                )
                Spacer(modifier = Modifier.height(4.dp)) // Khoảng cách nhỏ giữa hai dòng text
                Text(
                    text = "You have done your test very well",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.height(60.dp)) // Khoảng cách giữa text và các box

            // Hai box bên dưới cho Total XP và Reward
            InfoBoxes()
        }
    }
}

@Composable
fun InfoBoxes() {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly, // Căn đều khoảng cách giữa hai Box
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp), // Khoảng cách từ rìa ngoài vào trong một chút
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Box đầu tiên cho "Total XP"
        InfoBox(title = "Total XP", value = "+30", icon = null)

        // Box thứ hai cho "Reward" với biểu tượng
        InfoBox(title = "Reward", value = "+5", icon = R.drawable.honey_picture) // Thay "honey_picture" bằng ID của biểu tượng của bạn
    }
}


@Composable
fun InfoBox(title: String, value: String, icon: Int?) {
    Box(
        modifier = Modifier
            .width(130.dp) // Chiều rộng của Box vàng
            .height(100.dp) // Chiều cao tổng thể của Box vàng
            .background(Color(0xFFFFF176), shape = RoundedCornerShape(12.dp)) // Nền màu vàng và bo góc
            .padding(8.dp), // Khoảng cách bên trong Box vàng
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

            // Box màu trắng bên dưới
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
                        Spacer(modifier = Modifier.width(4.dp)) // Khoảng cách giữa giá trị và biểu tượng
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = null,
                            tint = Color(0xFF6A1B9A),
                            modifier = Modifier.size(18.dp) // Kích thước biểu tượng
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
