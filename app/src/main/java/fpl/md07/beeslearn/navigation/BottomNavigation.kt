package fpl.md07.beeslearn.navigation

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fpl.md07.beeslearn.screens.auth.InputOTPScreen
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.api.AppInfo.APP_ID
import fpl.md07.beeslearn.components.HomePageComponent
import fpl.md07.beeslearn.components.PaymentComponent
import fpl.md07.beeslearn.screens.auth.ChangePasswordScreen
import fpl.md07.beeslearn.screens.onboard.ChooseLanguagesScreen
import fpl.md07.beeslearn.screens.tabs.HomeScreen
import fpl.md07.beeslearn.screens.tabs.IPAExercise
import fpl.md07.beeslearn.screens.auth.LoginScreen
import fpl.md07.beeslearn.screens.movie.MovieListScreen
import fpl.md07.beeslearn.screens.movie.MovieDetailScreen
import fpl.md07.beeslearn.screens.music.MusicDetailScreen
import fpl.md07.beeslearn.screens.podcast.PodcastListScreen
import fpl.md07.beeslearn.screens.podcast.PodcastDetailScreen
import fpl.md07.beeslearn.screens.lessons.LessonScreen
import fpl.md07.beeslearn.screens.auth.ForgotPasswordScreen
import fpl.md07.beeslearn.screens.lessons.SelectLessonScreen
import fpl.md07.beeslearn.screens.tabs.SettingScreen
import fpl.md07.beeslearn.screens.auth.SignUpScreen
import fpl.md07.beeslearn.screens.tabs.TranslateScreen
import fpl.md07.beeslearn.screens.onboard.FrequencyScreen
import fpl.md07.beeslearn.screens.onboard.SelectLevelScreen
import fpl.md07.beeslearn.screens.auth.ChooseLoginScreen
import fpl.md07.beeslearn.screens.music.MusicListScreen
import fpl.md07.beeslearn.screens.tabs.EditProfile
import fpl.md07.beeslearn.screens.tabs.HomeLeversScreen
import fpl.md07.beeslearn.screens.tabs.VideoScreen
import fpl.md07.beeslearn.viewmodels.MovieViewModel
import vn.zalopay.sdk.Environment
import vn.zalopay.sdk.ZaloPaySDK

data class TabItem(
    val unselectedIcon: Int,
    val selectedIcon: Int,
    val content: @Composable (NavController) -> Unit,
    val screenName: String,
)

val tabItems = listOf(
    TabItem(
        unselectedIcon = R.drawable.home_icon,
        selectedIcon = R.drawable.hone_icon_dark,
        content = { navController -> HomeScreen(navController) },
        screenName = "HomeScreen"
    ),
    TabItem(
        unselectedIcon = R.drawable.ipa_icon,
        selectedIcon = R.drawable.ipa_icon_dark,
        content = { navController -> IPAExercise(navController) },
        screenName = "IPAExercise"
    ),
    TabItem(
        unselectedIcon = R.drawable.translatecnn,
        selectedIcon = R.drawable.translate,
        content = { navController -> TranslateScreen(navController) },
        screenName = "statsScreen"
    ),
    TabItem(
        unselectedIcon = R.drawable.setting_icon,
        selectedIcon = R.drawable.setting_icon_dark,
        content = { navController -> SettingScreen(navController) },
        screenName = "settingScreen"
    )
)

