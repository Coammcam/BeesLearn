package fpl.md07.beeslearn.screens.music

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.widget.Toast
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.res.colorResource
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
import fpl.md07.beeslearn.models.Music
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.MusicViewModel
import fpl.md07.beeslearn.viewmodels.data.musicItem1

@Composable
fun MusicPlayerScreen(navController: NavController, musicId: Int?) {
    val context = LocalContext.current
    val viewModel: MusicViewModel = viewModel()
    val musics = viewModel.musics.value
    val loading = viewModel.loading.value
    val error = viewModel.error.value

    var currentMusicIndex by remember {
        mutableStateOf(musics.indexOfFirst { it.id == musicId.toString() })
    }
    fun navigateToNextPodcast() {
        if (currentMusicIndex < musics.size - 1) {
            currentMusicIndex++
            navController.navigate("musicScreen3/${musics[currentMusicIndex].id}") {
                popUpTo("musicScreen3/${musicId}") { inclusive = true }
            }
        }
    }

    fun navigateToPreviousPodcast() {
        if (currentMusicIndex > 0) {
            currentMusicIndex--
            navController.navigate("musicScreen3/${musics[currentMusicIndex].id}") {
                popUpTo("musicScreen3/${musicId}") { inclusive = true }
            }
        }
    }
    fun openYouTube(music: Music) {

        try {
            val youtubeUrl = music.youtubeUrl
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
        // Hiển thị chi tiết của podcast dựa trên id
        val music = musics.firstOrNull {
            it.id == musicId.toString()
        }

        if (music != null) {
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
                    .fillMaxHeight()
                    .padding(start = 20.dp, end = 20.dp, top = 70.dp, bottom = 20.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(24.dp)
                ) {
                    Image(
                        painter = painterResource(id = music.imageRes),
                        contentDescription = null,
                        modifier = Modifier.fillMaxWidth()
                            .graphicsLayer(alpha = 0.5f)
                            .aspectRatio(16 / 9f),
                        contentScale = ContentScale.Crop
                    )
                    Image(
                        painter = painterResource(id = music.imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, top = 15.dp)
                            .aspectRatio(16 / 9f),
                        contentScale = ContentScale.Crop
                    )
                }
                Text(
                    text = music.title,
                    fontSize = 35.sp,
                    color = colorResource(id = R.color.secondary_color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    fontWeight = FontWeight.Bold,
                    fontFamily = Nunito_Bold,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = music.author,
                    fontSize = 18.sp,
                    fontFamily = Nunito_Bold,
                    color = colorResource(id = R.color.secondary_color),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp),
                    textAlign = TextAlign.Center
                )
                Box(
                    modifier = Modifier
                        .padding(start = 30.dp, end = 30.dp, top = 10.dp)
                        .fillMaxWidth()  // Chiều rộng chiếm toàn bộ màn hình
                        .height(1.dp)    // Chiều cao của đường line
                        .background(Color.Gray)  // Màu của đường line
                )
                Row(
                    modifier = Modifier
                        .padding(start = 25.dp, end = 25.dp, top = 10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = music.time,
                        fontSize = 16.sp,
                        fontFamily = Nunito_Bold,
                        color = colorResource(id = R.color.secondary_color)
                    )
                    Text(
                        text = music.time,
                        fontSize = 16.sp,
                        fontFamily = Nunito_Bold,
                        color = colorResource(id = R.color.secondary_color),
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
                        modifier = Modifier.size(40.dp)
                            .clickable(
                                enabled = currentMusicIndex > 0,
                                onClick = { navigateToPreviousPodcast() }
                            )
                            .alpha(if (currentMusicIndex > 0) 1f else 0.5f)
                    )
                    Image(
                        painter = painterResource(R.drawable.play),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                            .clickable {
                                music?.let { openYouTube(it) }
                            }
                    )
                    Image(
                        painter = painterResource(R.drawable.next_right_music),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp)
                            .clickable(
                                enabled = currentMusicIndex < musics.size - 1,
                                onClick = { navigateToNextPodcast() }
                            )
                            .alpha(if (currentMusicIndex < musics.size - 1) 1f else 0.5f)
                    )
                }
                Text(
                    "Lyrics",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = Nunito_Bold,
                    modifier = Modifier
                        .padding(top = 44.dp, start = 20.dp, end = 20.dp)
                        .fillMaxWidth(),
                    color = colorResource(id = R.color.secondary_color),
                )
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(musicItem1) { music ->
                        Music3Item(music)
                    }
                }
            }
        }
    }
}
@Composable
fun Music3Item(music: Music) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp, start = 5.dp, end = 5.dp)
    ) {
        Text(
            text = music.description,
            fontSize = 16.sp,
            fontFamily = Nunito_Bold,
            color = colorResource(id = R.color.secondary_color),
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMusicScreen3() {
    var navController = rememberNavController()
    MusicPlayerScreen(navController, musicId = 2 )
}