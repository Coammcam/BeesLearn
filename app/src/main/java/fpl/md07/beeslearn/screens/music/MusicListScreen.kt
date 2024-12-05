package fpl.md07.beeslearn.screens.music

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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
import coil.compose.AsyncImage
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.BackComponent
import fpl.md07.beeslearn.models.Music
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.MusicViewModel

@Composable
fun MusicListScreen(
    navController: NavController,
    musicViewModel: MusicViewModel= viewModel()
) {
    val musics by musicViewModel.musics
    val loading by musicViewModel.loading
    val error by musicViewModel.error

    Column (
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
            .padding(start = 10.dp, end = 10.dp, top = 70.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .size(width = 320.dp, height = 190.dp)
                .align(Alignment.CenterHorizontally)
        ) {
            Image(
                painter = painterResource(R.drawable.music1),
                contentDescription = null,
                modifier = Modifier
                    .clip(RoundedCornerShape(24.dp))
                    .fillMaxSize()
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, start = 15.dp, top = 18.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(R.drawable.play),
                contentDescription = "Play",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(35.dp),
            )
            Icon(
                painter = painterResource(R.drawable.heart1),
                contentDescription = "Play",
                modifier = Modifier
                    .padding(end = 10.dp, start = 10.dp)
                    .size(35.dp)
            )
        }
        when {
            loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            error != null -> {
                Text(
                    text = "Error: $error",
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }

            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    items(musics) { music ->
                        MusicItem(music, navController)
                    }
                }
            }
        }

    }
}

@Composable
fun MusicItem (music: Music, navController: NavController) {

    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 30.dp, top = 10.dp)
            .clickable {navController.navigate("musicDetail/${music.title.hashCode()}")},
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = music.image_url,
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))

        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = music.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = Nunito_Bold,
                maxLines = 1
            )
            Text(
                text = music.description,
                fontSize = 14.sp,
                fontFamily = Nunito_Bold,
                color = Color.Gray,
                maxLines = 2
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = music.duration,
                    fontWeight = FontWeight.W300,
                    fontFamily = Nunito_Bold,
                    fontSize = 10.sp
                )

            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMusicScreen2() {
    var navController = rememberNavController()
    MusicListScreen(navController)
}