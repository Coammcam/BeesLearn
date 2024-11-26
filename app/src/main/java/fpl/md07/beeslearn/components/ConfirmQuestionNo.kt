package fpl.md07.beeslearn.components

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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@Composable
fun ConfirmQuestionNo() {
    Box(
        modifier = Modifier
            .padding(top = 15.dp)
            .shadow(
                elevation = 32.dp, // Tăng độ cao bóng để rõ hơn
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
                clip = false // Đảm bảo bóng không bị cắt
            )
            .background(Color(0xFFB8B8B8))
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFFEDDE1),
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
            // Header: Biểu tượng lỗi và tiêu đề
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFFF5A5A), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "✕",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Nunito_Bold,
                        color = Color.White
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Incorrect",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Nunito_Bold,
                    color = Color(0xFFFE4D47)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Nội dung
//            Text(
//                text = "Book: Cuốn sách",
//                fontSize = 16.sp,
//                fontWeight = FontWeight.Bold,
//                fontFamily = Nunito_Bold,
//                color = Color(0xFFFE4D47),
//                modifier = Modifier
//                    .align(Alignment.Start)
//                    .padding(start = 16.dp)
//            )
//
//            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { /* Xử lý khi nhấn nút */ },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF5A5A)),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
                Text(
                    text = "CONTINUE",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontFamily = Nunito_Bold
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewConfirmQuestion() {
    ConfirmQuestionNo()
}