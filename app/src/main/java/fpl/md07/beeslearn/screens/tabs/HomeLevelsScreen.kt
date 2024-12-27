package fpl.md07.beeslearn.screens.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.components.BackComponentLevers
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.R
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape

fun hexagonShape() = GenericShape { size, _ ->
    val width = size.width
    val height = size.height
    val radius = width / 2

    moveTo(radius, 0f)
    lineTo(width, height / 4)
    lineTo(width, 3 * height / 4)
    lineTo(radius, height)
    lineTo(0f, 3 * height / 4)
    lineTo(0f, height / 4)
    close()
}


@Composable
fun HomeLeversScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
    ) {
        BackComponentLevers(navController)

        Spacer(modifier = Modifier.height(35.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "LV1",
                fontSize = 16.sp,
                color = Color.Black,
                fontFamily = Nunito_Bold,
                modifier = Modifier.padding(end = 8.dp)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(20.dp)
                    .background(
                        Color(0xFFE5E5E5),
                        shape = RoundedCornerShape(4.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.2f) // 20% progress
                        .background(
                            Color(0xFF8A1538),
                            shape = RoundedCornerShape(4.dp)
                        )
                )

                Text(
                    text = "40/200",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(color = (0xFFFFAA01)),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 4.dp)
                )
            }

            Text(
                text = "LV2",
                fontSize = 16.sp,
                color = Color.Black,
                fontFamily = Nunito_Bold,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(-25.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(
                listOf(
                    "Cấp độ 1",
                    "Cấp độ 2",
                    "Cấp độ 3",
                    "Cấp độ 4",
                    "Cấp độ 5"
                )
            ) { index, level ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(169.dp)
                        .height(199.dp)
                        .offset(x = if (index % 2 != 0) 170.dp else 20.dp)
                        .clip(hexagonShape())
                        .border(
                            width = 8.dp,
                            color = Color(0xFFFFAA01),
                            shape = hexagonShape()
                        )
                        .background(Color(0xFFFFF176))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                navController.navigate("selectExercise")
                            }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.beeds),
                            contentDescription = "Level Image",
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .aspectRatio(1f),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = level,
                            fontFamily = Nunito_Bold,
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeLeversScreen() {
    val navController = rememberNavController()
    HomeLeversScreen(navController)
}
