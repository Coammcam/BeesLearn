package fpl.md07.beeslearn.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import kotlin.math.cos
import kotlin.math.sin

class BeeGameActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    BeeGameScreen() // Call your Compose screen here
                    BeeAnimationScreen1()
                }
            }
        }
    }
}

@Composable
fun BeeGameScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar with hearts and coins
        TopBar()

        Spacer(modifier = Modifier.height(16.dp))

        // Input box
        InputBox()

        Spacer(modifier = Modifier.height(16.dp))

        // Bee and hexagonal grid
        BeeAndHexGrid()
    }
}

@Composable
fun TopBar() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back), // replace with actual back arrow resource
            contentDescription = "Back",
            modifier = Modifier.size(24.dp)
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("5", fontSize = 20.sp, color = Color.Red)
            Icon(
                painter = painterResource(id = R.drawable.ic_heart), // replace with heart icon resource
                contentDescription = "Hearts",
                tint = Color.Red,
                modifier = Modifier.size(24.dp)
            )

            Spacer(modifier = Modifier.width(16.dp))

            Text("100", fontSize = 20.sp, color = Color.Yellow)
            Icon(
                painter = painterResource(id = R.drawable.ic_coin), // replace with coin icon resource
                contentDescription = "Coins",
                tint = Color.Yellow,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
fun InputBox() {

    Spacer(modifier = Modifier.height(50.dp))

        Box(
            modifier = Modifier
                .padding(16.dp)
                .background(Color(android.graphics.Color.parseColor("#FFF192"))) // Nền màu từ mã màu HEX
                .padding(40.dp) // Padding bên trong box
                .clip(RoundedCornerShape(30.dp))

        ) {
            Text(
                text = "Đây là đoạn văn bản trong khung có viền và nền màu vàng.",
                color = Color.Black // Màu chữ
            )
        }
    }


@Composable
fun BeeAndHexGrid() {
    Box(
        contentAlignment = Alignment.TopEnd,
        modifier = Modifier.fillMaxWidth()
    ) {
//        Image(
//            painter = painterResource(id = R.drawable.picture_ani), // replace with bee drawable
//            contentDescription = "Bee",
//            contentScale = ContentScale.Fit,
//            modifier = Modifier.size(80.dp)
//        )
    }

    Spacer(modifier = Modifier.height(50.dp))

    // Hexagonal grid with center hexagon and surrounding ones
    HexGrid()
}

// animation
@Composable
fun BouncingBeee(modifier: Modifier = Modifier) {
    val infiniteTransition = rememberInfiniteTransition()

    val verticalOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 30f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val horizontalOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 15f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val rotation by infiniteTransition.animateFloat(
        initialValue = -5f,
        targetValue = 5f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 500, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )


    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.ani1_picture), // Replace with your bee image resource
            contentDescription = "Bouncing Bee",
            modifier = Modifier
                .size(100.dp)
                .offset(x = horizontalOffset.dp, y = -verticalOffset.dp)
                .rotate(rotation)
        )
    }
}

@Composable
fun HexGrid() {
    // State for the color of each hexagon
    val hexagonColors = remember {
        mutableStateListOf(
            Color.Gray,
            Color.Gray,
            Color.Gray,
            Color.Gray,
            Color.Gray,
            Color.Gray,
            Color.Gray
        )
    }

    // Define the hexagon radius (size)
    val hexagonRadius = 40.dp

    // Define spacing between hexagons (adjust based on the hexagon size)
    val spacing = hexagonRadius * 0.1f

    // Create the grid structure with hexagons arranged around a central one
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(spacing * 0.5f)
        ) {

            // Row 2: Two hexagons
            Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
                Hexagon(hexagonRadius, hexagonColors[0]) {
                    hexagonColors[0] =
                        if (hexagonColors[0] == Color.Gray) Color.Yellow else Color.Gray
                }
                Hexagon(hexagonRadius, hexagonColors[1]) {
                    hexagonColors[1] =
                        if (hexagonColors[1] == Color.Gray) Color.Yellow else Color.Gray
                }
            }

            // Row 3: Three hexagons including center
            Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
                Hexagon(hexagonRadius, hexagonColors[2]) {
                    hexagonColors[2] =
                        if (hexagonColors[2] == Color.Gray) Color.Yellow else Color.Gray
                }
                Hexagon(
                    hexagonRadius, hexagonColors[3], isCenter = true
                ) {
                    hexagonColors[3] =
                        if (hexagonColors[3] == Color.Gray) Color.Yellow else Color.Gray
                }
                Hexagon(hexagonRadius, hexagonColors[4]) {
                    hexagonColors[4] =
                        if (hexagonColors[4] == Color.Gray) Color.Yellow else Color.Gray
                }
            }


            // Row 3: Two hexagons
            Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
                Hexagon(hexagonRadius, hexagonColors[5]) {
                    hexagonColors[5] =
                        if (hexagonColors[5] == Color.Gray) Color.Yellow else Color.Gray
                }
                Hexagon(hexagonRadius, hexagonColors[6]) {
                    hexagonColors[6] =
                        if (hexagonColors[6] == Color.Gray) Color.Yellow else Color.Gray
                }
            }
        }
    }
}

@Composable
fun BeeAnimationScreen1() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BouncingBeee(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp)
        )
    }
}

@Composable
fun Hexagon(
    radius: Dp,
    color: Color,
    isCenter: Boolean = false,
    onClick: () -> Unit
) {
    // Draw the hexagonal shape using Canvas
    Canvas(
        modifier = Modifier
            .size(radius * 2) // Hexagon diameter
            .clickable { onClick() } // Handle click events
            .background(Color.Transparent)
    ) {
        val hexPath = Path().apply {
            val radiusPx = radius.toPx()
            val centerX = size.width / 2
            val centerY = size.height / 2
            val angle = Math.PI / 3.0 // 60 degrees for hexagon angles

            // Move to the first point (rotated by 30 degrees)
            moveTo(
                (centerX + radiusPx * cos(angle / 2)).toFloat(),
                (centerY + radiusPx * sin(angle / 2)).toFloat()
            )

            // Draw the hexagon path
            for (i in 1..6) {
                lineTo(
                    (centerX + radiusPx * cos(angle * i + angle / 2)).toFloat(),
                    (centerY + radiusPx * sin(angle * i + angle / 2)).toFloat()
                )
            }
            close()
        }

        // Draw the hexagon with the chosen color
        drawPath(
            path = hexPath,
            color = color // Use color as background
        )

        // Add icon to the center hexagon
        if (isCenter) {
            drawCircle(
                color = Color.White,
                radius = radius.toPx() * 0.4f,
                center = Offset(size.width / 2, size.height / 2)
            )
        }
    }
}

@Preview
@Composable
fun PreviewBeeGameScreen() {
    BeeGameScreen()
}
