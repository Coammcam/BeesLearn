package fpl.md07.beeslearn.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import fpl.md07.beeslearn.components.BeeAnimaComponent
import fpl.md07.beeslearn.components.TextBoxComponent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.TopBarComponent
import fpl.md07.beeslearn.components.customFont
import kotlin.math.cos
import kotlin.math.sin

//class SelectExScreen : ComponentActivity() { // Use ComponentActivity instead of AppCompatActivity
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            val navController = rememberNavController()
//            NavHost(navController, startDestination = "select_exercise") {
//                composable("select_exercise") { SelectExercise(navController) }
//                composable("practice_one_screen") { PracticeOneScreen() }
//            }
//        }
//    }
//}

@Composable
fun SelectExercise(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar with hearts and coins
        TopBar(navController)

        Spacer(modifier = Modifier.height(16.dp))

        Box(
        ) {
            TextBoxComponent(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp)
            )
            BeeAnimaComponent(
                modifier = Modifier
                    .align(Alignment.TopEnd)
            )
        }
        Spacer(modifier = Modifier.height(200.dp))

        HexGridd(navController)
    }
}
@Composable
fun HexGridd(navController: NavController) {
    val hexagonRadius = 40.dp
    val hexagonCount = 10
    // Tạo danh sách trạng thái cho các hexagon
    val hexagonStates = remember { mutableStateListOf(*Array(hexagonCount) { false }) } // Sử dụng mutableStateListOf

    // Scrollable row to hold hexagons
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..10) {
            // Draw the hexagon with number
            HexagonWithNumber(radius = hexagonRadius, number = i) {
                navController.navigate("practiceOneScreen")
            }

            // Draw a connecting line (except for the last hexagon)
            if (i < hexagonCount - 1) {
                Spacer(modifier = Modifier.width(8.dp))
                HorizontalDivider(
                    modifier = Modifier
                        .width(100.dp)
                        .height(2.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp)) // Adjust space after line
            }
        }
    }
}

@Composable
fun HexagonWithNumber(radius: Dp, number: Int, onClick: () -> Unit, ) {
    val outerColor = colorResource(id = R.color.outerColor_hexagon)
    val innerColor = colorResource(id = R.color.innerColor_hexagon)

    // Màu sắc mới khi hexagon được nhấn
    val newOuterColor = colorResource(id = R.color.newOuterColor)
    val newInnerColor = colorResource(id = R.color.newInnerColor)

    Box(
        modifier = Modifier
            .size(radius * 2)
            .clickable { onClick() } // Xử lý nhấp chuột
    ) {
        Canvas(
            modifier = Modifier
                .size(radius * 2)
//                .clickable { navController.navigate("")}
        ) {
            val radiusPx = radius.toPx()
            val centerX = size.width / 2
            val centerY = size.height / 2
            val angle = Math.PI / 3.0

            // Outer hexagon path
            val hexPathOuter = Path().apply {
                moveTo(
                    (centerX + radiusPx * cos(angle / 2)).toFloat(),
                    (centerY + radiusPx * sin(angle / 2)).toFloat()
                )
                for (i in 1..6) {
                    lineTo(
                        (centerX + radiusPx * cos(angle * i + angle / 2)).toFloat(),
                        (centerY + radiusPx * sin(angle * i + angle / 2)).toFloat()
                    )
                }
                close()
            }

            // Inner hexagon path
            val innerRadiusPx = radiusPx * 0.7f
            val hexPathInner = Path().apply {
                moveTo(
                    (centerX + innerRadiusPx * cos(angle / 2)).toFloat(),
                    (centerY + innerRadiusPx * sin(angle / 2)).toFloat()
                )
                for (i in 1..6) {
                    lineTo(
                        (centerX + innerRadiusPx * cos(angle * i + angle / 2)).toFloat(),
                        (centerY + innerRadiusPx * sin(angle * i + angle / 2)).toFloat()
                    )
                }
                close()
            }

            // Vẽ hexagon bên ngoài với màu sắc mới nếu đã nhấn
            drawPath(
                path = hexPathOuter,
                color = if (isClicked) newOuterColor else outerColor
            )

            // Vẽ hexagon bên trong với màu sắc mới nếu đã nhấn
            drawPath(
                path = hexPathInner,
                color = if (isClicked) newInnerColor else innerColor
            )
        }

        // Thêm số vào giữa hexagon
        Text(
            text = number.toString(),
            modifier = Modifier.align(Alignment.Center),
            fontSize = 22.sp,
            fontFamily = customFont,
            color = colorResource(id = R.color.secondary_color)
        )
    }
}
@Preview
@Composable
fun PreviewSelectExercise() {
    val mockNavController = rememberNavController()
    SelectExercise(navController = mockNavController)
}