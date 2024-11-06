package fpl.md07.beeslearn.main

import android.net.wifi.hotspot2.pps.HomeSp
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.models.Podcast
import fpl.md07.beeslearn.navigation.BottomNavBar
import fpl.md07.beeslearn.screens.HomeScreen
import fpl.md07.beeslearn.screens.IPAExercise
import fpl.md07.beeslearn.screens.IPAScreenPreview
import fpl.md07.beeslearn.screens.LoginScreen
import fpl.md07.beeslearn.screens.MovieScreen
import fpl.md07.beeslearn.screens.MovieScreen2
import fpl.md07.beeslearn.screens.MusicScreen
import fpl.md07.beeslearn.screens.MusicScreen2
import fpl.md07.beeslearn.screens.MusicScreen3
import fpl.md07.beeslearn.screens.PodcastScreen
import fpl.md07.beeslearn.screens.PodcastScreen2
import fpl.md07.beeslearn.screens.SelectExercise
import fpl.md07.beeslearn.screens.WelcomeScreen
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BeesLearnTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    BottomNavBar (navController = navController)
//                    WelcomeScreen(navController)
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController : NavHostController) {
    val navController = rememberNavController()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Chứa BottomNavBar và NavHost
        BottomNavBar(navController = navController)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = "mainScreen"
    ) {

        composable("mainScreen") { MainScreen(navController = navController) }

        composable("homeScreen") { HomeScreen(navController = navController) }
        composable("ipaExercise") { IPAExercise( navController = navController) }
        composable("podcastScreen") {PodcastScreen( navController = navController)}
        composable("movieScreen") {MovieScreen (navController = navController)}
        composable("musicScreen") {MusicScreen (navController = navController)}
        composable("selectExercise") {SelectExercise (navController = navController)}

        composable("bottomNavBar") { BottomNavBar(navController = navController) }
    }
}