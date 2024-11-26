package fpl.md07.beeslearn.screens.podcast

import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.BackComponent
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.PodcastViewModel
import android.net.Uri
import android.widget.Toast
import android.content.ActivityNotFoundException
import android.util.Log
import androidx.compose.ui.graphics.ColorFilter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import fpl.md07.beeslearn.models.Podcast
import java.net.URLEncoder
import java.net.URLDecoder
import java.nio.charset.StandardCharsets

@Composable
fun PodcastDetailScreen(navController: NavController, podcastId: Int?) {
    val context = LocalContext.current
    val viewModel: PodcastViewModel = viewModel()
    val podcasts = viewModel.podcasts.value
    val loading = viewModel.loading.value
    val error = viewModel.error.value

    var isLiked by remember { mutableStateOf(false) }
    var isDisliked by remember { mutableStateOf(false) }
    var isShared by remember { mutableStateOf(false) }
    var isDownloaded by remember { mutableStateOf(false) }

    fun handleLike() {
        isLiked = !isLiked
    }

    fun handleDislike() {
        isDisliked = !isDisliked
    }

    fun handleShare() {
        isShared = true
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, "Check out this podcast!")
        }
        context.startActivity(Intent.createChooser(intent, "Share Podcast"))
    }

    fun handleDownload() {
        isDownloaded = true
        // tải xuống, sử dụng API download
        Toast.makeText(context, "Downloading...", Toast.LENGTH_SHORT).show()
    }

    fun openYouTube(podcast: Podcast) {

        try {
            val youtubeUrl = podcast.link_on_youtube
            Log.d("YouTube", "Opening URL: $youtubeUrl")
            val intent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(youtubeUrl)
                setPackage("com.google.android.youtube")  //app
            }

            try {
                context.startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                // trinh duyet
                val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl))
                context.startActivity(browserIntent)
            }
        } catch (e: Exception) {
            Toast.makeText(context, "Không thể mở YouTube", Toast.LENGTH_SHORT).show()
        }
    }

    if (loading) {
        Text("Loading...")
    } else if (error != null) {
        Text("Error: $error")
    } else {

        // Display podcast details based on ID
        val podcast = podcasts.firstOrNull { it.title.hashCode() == podcastId }
        if (podcast != null) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                BackComponent(navController)
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 70.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(24.dp)
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(model = podcast.image_url),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .graphicsLayer(alpha = 0.5f)
                            .aspectRatio(16 / 9f),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = rememberAsyncImagePainter(model = podcast.image_url),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                            .aspectRatio(16 / 9f),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = podcast.title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Nunito_Bold,
                    modifier = Modifier
                        .padding(top = 44.dp, start = 20.dp, end = 20.dp),
                    color = Color(0xff591429)
                )
                Box(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp, top = 25.dp)
                        .fillMaxWidth()
                        .height(1.dp)    // Chiều cao của đường line
                        .background(Color.Gray)  // Màu của đường line
                )
                Row(
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp, top = 15.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = podcast.duration,
                        fontFamily = Nunito_Bold,
                        fontSize = 16.sp,
                    )
                    Text(
                        text = podcast.duration,
                        fontFamily = Nunito_Bold,
                        fontSize = 16.sp,
                    )
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 40.dp, end = 40.dp, top = 30.dp)
                        .align(Alignment.CenterHorizontally),
                    horizontalArrangement = Arrangement.spacedBy(
                        40.dp,
                        Alignment.CenterHorizontally
                    )
                ) {
                    Image(
                        painter = painterResource(R.drawable.next_left_music),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)

                    )
                    Image(
                        painter = painterResource(R.drawable.play),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                podcast?.let { openYouTube(it) }
                            }
                    )
                    Image(
                        painter = painterResource(R.drawable.next_right_music),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)

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
                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.like), // Thay thế bằng ID ảnh thực tế
                            contentDescription = "Ảnh 1",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    handleLike()
                                }
                                .alpha(if (isLiked) 1f else 0.5f)
                                .graphicsLayer {
                                    scaleX = 1f
                                    scaleY = 1f
                                },
                            colorFilter = if (isLiked) ColorFilter.tint(Color.Red) else null // Chuyển màu khi Like
                        )

                        Image(
                            painter = painterResource(id = R.drawable.like),
                            contentDescription = "Ảnh 2",
                            modifier = Modifier
                                .size(25.dp)
                                .graphicsLayer(rotationZ = 180f)
                                .clickable {
                                    handleDislike()
                                }
                                .alpha(if (isDisliked) 1f else 0.5f),
                            colorFilter = if (isDisliked) ColorFilter.tint(Color.Black) else null
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
                                .clickable {
                                    handleShare()
                                }
                        )

                        Image(
                            painter = painterResource(id = R.drawable.down),
                            contentDescription = "Ảnh 4",
                            modifier = Modifier
                                .size(25.dp)
                                .clickable {
                                    handleDownload()
                                }
                        )
                    }
                }
                Text(
                    text = podcast.description,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = Nunito_Bold,
                    modifier = Modifier
                        .padding(start = 40.dp, end = 40.dp, top = 10.dp),
                    color = Color(0xff591429)
                )
            }

        }
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPodcastScreen2() {
    var navController = rememberNavController()
    val podcastId = 2// Khởi tạo giá trị giả định cho podcastId
    PodcastDetailScreen(navController, podcastId)
}