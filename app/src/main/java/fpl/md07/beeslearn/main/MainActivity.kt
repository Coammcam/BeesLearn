package fpl.md07.beeslearn.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.navigation.BottomNavBar
import fpl.md07.beeslearn.notifications.LessonViewModels
import fpl.md07.beeslearn.notifications.ReminderService
import fpl.md07.beeslearn.notifications.TimePreferences
import fpl.md07.beeslearn.screens.tabs.HomeScreen
import fpl.md07.beeslearn.screens.tabs.IPAExercise
import fpl.md07.beeslearn.screens.movie.MovieListScreen
import fpl.md07.beeslearn.screens.podcast.PodcastListScreen
import fpl.md07.beeslearn.screens.lessons.SelectLessonScreen
import fpl.md07.beeslearn.screens.music.MusicListScreen
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme
import fpl.md07.beeslearn.viewmodels.MovieViewModel

class MainActivity : ComponentActivity() {
    lateinit var timePreferences: TimePreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeesLearnTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val intent = Intent(this, ReminderService::class.java)
                    startService(intent)
                    timePreferences = TimePreferences(this)
                    timePreferences.resetDailyStatus()
                    timePreferences.checkAndResetDaily()

                    val navController = rememberNavController()
                    val movieViewModel: MovieViewModel = viewModel()
                    MainScreen(navController = navController, movieViewModel = movieViewModel) // Truyền movieViewModel vào MainScreen
                }
//                    WelcomeScreen(navController)
                }
            }
        }
    }


@Composable
fun MainScreen(navController : NavHostController, movieViewModel: MovieViewModel) {
    val navController = rememberNavController()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Chứa BottomNavBar và NavHost
        BottomNavBar(navController = navController,movieViewModel = movieViewModel)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController, movieViewModel: MovieViewModel) {
    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ) {

        composable("mainScreen") { MainScreen(navController = navController, movieViewModel = movieViewModel) }

        composable("homeScreen") { HomeScreen(navController = navController) }
        composable("ipaExercise") { IPAExercise( navController = navController) }
        composable("podcastScreen") { PodcastListScreen( navController = navController) }
        composable("movieScreen") {  MovieListScreen(navController = navController, movieViewModel = movieViewModel)}
        composable("musicScreen") { MusicListScreen (navController = navController) }
        composable("selectExercise") { SelectLessonScreen (navController = navController, lessonViewModel = LessonViewModels()) }

        composable("bottomNavBar") {  BottomNavBar(navController = navController, movieViewModel = movieViewModel)}
    }
}