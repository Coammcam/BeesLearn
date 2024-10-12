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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
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
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import kotlin.math.cos
import kotlin.math.sin

class PracticeOneScreen : ComponentActivity() {
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
            .background(Color(android.graphics.Color.parseColor("#FFF192")), shape = RoundedCornerShape(16.dp)) // Nền màu từ mã màu HEX với góc bo tròn
            .border(BorderStroke(2.dp, Color.White), shape = RoundedCornerShape(16.dp)) // Viền với góc bo tròn
            .padding(40.dp) // Padding bên trong box
            .clip(RoundedCornerShape(16.dp)) // Cắt hình dạng với góc bo tròn
    ) {
        Text(
            text = "Đây là đoạn văn bản trong khung có viền và nền màu vàng.",
            color = Color.Black, // Màu chữ
            textAlign = TextAlign.Center // Canh giữa văn bản
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
fun BeeAnimationScreen1() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BouncingBeee(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 60.dp, end = 16.dp) // Thêm khoảng cách 50dp từ trên
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
fun Hexagon(
    radius: Dp,
    color: Color,
    isCenter: Boolean = false,
    onClick: () -> Unit
) {
    var isPressed by remember { mutableStateOf(false) }

    Box(modifier = Modifier.size(radius * 2)) {
        Canvas(
            modifier = Modifier
                .size(radius * 2)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { offset ->
                            val centerX = size.width / 2
                            val centerY = size.height / 2
                            val radiusPx = radius.toPx()
                            val angle = Math.PI / 3.0

                            val points = (0..5).map { i ->
                                Offset(
                                    (centerX + radiusPx * cos(angle * i - Math.PI / 6)).toFloat(),
                                    (centerY + radiusPx * sin(angle * i - Math.PI / 6)).toFloat()
                                )
                            }

                            if (isPointInPolygon(offset, points)) {
                                isPressed = true
                                tryAwaitRelease()
                                isPressed = false
                                onClick()
                            }
                        }
                    )
                }
        ) {
            val radiusPx = radius.toPx()
            val centerX = size.width / 2
            val centerY = size.height / 2
            val angle = Math.PI / 3.0

            // Hexagon path
            val hexPath = Path().apply {
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

            // Shadow hexagon (drawn slightly larger and shifted)
            val shadowOffset = 10f
            val shadowColor = Color(0x55000000)

            val shadowPath = Path().apply {
                moveTo(
                    (centerX + (radiusPx + shadowOffset) * cos(angle / 2)).toFloat(),
                    (centerY + (radiusPx + shadowOffset) * sin(angle / 2) + shadowOffset).toFloat()
                )

                for (i in 1..6) {
                    lineTo(
                        (centerX + (radiusPx + shadowOffset) * cos(angle * i + angle / 2)).toFloat(),
                        (centerY + (radiusPx + shadowOffset) * sin(angle * i + angle / 2) + shadowOffset).toFloat()
                    )
                }
                close()
            }

            // Draw shadow
            drawPath(
                path = shadowPath,
                color = shadowColor
            )

            // Draw hexagon
            drawPath(
                path = hexPath,
                color = if (isPressed) color.copy(alpha = 0.7f) else color
            )
        }

        if (isCenter) {
            Image(
                painter = painterResource(id = R.drawable.honey_picture), // Replace with your drawable
                contentDescription = null,
                modifier = Modifier
                    .size(radius * 0.8f) // Adjust size of the image relative to hexagon size
                    .align(Alignment.Center), // Center the image within the hexagon
            )
        }
    }
}

fun isPointInPolygon(point: Offset, vertices: List<Offset>): Boolean {
    var intersections = 0
    for (i in vertices.indices) {
        val a = vertices[i]
        val b = vertices[(i + 1) % vertices.size]

        if ((a.y > point.y) != (b.y > point.y) &&
            point.x < (b.x - a.x) * (point.y - a.y) / (b.y - a.y) + a.x
        ) {
            intersections++
        }
    }
    return intersections % 2 == 1
}

// Rest of the HexGrid code remains the same




@Preview
@Composable
fun PreviewBeeGameScreen() {
    BeeGameScreen()
}
