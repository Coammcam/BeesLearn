package fpl.md07.beeslearn.screens
//
//import android.os.Bundle
//import androidx.activity.ComponentActivity
//import androidx.activity.compose.setContent
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.runtime.*
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Path
//import androidx.compose.ui.graphics.drawscope.DrawScope
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import kotlin.math.cos
//import kotlin.math.sin
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            HexagonLayout()
//        }
//    }
//}
//
//fun DrawScope.drawHexagon(centerX: Float, centerY: Float, radius: Float, color: Color) {
//    val path = Path()
//    val angle = (Math.PI / 3) // 60 độ
//
//    for (i in 0..5) {
//        val x = (centerX + radius * cos(angle * i)).toFloat()
//        val y = (centerY + radius * sin(angle * i)).toFloat()
//        if (i == 0) {
//            path.moveTo(x, y)
//        } else {
//            path.lineTo(x, y)
//        }

//    }
//    path.close()
//    drawPath(path = path, color = color)
//}
//
//@Composable
//fun HexagonLayout() {
//    val radius = 100f // Bán kính cho lục giác
//    val mmToPx = with(LocalDensity.current) { 3.dp.toPx() } // Chuyển đổi 3mm thành px
//
//    // Khoảng cách giữa các lục giác với bán kính và thêm khoảng cách 3mm
//    val distance = 2 * radius * cos(Math.PI / 6).toFloat() + mmToPx
//
//    // Tạo danh sách trạng thái màu cho mỗi hình lục giác
//    var hexagonColors = remember {
//        mutableStateListOf(
//            Color(0xFF6A5ACD), // Màu ban đầu cho lục giác trung tâm
//            Color(0xFF6A5ACD), // Màu ban đầu cho các lục giác bao quanh
//            Color(0xFF6A5ACD),
//            Color(0xFF6A5ACD),
//            Color(0xFF6A5ACD),
//            Color(0xFF6A5ACD),
//            Color(0xFF6A5ACD)
//        )
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize(),
//        contentAlignment = Alignment.Center // Căn giữa toàn bộ nội dung
//    ) {
//        // Vẽ 6 hình lục giác bao quanh
//        for (i in 1..6) {
//            val angle = Math.PI / 3 * (i - 1)
//            val xOffset = (distance * cos(angle)).toFloat().dp
//            val yOffset = (distance * sin(angle)).toFloat().dp
//
//            Box(
//                modifier = Modifier
//                    .offset(xOffset, yOffset)
//                    .clickable {
//                        hexagonColors[i] = Color.Yellow
//                    }
//            ) {
//                Canvas(modifier = Modifier.size(200.dp)) {
//                    drawHexagon(size.width / 2, size.height / 2, radius, hexagonColors[i])
//                }
//            }
//        }
//
//        // Vẽ lục giác ở giữa
//        Box(
//            modifier = Modifier
//                .clickable {
//                    hexagonColors[0] = Color.Yellow
//                }
//        ) {
//            Canvas(modifier = Modifier.size(200.dp)) {
//                drawHexagon(size.width / 2, size.height / 2, radius, hexagonColors[0])
//            }
//        }
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun HexagonLayoutPreview() {
//    HexagonLayout()
//}

//////// bằng ảnh
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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import fpl.md07.beeslearn.R
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TextBox()
            HexagonApp()
            BeeAnimationScreen()
        }
    }
}


@Composable
fun HexagonApp() {

    // Khởi tạo danh sách màu sắc cho hình lục giác
    val hexagonColors =
        remember { mutableStateListOf(R.drawable.hexagon_gray) } // Hình lục giác trung tâm
    hexagonColors.addAll(List(7) { R.drawable.hexagon_gray }) // 6 hình lục giác bao quanh
    val hexagonSize = 100.dp
    val spacing = 5.dp
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,


        ) {
        // Gọi composable chứa văn bản
//        TextBox()


//        Spacer(modifier = Modifier.height(500.dp)) // Thêm khoảng trống giữa box và hình lục giác
        // Bố cục 6 lục giác xung quanh 1 lục giác trung tâm
        Box() {

            // Lục giác trung tâm
            Box(modifier = Modifier.size(hexagonSize)) { // Thêm Box để chứa cả hình lục giác và icon
                Image(
                    painter = painterResource(id = hexagonColors[0]),
                    contentDescription = "Center Hexagon",
                    modifier = Modifier.matchParentSize() // Lấp đầy box
                )

                // Biểu tượng ở giữa lục giác
                Image(
                    painter = painterResource(id = R.drawable.honey_picture), // Thay thế bằng biểu tượng của bạn
                    contentDescription = "Icon in Center",
                    modifier = Modifier
                        .size(40.dp) // Kích thước của biểu tượng
                        .align(Alignment.Center) // Căn giữa biểu tượng
                )
            }
            // Lục giác trung tâm
//            Image(
//                painter = painterResource(id = hexagonColors[0]),
//                contentDescription = "Center Hexagon",
//                modifier = Modifier.size(hexagonSize)
//            )


            // Lục giác bao quanh
            for (i in 1..7) {
                val angle = Math.toRadians((60 * (i - 1)).toDouble() - 30) // Tính toán góc
                // Sử dụng LocalDensity để chuyển đổi Dp sang Px
                val radius =
                    with(LocalDensity.current) { (hexagonSize.toPx() * 0.3 + spacing.toPx()) } // Bỏ qua spacing
                val x = (radius * cos(angle)).toFloat()
                val y = (radius * sin(angle)).toFloat()

                Image(
                    painter = painterResource(id = hexagonColors[i]),
                    contentDescription = "Hexagon $i",
                    modifier = Modifier
                        .size(hexagonSize)
                        .offset(x.dp, y.dp)
                        .clickable {
                            // Chuyển đổi màu sắc khi nhấp
                            hexagonColors[i] = if (hexagonColors[i] == R.drawable.hexagon_gray) {
                                R.drawable.hexagon_yellow
                            } else {
                                R.drawable.hexagon_gray // Đổi lại thành màu xám khi nhấp lại
                            }
                        }
                )
            }
        }
    }
}

@Composable
fun TextBox() {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top


    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Box(
            modifier = Modifier
                .padding(16.dp)
                .border(
                    BorderStroke(1.dp, Color.Black),
                    shape = RoundedCornerShape(20.dp)
                ) // Viền với góc bo tròn
//                .background(Color.) // Nền màu vàng
                .background(Color(android.graphics.Color.parseColor("#FFF192"))) // Nền màu từ mã màu HEX
                .padding(40.dp) // Padding bên trong box


        ) {
            Text(
                text = "Đây là đoạn văn bản trong khung có viền và nền màu vàng.",
                color = Color.Black // Màu chữ
            )
        }
    }
}

@Composable
fun BouncingBee(modifier: Modifier = Modifier) {
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
            painter = painterResource(id = R.drawable.picture_ani), // Replace with your bee image resource
            contentDescription = "Bouncing Bee",
            modifier = Modifier
                .size(100.dp)
                .offset(x = horizontalOffset.dp, y = -verticalOffset.dp)
                .rotate(rotation)
        )
    }
}

@Composable
fun BeeAnimationScreen() {
//    Box {
//        BouncingBee(modifier = Modifier.size(200.dp))
//    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        BouncingBee(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 16.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHexagonApp() {
    HexagonApp()
}