@Composable
fun BottomNavBar(navController: NavController, movieViewModel: MovieViewModel) {
    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(modifier = Modifier.fillMaxSize(),
            bottomBar = { TabView(tabItems, navController = navController) }) {
            Box(modifier = Modifier.padding(it)) {
                NestedBottomTab(
                    navController = navController as NavHostController,
                    movieViewModel = movieViewModel
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun NestedBottomTab(
    navController: NavHostController,
    movieViewModel: MovieViewModel,
) {

    NavHost(
        navController, "welcomeScreen"

    ) {
        //dinh nghia man hinh
        composable("HomeScreen") {
            HomeScreen(navController)
        }
        composable("IPAExercise") {
            IPAExercise(navController)
        }
        composable("statsScreen") {
            TranslateScreen(navController)
        }
        composable("settingScreen") {
            SettingScreen(navController)
        }
        composable("selectExercise") {
            SelectLessonScreen(navController)
        }
        composable("podcastScreen") {
            PodcastListScreen(navController)
        }
        composable("movieScreen") {
            MovieListScreen(navController = navController, movieViewModel = movieViewModel)
        }
        composable(
            route = "podcastDetail/{podcastId}",
            arguments = listOf(navArgument("podcastId") { type = NavType.IntType })
        ) { backStackEntry ->
            val podcastId = backStackEntry.arguments?.getInt("podcastId")
            PodcastDetailScreen(navController, podcastId)
        }


        composable(
            "movieScreen2/{title}/{duration}/{genre}/{year}/{rating}/{description}/{banner}/{trailer}",
            arguments = listOf(
                navArgument("title") { type = NavType.StringType },
                navArgument("duration") { type = NavType.StringType },
                navArgument("genre") { type = NavType.StringType },
                navArgument("year") { type = NavType.StringType },
                navArgument("rating") { type = NavType.StringType },
                navArgument("description") { type = NavType.StringType },
                navArgument("banner") { type = NavType.StringType },
                navArgument("trailer") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            MovieDetailScreen(
                navController = navController,
                title = backStackEntry.arguments?.getString("title") ?: "",
                duration = backStackEntry.arguments?.getString("duration") ?: "",
                genre = backStackEntry.arguments?.getString("genre") ?: "",
                year = backStackEntry.arguments?.getString("year") ?: "",
                rating = backStackEntry.arguments?.getString("rating") ?: "",
                description = backStackEntry.arguments?.getString("description") ?: "",
                banner = backStackEntry.arguments?.getString("banner") ?: "",
                trailer = backStackEntry.arguments?.getString("trailer") ?: ""
            )
        }

        composable("musicScreen") {
            MusicListScreen(navController)
        }

        composable(
            "musicDetail/{musicId}",
            arguments = listOf(navArgument("musicId") { type = NavType.IntType })
        ) { backStackEntry ->
            val musicId = backStackEntry.arguments?.getInt("musicId")
            MusicDetailScreen(navController, musicId)
        }
        composable("practiceOneScreen") {
            LessonScreen(navController)
        }
        composable("welcomeScreen") {
            ChooseLoginScreen(navController)
        }
        composable("signUpScreen") {
            SignUpScreen(navController)
        }
        composable("loginScreen") {
            LoginScreen(navController)
        }
        composable("quenMatKhauScreen") {
            ForgotPasswordScreen(navController)
        }
        composable("nhapMaCodeScreen") {
            InputOTPScreen(navController)
        }
        composable("chaoHoiScreen") {
            ChooseLanguagesScreen(navController)
        }
        composable("trinhDoScreen") {
            SelectLevelScreen(navController)
        }
        composable("tanSuatScreen") {
            FrequencyScreen(navController)
        }
        composable("editProfile") {
            EditProfile(navController)
        }
        composable("changePasswordScreen") {
            ChangePasswordScreen(navController)
        }


        // question list
        composable("questionList") {
            NavHost(
                navController = navController,
                startDestination = "multipleChoice"
            ) {
//                addQuestionNavGraph(navController)
            }
        }

        composable("videoScreen/{videoResId}") { backStackEntry ->
            val videoResId = backStackEntry.arguments?.getString("videoResId")?.toIntOrNull() ?: 0
            VideoScreen(navController = navController, videoResId = videoResId)
        }
        composable("homeComponents") {
            HomePageComponent (navController)
        }
        composable("paymentComponent") {
            PaymentComponent(navController)
        }
        composable("homeLeversScreen") {
            HomeLeversScreen(navController)
        }
    }
}

@Composable
fun TabView(tabBarItems: List<TabItem>, navController: NavController) {
    var selectedTabIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val navBackStack by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStack?.destination

    val bottomBarDestination = tabBarItems.any() {
        it.screenName == currentDestination?.route
    }
    if (bottomBarDestination) {
        NavigationBar(containerColor = Color.White) {
            tabBarItems.forEachIndexed { index, tabBarItem ->
                NavigationBarItem(
                    selected = selectedTabIndex == index,
                    onClick = {
                        navController.navigate(tabBarItem.screenName)
                        selectedTabIndex = index
                    },
                    icon = {
                        TabBarIconView(
                            icon = tabBarItem.selectedIcon,
                            isFocused = selectedTabIndex == index
                        )
                    },
                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.Black,
                        unselectedIconColor = Color.Gray,
                        indicatorColor = Color.White
                    )
                )
            }
        }
    }
}

// This component helps to clean up the API call from our TabView above,
// but could just as easily be added inside the TabView without creating this custom component

@Composable
fun TabBarIconView(
    icon: Int,
    badgeAmount: Int? = null,
    isFocused: Boolean,
) {
    BadgedBox(badge = { TabBarBadgeView(badgeAmount) }) {
//        val color = if (isFocused) Color.Blue else Color.DarkGray
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            modifier = Modifier.size(25.dp)
//                .background(
//                    color
//                )
        )
    }
}

// This component helps to clean up the API call from our TabBarIconView above,
// but could just as easily be added inside the TabBarIconView without creating this custom component
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun TabBarBadgeView(count: Int? = null) {
    if (count != null) {
        Badge {
            Text(count.toString())
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewMusicScreen() {
    // Tạo navController giả cho Preview
    val navController = rememberNavController()

    // Tạo movieViewModel giả
    val movieViewModel = MovieViewModel()

    // Gọi MovieListScreen với cả navController và movieViewModel
    MovieListScreen(navController = navController, movieViewModel = movieViewModel)
}