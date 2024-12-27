package fpl.md07.beeslearn.screens.onboard

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.CustomButtonWithText
import fpl.md07.beeslearn.notifications.LessonViewModels
import fpl.md07.beeslearn.ui.theme.Nunito_Bold


@Composable
fun FrequencyScreen(navController: NavController, lessonViewModel: LessonViewModels) {
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        lessonViewModel.initialize(context)
    }

    fun handleTimeSelection(selectedTime: Int) {
        lessonViewModel.setSelectedTime(selectedTime)
        Toast.makeText(context, "Chúc Bạn Học Tập Vui Vẻ với $selectedTime phút!", Toast.LENGTH_SHORT).show()
        navController.navigate("HomeScreen")
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.primary_color)),
    ) {
        Column {
            Spacer(modifier = Modifier.height(100.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painterResource(id = R.drawable.logo_without_text),
                    contentDescription = "Placeholder",
                    modifier = Modifier
                        .width(170.dp)
                        .height(210.dp)
                )

                Box(
                    modifier = Modifier
                        .width(230.dp)
                        .height(130.dp)
                ) {
                    Image(
                        painterResource(id = R.drawable.cloudshape),
                        contentDescription = "Placeholder",
                        modifier = Modifier.fillMaxSize()
                    )

                    Text(
                        text = "Số thời gian \nhọc trong ngày",
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(10.dp),
                        color = Color.Black,
                        fontFamily = Nunito_Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(55.dp))

            val context = LocalContext.current

            CustomButtonWithText(
//                onClick = { navController.navigate("HomeScreen")
//                    Toast.makeText(context, "Chúc Bạn Học Tập Vui Vẻ !", Toast.LENGTH_SHORT).show()},
                onClick = { handleTimeSelection(1) },
                buttonText = "1’ mỗi ngày"
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomButtonWithText(
   //             onClick = { navController.navigate("HomeScreen")
   //                 Toast.makeText(context, "Chúc Bạn Học Tập Vui Vẻ !", Toast.LENGTH_SHORT).show()},
                onClick = { handleTimeSelection(2) },
                buttonText = "2’ mỗi ngày"
            )

            Spacer(modifier = Modifier.height(30.dp))

            CustomButtonWithText(
     //          onClick = { navController.navigate("HomeScreen")
     //               Toast.makeText(context, "Chúc Bạn Học Tập Vui Vẻ !", Toast.LENGTH_SHORT).show()},
                onClick = { handleTimeSelection(3) },
                buttonText = "3’ mỗi ngày"
            )

        }
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
private fun TanSuatScreenPreview() {
    var navController = rememberNavController()
    FrequencyScreen(navController = navController, lessonViewModel = LessonViewModels())
}