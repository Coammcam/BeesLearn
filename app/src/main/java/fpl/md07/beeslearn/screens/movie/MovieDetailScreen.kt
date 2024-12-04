package fpl.md07.beeslearn.screens.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.BackComponent
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun MovieDetailScreen(
    navController: NavController,
    title: String,
    duration: String,
    genre: String,
    year: String,
    rating: String,
    description: String,
    banner: String,
) {
    // State to handle loading status
    var isLoading by remember { mutableStateOf(true) }

    // Simulate loading data (e.g., from an API)
    LaunchedEffect(true) {
        // Simulate API call delay
        kotlinx.coroutines.delay(2000) // 2 seconds delay for demo
        isLoading = false // Set isLoading to false when data is fetched
    }

    // Show a loading spinner while data is being loaded
    if (isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator() // This is the loading spinner
        }
    } else {
        // Content of the screen when data is loaded
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 30.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            BackComponent(navController)
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp),
            verticalArrangement = Arrangement.Top,
        ) {
            val bannerPainter = rememberImagePainter(
                data = banner,
                builder = {
                    crossfade(true) // Add transition effect when loading
                }
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(24.dp)
            ) {
                Image(
                    painter = bannerPainter,
                    contentDescription = null,
                    modifier = Modifier.fillMaxWidth()
                )
                Image(
                    painter = bannerPainter,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                )
            }
            Text(
                text = title,
                fontSize = 35.sp,
                color = colorResource(id = R.color.secondary_color),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                fontWeight = FontWeight.Bold,
                fontFamily = Nunito_Bold,
                textAlign = TextAlign.Center,
                lineHeight = 40.sp
            )

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
                        fontFamily = Nunito_Bold,
                        fontWeight = FontWeight.Bold
                    )
                }

            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 15.dp, end = 15.dp),  // Thêm padding phía trên và dưới
                horizontalAlignment = Alignment.Start,  // Căn lề trái cho các phần tử
                verticalArrangement = Arrangement.spacedBy(2.dp) // Giảm khoảng cách giữa các dòng
            ) {
                // Year
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Năm phát hành: ",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.secondary2_color),
                        fontWeight = FontWeight.Bold,
                        fontFamily = Nunito_Bold,
                    )
                    Text(
                        text = year,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.secondary_color),
                        fontWeight = FontWeight.Normal,
                        fontFamily = Nunito_Bold,
                    )
                }

                // Genre
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Thể loại: ",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.secondary2_color),
                        fontWeight = FontWeight.Bold,
                        fontFamily = Nunito_Bold,
                    )
                    Text(
                        text = genre,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.secondary_color),
                        fontWeight = FontWeight.Normal,
                        fontFamily = Nunito_Bold,
                    )
                }

                // Duration
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Thời gian: ",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.secondary2_color),
                        fontWeight = FontWeight.Bold,
                        fontFamily = Nunito_Bold,
                    )
                    Text(
                        text = duration,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.secondary_color),
                        fontWeight = FontWeight.Normal,
                        fontFamily = Nunito_Bold,
                    )
                }

                // Rating
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Đánh giá: ",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.secondary2_color),
                        fontWeight = FontWeight.Bold,
                        fontFamily = Nunito_Bold,
                    )
                    Text(
                        text = rating,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.secondary_color),
                        fontWeight = FontWeight.Normal,
                        fontFamily = Nunito_Bold,
                    )
                }

                // Description
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Mô tả: ",
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.secondary2_color),
                        fontWeight = FontWeight.Bold,
                        fontFamily = Nunito_Bold,
                    )
                    Text(
                        text = description,
                        fontSize = 18.sp,
                        color = colorResource(id = R.color.secondary_color),
                        fontWeight = FontWeight.Normal,
                        fontFamily = Nunito_Bold,
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun PreviewMovieScreen2() {
    val navController = rememberNavController() // Khởi tạo navController giả
    MovieDetailScreen(
        navController = navController,
        title = "The Dark Knight",
        duration = "2h 32m",
        genre = "Action, Drama",
        year = "2008",
        rating = "9.0",
        description = "In Gotham City, the Joker emerges from his mysterious past to wreak havoc and chaos on the people of Gotham.",
        banner = "jdsdajd"
    )
}
