package fpl.md07.beeslearn.screens.movie


import YoutubeMoviePlayerScreen
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.BackComponent
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.MovieViewModel


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
    trailer: String?,
    movieViewModel: MovieViewModel = viewModel ()
) {


    var showYouTubeMoviePlayer by remember { mutableStateOf(false) }
    var videoMovieId: String? by remember { mutableStateOf(null) }


    fun extractVideoIdFromUrl(url: String): String {
        val uri = Uri.parse(url)
        return uri.getQueryParameter("v") ?: uri.lastPathSegment ?: ""
    }


    fun startPlaying() {
        trailer?.let {
            videoMovieId = extractVideoIdFromUrl(it)
            if (videoMovieId != null) {
                showYouTubeMoviePlayer = true
            } else {
                // Handle invalid video ID
                println("Invalid video ID")
            }
        }
    }
    if (movieViewModel.isLoading.value) {
        // Display the loading spinner
        Box(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
        ) {
            CircularProgressIndicator()
        }
    } else {
        // Show a loading spinner while data is being loaded
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
            val bannerPainter = rememberImagePainter(data = banner)


            if (showYouTubeMoviePlayer && videoMovieId != null) {
                // Display the YouTube player screen when play is clicked
                YoutubeMoviePlayerScreen(videoMovieId = videoMovieId!!)
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(24.dp)
                ) {
                    Image(
                        painter = bannerPainter,
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    Image(
                        painter = bannerPainter,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 10.dp),
                    )
                }
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
                    .fillMaxWidth()
            ) {
                Button(
                    onClick = { startPlaying() },
                    modifier = Modifier
                        .padding(16.dp)
                        .height(56.dp)
                        .width(179.dp)
                        .clip(RoundedCornerShape(28.dp))
                        .align(Alignment.TopCenter), // Center the button
                    colors = ButtonDefaults.buttonColors(Color(0xFF591429))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.play2), // Replace with your play icon
                        contentDescription = "Play",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                    )


                    Spacer(modifier = Modifier.width(14.dp))


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
                    .padding(top = 10.dp, start = 15.dp, end = 15.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(2.dp)
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

