package fpl.md07.beeslearn.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@Composable
fun ComfirmQuestion () {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB8B8B8))
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFFFFFFA6),
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
            Text(
                text = "Are you sure?",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = Nunito_Bold,
                color = Color(0xFF5A1E2D)
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = { /* Xử lý cho nút Cancel */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.LightGray),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier.weight(1f).padding(end = 8.dp)
                ) {
                    Text(
                        text = "CANCEL",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = Nunito_Bold
                    )
                }

                Button(
                    onClick = { /* Xử lý cho nút Yes */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF5A1E2D)),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp)
                ) {
                    Text(
                        text = "YES",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontFamily = Nunito_Bold
                    )
                }
            }
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewConfirm () {
    ComfirmQuestion()
}