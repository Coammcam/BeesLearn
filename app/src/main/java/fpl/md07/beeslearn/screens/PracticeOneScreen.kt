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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.BackComponent
import fpl.md07.beeslearn.components.IconTopComponent
import kotlin.math.cos
import kotlin.math.sin
import fpl.md07.beeslearn.components.TextBoxComponent
import fpl.md07.beeslearn.components.BeeAnimaComponent
class PracticeOneScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface {
                    BeeGameScreen() // Call your Compose screen here

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


        Box(modifier = Modifier.padding(10.dp)) { // Adjust padding as needed
            // Use the Bee Animation Component at the top left
            TextBoxComponent(
                modifier = Modifier
                    .fillMaxWidth() // Fill the width of the parent
                    .height(200.dp) // Set a specific height for the text box
                    .padding(top = 50.dp) // Adjust padding to avoid overlap if needed
            )
            BeeAnimaComponent(
                modifier = Modifier
                    .align(Alignment.TopEnd) // Align to top end (right corner)
                    .size(200.dp) // Increase the size of the bee image
                    .padding(top = 10.dp, start = 100.dp) // Optional padding from the top and left

            )
        }
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

        BackComponent()
        IconTopComponent()
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


@Composable
fun HexGrid() {
    // State for the color of each hexagon using a List of mutableStateOf
    val hexagonColors = remember {
        mutableStateListOf(
            mutableStateOf(Color(android.graphics.Color.parseColor("#C8C8C8"))), // Outer color default
            mutableStateOf(Color(android.graphics.Color.parseColor("#C8C8C8"))), // Outer color default
            mutableStateOf(Color(0xFFD3D3D3)), // Outer color default
            mutableStateOf(Color(0xFFD3D3D3)), // Outer color default
            mutableStateOf(Color(0xFFD3D3D3)), // Outer color default
            mutableStateOf(Color(0xFFD3D3D3)), // Outer color default
            mutableStateOf(Color(0xFFD3D3D3))  // Outer color default
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

            // Row 2: Two outer hexagons
            Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
                Hexagon(hexagonRadius, hexagonColors[0], R.drawable.icon_lock) { // Outer image
                    toggleHexagonColor(hexagonColors[0])
                }
                Hexagon(hexagonRadius, hexagonColors[1], R.drawable.icon_lock) { // Outer image
                    toggleHexagonColor(hexagonColors[1])
                }
            }

            // Row 3: Three hexagons including center
            Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
                Hexagon(hexagonRadius, hexagonColors[2], R.drawable.icon_lock) { // Outer image
                    toggleHexagonColor(hexagonColors[2])
                }
                Hexagon(
                    hexagonRadius,
                    hexagonColors[3],
                    R.drawable.honey_picture,
                    isCenter = true
                ) { // Inner image (center)
                    toggleHexagonColor(hexagonColors[3])
                }
                Hexagon(hexagonRadius, hexagonColors[4], R.drawable.icon_lock) { // Outer image
                    toggleHexagonColor(hexagonColors[4])
                }
            }

            // Row 4: Two outer hexagons
            Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
                Hexagon(hexagonRadius, hexagonColors[5], R.drawable.icon_lock) { // Outer image
                    toggleHexagonColor(hexagonColors[5])
                }
                Hexagon(hexagonRadius, hexagonColors[6], R.drawable.icon_lock) { // Outer image
                    toggleHexagonColor(hexagonColors[6])
                }
            }
        }
    }
}

fun toggleHexagonColor(hexColor: MutableState<Color>) {
    hexColor.value = if (hexColor.value == Color(0xFFD3D3D3)) {
        Color(0xFFFFAA01) // Inner color on click
    } else {
        Color(0xFFD3D3D3) // Reset to default outer color
    }
}


@Composable
fun Hexagon(
    radius: Dp,
    hexColor: MutableState<Color>,
    imageRes: Int,
    isCenter: Boolean = false,
    onClick: () -> Unit
) {
    val outerColor =
        if (hexColor.value == Color(0xFFD3D3D3)) Color(0xFFD3D3D3) else Color(0xFFFFBE00)
    val innerColor =
        if (hexColor.value == Color(0xFFD3D3D3)) Color(0xFFC8C8C8) else Color(0xFFFFAA01)
    val shadowColor = Color(android.graphics.Color.parseColor("#D9D9D9"))

    Box(modifier = Modifier.size(radius * 2)) {
        Canvas(
            modifier = Modifier
                .size(radius * 2)
                .pointerInput(Unit) {
                    detectTapGestures(onTap = { onClick() })
                }
        ) {
            val radiusPx = radius.toPx()
            val centerX = size.width / 2
            val centerY = size.height / 2
            val angle = Math.PI / 3.0

            // Shadow path (slightly larger and offset)
            val shadowPath = Path().apply {
                val shadowOffset = radiusPx * 0.2f
                moveTo(
                    (centerX + radiusPx * cos(angle / 2)).toFloat(),
                    (centerY + radiusPx * sin(angle / 2) + shadowOffset).toFloat()
                )
                for (i in 1..6) {
                    lineTo(
                        (centerX + radiusPx * cos(angle * i + angle / 2)).toFloat(),
                        (centerY + radiusPx * sin(angle * i + angle / 2) + shadowOffset).toFloat()
                    )
                }
                close()
            }

            // Draw shadow
            drawPath(
                path = shadowPath,
                color = shadowColor,
                style = Fill,
                alpha = 0.5f
            )

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

            // Draw outer hexagon
            drawPath(
                path = hexPathOuter,
                color = outerColor
            )

            // Draw inner hexagon
            drawPath(
                path = hexPathInner,
                color = innerColor
            )
        }

        // Add image to hexagon
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(if (isCenter) radius * 0.9f else radius * 0.7f)
                .align(Alignment.Center)
        )
    }
}


@Preview
@Composable
fun PreviewBeeGameScreen() {
    BeeGameScreen()
}
