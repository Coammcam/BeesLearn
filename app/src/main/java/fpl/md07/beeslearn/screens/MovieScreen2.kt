package fpl.md07.beeslearn.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@Composable
fun MovieScreen2 () {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
//        horizontalAlignment = Alignment.CenterHorizontally
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
                painter = painterResource(R.drawable.posterphim5_1),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Image(
                painter = painterResource(R.drawable.posterphim5_2),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            )
        }
        Text(
            text = "Lion King",
            fontSize = 35.sp,
            color = colorResource(id = R.color.secondary_color),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = "1994 - Adventure, Drama - 1h 58m",
            fontSize = 18.sp,
            color = colorResource(id = R.color.secondary_color),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 40.dp, end = 40.dp, top = 10.dp)
                .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.star),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Image(
                painter = painterResource(R.drawable.star),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Image(
                painter = painterResource(R.drawable.star),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Image(
                painter = painterResource(R.drawable.star),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
            Image(
                painter = painterResource(R.drawable.star_dis),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth() // Chiếm toàn bộ màn hình
        ) {
            Button(
                onClick = { /* TODO: Xử lý sự kiện khi nhấn nút */ },
                modifier = Modifier
                    .padding(16.dp)
                    .height(56.dp)
                    .width(179.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .align(Alignment.TopCenter), // Căn nút vào giữa màn hình
                colors = ButtonDefaults.buttonColors(Color(0xFF591429))
            ) {
                // Icon Play
                Icon(
                    painter = painterResource(id = R.drawable.play2), // Thay thế với icon play của bạn
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp) // Kích thước của icon
                )

                Spacer(modifier = Modifier.width(14.dp)) // Khoảng cách giữa icon và text

                // Text Play
                Text(
                    text = "Play",
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
        Text(
            text = "After the murder of his father, a young lion prince flees his kingdom only to learn the true meaning of responsibility and bravery.",
            fontSize = 18.sp,
            color = colorResource(id = R.color.secondary_color),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 40.dp, end = 40.dp),
            textAlign = TextAlign.Center
        )

    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewMovieScreen2 () {
    MovieScreen2()
}
