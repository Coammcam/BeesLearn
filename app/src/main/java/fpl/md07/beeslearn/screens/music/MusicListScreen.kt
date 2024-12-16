package fpl.md07.beeslearn.screens.music

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.draw.shadow
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
                .padding(16.dp, bottom = 10.dp, end = 16.dp)
                .size(width = 276.dp, height = 151.dp)
                .shadow(18.dp, RoundedCornerShape(6.dp))
                .background(Color(0xFFFFF176))
                .align(Alignment.CenterHorizontally)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(10.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Âm nhạc",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF591429), // Màu nâu chữ
                            fontSize = 20.sp,
                            fontFamily = Nunito_Bold,
                        )
                    )
                    Text(
                        text = "Học tiếng anh\nvới các bài hát",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF591429),
                            fontSize = 14.sp,
                            fontFamily = Nunito_Bold,
                        )
                    )
                }


                Image(
                    painter = painterResource(R.drawable.bee_wink),
                    contentDescription = "Bee with headphones",
                    modifier = Modifier
                        .width(117.dp)
                        .height(72.dp),
                    contentScale = ContentScale.Crop
                )
            }
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
                    verticalArrangement = Arrangement.spacedBy(10.dp)
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
            .padding(start = 20.dp, top = 10.dp)
            .clickable { navController.navigate("musicDetail/${music.title.hashCode()}") },
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
                color = colorResource(id = R.color.secondary_color),
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                fontFamily = Nunito_Bold,
                maxLines = 1,
                modifier = Modifier
                    .padding(top = 10.dp)
            )
            Text(
                text = music.description,
                fontSize = 14.sp,
                fontFamily = Nunito_Bold,
                color = colorResource(id = R.color.secondary2_color),
                maxLines = 2,
                fontWeight = FontWeight.Bold
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 18.dp),
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