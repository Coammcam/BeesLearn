package fpl.md07.beeslearn.screens.tabs

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R

@Composable
fun TranslateScreen(navController: NavController) {
    // Trạng thái theo dõi ngôn ngữ hiện tại
    var currentLanguage by remember { mutableStateOf("ENG") }  // Ngôn ngữ ban đầu là ENG
    var inputText by remember { mutableStateOf("Hello, Nice to meet you") }  // Văn bản nhập ban đầu
    var translatedText by remember { mutableStateOf("Xin chào, rất vui được gặp bạn") }  // Văn bản dịch
    var translationHistory by remember { mutableStateOf(listOf<String>()) } // Lịch sử dịch

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Tăng chiều rộng
        ) {
            // Hàng đầu tiên: Nút Back
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), // Tăng khoảng cách giữa các hàng
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    navController.popBackStack() // Quay lại màn hình trước đó
                }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        modifier = Modifier.size(30.dp)
                    )
                }
            }

            // Hàng thứ hai: Nhãn ngôn ngữ "ENG" và "VIE" với mũi tên hoán đổi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp), // Tăng khoảng cách giữa các hàng
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = if (currentLanguage == "ENG") "ENG" else "VIE", // Hiển thị ngôn ngữ hiện tại
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
                Icon(
                    painter = painterResource(id = R.drawable.swap), // Biểu tượng mũi tên
                    contentDescription = "Swap Languages",
                    modifier = Modifier
                        .size(32.dp)
                        .clickable {
                            // Khi nhấn vào mũi tên hoán đổi ngôn ngữ
                            if (currentLanguage == "ENG") {
                                currentLanguage = "VIE"
                                val temp = inputText
                                inputText = translatedText
                                translatedText = temp
                            } else {
                                currentLanguage = "ENG"
                                val temp = inputText
                                inputText = translatedText
                                translatedText = temp
                            }
                        }
                )
                Text(
                    text = if (currentLanguage == "ENG") "VIE" else "ENG", // Hiển thị ngôn ngữ đích
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color.Black
                    )
                )
            }

            // Thanh ngang thứ nhất
            Divider(
                color = Color(0xFF800000), // Màu nâu
                thickness = 2.dp // Độ dày của thanh ngang
            )

            Spacer(modifier = Modifier.height(16.dp)) // Tăng khoảng cách giữa các hàng

            // Hàng thứ ba: Hiển thị văn bản cần dịch
            Text(
                text = inputText,
                style = TextStyle(fontSize = 20.sp, color = Color.Black), // Tăng kích thước chữ
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp) // Tăng padding
            )

            // Thanh ngang thứ hai
            Divider(
                color = Color(0xFF800000), // Màu nâu
                thickness = 2.dp // Độ dày của thanh ngang
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Hàng thứ tư: Hiển thị bản dịch
            Text(
                text = translatedText,
                style = TextStyle(fontSize = 20.sp, color = Color.Black), // Tăng kích thước chữ
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(14.dp)
            )

            // Thanh ngang thứ ba
            Divider(
                color = Color(0xFF800000), // Màu nâu
                thickness = 2.dp // Độ dày của thanh ngang
            )

            Spacer(modifier = Modifier.height(16.dp)) // Tăng khoảng cách

            // Sử dụng Text thay vì Button để xóa lịch sử dịch
            Text(
                text = "Xóa lịch sử dịch", // Text thay vì Button
                style = TextStyle(fontSize = 18.sp, color = Color.Blue, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .wrapContentWidth(Alignment.End)  // Căn chỉnh text sang bên phải
                    .clickable {
                        translationHistory = listOf() // Xóa lịch sử dịch khi nhấn vào
                    }
            )
            Spacer(modifier = Modifier.height(16.dp))

            // Hiển thị danh sách lịch sử dịch nếu có
            if (translationHistory.isNotEmpty()) {
                Text(
                    text = "Lịch sử dịch:",
                    style = TextStyle(fontSize = 22.sp, fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                LazyColumn(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(translationHistory) { historyItem ->
                        Text(
                            text = historyItem,
                            style = TextStyle(fontSize = 18.sp, color = Color.Black),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp)
                                .border(1.dp, Color.Gray, RoundedCornerShape(8.dp))
                                .padding(16.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewTranslateScreen() {
    val navController = rememberNavController()
    TranslateScreen(navController)
}
