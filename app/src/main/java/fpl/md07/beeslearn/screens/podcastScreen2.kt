package fpl.md07.beeslearn.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R


@Composable
fun PodcastScreen2 () {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(R.drawable.back_left),
            contentDescription = "Back",
            modifier = Modifier
                .padding(top = 50.dp, start = 20.dp, bottom = 20.dp)
                .clickable { /* Handle back navigation */ }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(24.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.podcast1_2),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Image(
                painter = painterResource(R.drawable.podcast1_1),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            )
        }
        Text(
            "Learn English with PODCASTS When I Was Younger...",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(top = 44.dp, start = 20.dp, end = 20.dp),
            color = Color(0xff591429)
        )
        Box(
            modifier = Modifier
                .padding(start = 30.dp, end = 30.dp, top = 25.dp)
                .fillMaxWidth()  // Chiều rộng chiếm toàn bộ màn hình
                .height(1.dp)    // Chiều cao của đường line
                .background(Color.Gray)  // Màu của đường line
        )
        Row (
            modifier = Modifier
                .padding(start = 25.dp, end = 25.dp, top = 15.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                "2:30",
                fontSize = 16.sp,
            )
            Text(
                "-2:30",
                fontSize = 16.sp,
            )
        }
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp, top = 30.dp)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(40.dp, Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.next_left_music),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Image(
                painter = painterResource(R.drawable.play),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Image(
                painter = painterResource(R.drawable.next_right_music),
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(28.dp),
            horizontalArrangement = Arrangement.SpaceBetween // Tách hai hàng con cách xa nhau về hai đầu
        ) {
            // Row ở start chứa 2 ảnh
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp) // Cách ảnh trong row 8dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.like), // Thay thế bằng ID ảnh thực tế
                    contentDescription = "Ảnh 1",
                    modifier = Modifier
                        .size(25.dp) // Kích thước tùy chọn
                )

                Image(
                    painter = painterResource(id = R.drawable.like),
                    contentDescription = "Ảnh 2",
                    modifier = Modifier
                        .size(25.dp)
                        .graphicsLayer(rotationZ = 180f)
                )
            }

            // Row ở end chứa 2 ảnh
            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp) // Cách ảnh trong row 8dp
            ) {
                Image(
                    painter = painterResource(id = R.drawable.share),
                    contentDescription = "Ảnh 3",
                    modifier = Modifier
                        .size(25.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.down  ),
                    contentDescription = "Ảnh 4",
                    modifier = Modifier
                        .size(25.dp)
                )
            }
        }
        Text(
            "Today, you'll learn advanced English vocabulary with Cassé telling stories from the old days when she was a child back in school!",
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp, top = 10.dp),
            color = Color(0xff591429)
        )
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewPodcastScreen2 () {
    PodcastScreen2()
}