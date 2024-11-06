package fpl.md07.beeslearn.screens

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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.models.Podcast
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.data.podcastList

@Composable
fun PodcastScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 10.dp, end = 10.dp, top = 20.dp, bottom = 20.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        // Back arrow
        Image(
            painter = painterResource(R.drawable.back_left),
            contentDescription = "Back",
            modifier = Modifier
                .padding(16.dp)
                .clickable { navController.popBackStack() }
        )

        Box(
            modifier = Modifier
                .padding(16.dp, bottom = 10.dp)
                .size(width = 276.dp, height = 151.dp)
                .shadow(24.dp, RoundedCornerShape(6.dp))
                .background(Color(0xFFFFF176))
                .align(Alignment.CenterHorizontally)
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = "Podcast",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            fontFamily = Nunito_Bold,
                            color = Color(0xFF591429), // Màu nâu chữ
                            fontSize = 20.sp
                        )
                    )
                    Text(
                        text = "excellent way for\nstudents to practice\nself-study",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF591429),
                            fontFamily = Nunito_Bold,
                            fontSize = 14.sp
                        )
                    )
                }

                Image(
                    painter = painterResource(R.drawable.headphone),
                    contentDescription = "Bee with headphones",
                    modifier = Modifier
                        .size(140.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 15.dp, top = 20.dp),
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                painter = painterResource(R.drawable.play),
                contentDescription = "Play",
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(28.dp)
            )
            Icon(
                painter = painterResource(R.drawable.heart1),
                contentDescription = "Play",
                modifier = Modifier
                    .padding(end = 10.dp, start = 10.dp)
                    .size(28.dp)
            )
            Icon(
                painter = painterResource(R.drawable.threedots),
                contentDescription = "Play",
                modifier = Modifier
                    .padding(end = 10.dp, start = 10.dp)
                    .size(28.dp)
            )
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(podcastList) { podcast ->
                PodcastItem(podcast, navController)
            }
        }
    }
}

@Composable
fun PodcastItem(podcast: Podcast, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {navController.navigate("podcastScreen2")},
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(podcast.imageRes),
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
                text = podcast.title,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                fontFamily = Nunito_Bold,
                maxLines = 1
            )
            Text(
                text = podcast.description,
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
                    text = podcast.view + " view",
                    fontWeight = FontWeight.W300,
                    fontFamily = Nunito_Bold,
                    fontSize = 10.sp
                )
                Text(
                    text = podcast.time,
                    fontWeight = FontWeight.W300,
                    fontSize = 10.sp,
                    fontFamily = Nunito_Bold,
                    modifier = Modifier
                        .padding(end = 50.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPodcastScreen() {
    var navController = rememberNavController()
    PodcastScreen(navController)
}