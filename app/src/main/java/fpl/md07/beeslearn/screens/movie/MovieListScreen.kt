package fpl.md07.beeslearn.screens.movie

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.BackComponent
import fpl.md07.beeslearn.models.Movie
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.MovieViewModel
//import fpl.md07.beeslearn.viewmodels.data.movieList
@Composable
fun MovieListScreen(navController: NavController, movieViewModel: MovieViewModel) {

    // Directly access the properties from ViewModel
    val movieList = movieViewModel.movieList.value
    val isLoading = movieViewModel.isLoading.value
    val errorMessage = movieViewModel.errorMessage.value

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
            .padding(top = 60.dp, start = 15.dp, end = 15.dp, bottom = 15.dp),
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
                modifier = Modifier.fillMaxSize()
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
                        text = "Movie",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF591429), // Màu nâu chữ
                            fontSize = 20.sp,
                            fontFamily = Nunito_Bold,
                        )
                    )
                    Text(
                        text = "Learning English\nby watching moview",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = Color(0xFF591429),
                            fontSize = 14.sp,
                            fontFamily = Nunito_Bold,
                        )
                    )
                }

                Image(
                    painter = painterResource(R.drawable.beetv),
                    contentDescription = "Bee with headphones",
                    modifier = Modifier
                        .width(117.dp)
                        .height(72.dp),
                    contentScale = ContentScale.Crop
                )
            }
        }
        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (errorMessage.isNotEmpty()) {
            // Show error message if there was an issue loading the movies
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = errorMessage, color = Color.Red)
            }
        } else {
//            Text(text = "Data loaded successfully!", color = Color.Green)
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(movieList.size) { index ->
                    MovieItem(movie = movieList[index], navController)
                }
            }
        }
    }
}

@Composable
fun MovieItem(movie: Movie, navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .width(200.dp)
                .height(250.dp)
                .clip(shape = RoundedCornerShape(8.dp))
                .clickable { navController.navigate("movieScreen2/${movie.title}/${movie.duration}/${movie.genre}/${movie.year}/${movie.rating}/${movie.description}") },
//            painter = painterResource(id = movie.imageResMovie),
            painter = painterResource(id = R.drawable.posterphim1),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        Text(
            text = movie.title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontFamily = Nunito_Bold,
            modifier = Modifier.padding(8.dp),
        )
    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreViewMovieScreen() {
    // Tạo một navController giả cho preview
    val navController = rememberNavController()

    // Tạo một MovieViewModel giả (mock) cho preview
    val movieViewModel = MovieViewModel()

    // Gọi MovieListScreen với cả navController và movieViewModel
    MovieListScreen(navController = navController, movieViewModel = movieViewModel)
}

