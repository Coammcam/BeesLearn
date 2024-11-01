package fpl.md07.beeslearn.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import fpl.md07.beeslearn.R
import kotlin.math.cos
import kotlin.math.sin
import fpl.md07.beeslearn.components.TextBoxComponent
import fpl.md07.beeslearn.components.BeeAnimaComponent
import fpl.md07.beeslearn.components.TopBarComponent

@Composable
fun PracticeOneScreen() {
    MaterialTheme {
        Surface {
            ChoosePractice()
        }
    }
}

@Composable
fun ChoosePractice() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopBarComponent()
        Spacer(modifier = Modifier.height(16.dp))
        Box() {
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

        Spacer(modifier = Modifier.height(80.dp))
        HexGrid()
    }
}

@Composable
fun HexGrid() {
    val outerColor = colorResource(id = R.color.outerColor_hexagon)
    val innerColor = colorResource(id = R.color.innerColor_hexagon)
    val newOuterColor = colorResource(id = R.color.newOuterColor)
    val newInnerColor = colorResource(id = R.color.newInnerColor)

    val hexagonColors = remember {
        mutableStateListOf(
            mutableStateOf(Pair(outerColor, innerColor)), // Hexagon 1
            mutableStateOf(Pair(outerColor, innerColor)),
            mutableStateOf(Pair(outerColor, innerColor)),
            mutableStateOf(Pair(outerColor, innerColor)),  // Center hexagon
            mutableStateOf(Pair(outerColor, innerColor)),
            mutableStateOf(Pair(outerColor, innerColor)),
            mutableStateOf(Pair(outerColor, innerColor))   // 6
        )
    }

    // Define the hexagon radius (size)
    val hexagonRadius = 40.dp

    // Define spacing between hexagons
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
            // Row 1: Two outer hexagons
            Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
                Hexagon(hexagonRadius, hexagonColors[0]) {
                    toggleHexagonColor(hexagonColors[0], outerColor, innerColor, newOuterColor, newInnerColor)
                }
                Hexagon(hexagonRadius, hexagonColors[1]) {
                    toggleHexagonColor(hexagonColors[1], outerColor, innerColor, newOuterColor, newInnerColor)
                }
            }

            // Row 2: Three hexagons including center
            Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
                Hexagon(hexagonRadius, hexagonColors[2]) {
                    toggleHexagonColor(hexagonColors[2], outerColor, innerColor, newOuterColor, newInnerColor)
                }
                Hexagon(hexagonRadius, hexagonColors[3], imageRes = R.drawable.honey_picture, isCenter = true) {
                    toggleHexagonColor(hexagonColors[3], outerColor, innerColor, newOuterColor, newInnerColor)
                }
                Hexagon(hexagonRadius, hexagonColors[4]) {
                    toggleHexagonColor(hexagonColors[4], outerColor, innerColor, newOuterColor, newInnerColor)
                }
            }

            // Row 3: Two outer hexagons
            Row(horizontalArrangement = Arrangement.spacedBy(spacing)) {
                Hexagon(hexagonRadius, hexagonColors[5]) {
                    toggleHexagonColor(hexagonColors[5], outerColor, innerColor, newOuterColor, newInnerColor)
                }
                Hexagon(hexagonRadius, hexagonColors[6]) {
                    toggleHexagonColor(hexagonColors[6], outerColor, innerColor, newOuterColor, newInnerColor)
                }
            }
        }
    }
}

fun toggleHexagonColor(
    hexColor: MutableState<Pair<Color, Color>>,
    outerColor: Color,
    innerColor: Color,
    newOuterColor: Color,
    newInnerColor: Color
) {
    val (currentOuterColor, currentInnerColor) = hexColor.value

    hexColor.value = when {
        currentOuterColor == newOuterColor && currentInnerColor == newInnerColor ->
            Pair(currentOuterColor, currentInnerColor) // No change
        currentOuterColor == outerColor && currentInnerColor == innerColor ->
            Pair(newOuterColor, newInnerColor) // Change to new colors
        else ->
            Pair(outerColor, innerColor) // Reset to default colors
    }
}


@Composable
fun Hexagon(
    radius: Dp,
    hexColor: MutableState<Pair<Color, Color>>,
    imageRes: Int? = null, // Optional image for the center hexagon
    isCenter: Boolean = false,
    onClick: () -> Unit
) {
    // Load colors using colorResource or define it directly
    val innerColor = colorResource(id = R.color.innerColor_hexagon) // Ensure this resource exists
    val shadowColor = colorResource(id = R.color.shadowColor_hexagon)

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
                color = hexColor.value.first // Use the outer color from hexColor
            )

            // Draw inner hexagon
            drawPath(
                path = hexPathInner,
                color = hexColor.value.second // Use the inner color from hexColor
            )
        }

        // Add image only if it's the center hexagon
        if (isCenter && imageRes != null) {
            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .size(radius * 1.3f)
                    .align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun PreviewBeeGameScreen() {
    ChoosePractice()
}
