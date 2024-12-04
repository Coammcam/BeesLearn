package fpl.md07.beeslearn.screens.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

import java.util.*

@Composable
fun HomeScreen(navController: NavController) {
    var showCalendar by remember { mutableStateOf(false) } // Trạng thái để hiển thị lịch hoặc hình ảnh
    var showTranslationBox by remember { mutableStateOf(false) } // Trạng thái để hiển thị giao diện dịch
    var showImage by remember { mutableStateOf(true) } // Trạng thái để hiển thị hình ảnh

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconRowFirst(
            onIcon1Click = {

            },
            onIcon2Click = {
                // Khi người dùng nhấn vào Icon 2, hiển thị lịch
                showCalendar = true
                showTranslationBox = false
                showImage = false
            },
            onIcon3Click = {
                // Khi người dùng nhấn vào Icon 3, hiển thị giao diện dịch
                showCalendar = false
                showTranslationBox = true
                showImage = false
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Điều kiện để hiển thị hình ảnh, lịch hoặc giao diện dịch
        when {
            showCalendar -> RealCalendarView()
            showTranslationBox -> TranslationBox()
            showImage -> BeeImage()
        }

        Spacer(modifier = Modifier.weight(1f))

        FunctionButtonsGrid(navController)

        Spacer(modifier = Modifier.height(24.dp))

        Spacer(modifier = Modifier.height(16.dp))
    }
}


@Composable
fun TranslationBox() {
    // Trạng thái theo dõi ngôn ngữ hiện tại
    var currentLanguage by remember { mutableStateOf("ENG") }  // Ngôn ngữ ban đầu là ENG
    var inputText by remember { mutableStateOf("Hello, Nice to meet you") }  // Văn bản nhập ban đầu
    var translatedText by remember { mutableStateOf("Xin chào, rất vui được gặp bạn") }  // Văn bản dịch

    Column(
        modifier = Modifier
            .width(350.dp) // Tăng chiều rộng
            .background(Color(0xFFFFF59D), shape = RoundedCornerShape(16.dp)) // Nền màu vàng
            .border(2.dp, Color(0xFF800000), shape = RoundedCornerShape(16.dp)) // Viền màu nâu
            .padding(16.dp) // Tăng padding
    ) {
        // Hàng đầu tiên: Nhãn ngôn ngữ "ENG" và "VIE" với mũi tên hoán đổi
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp), // Tăng khoảng cách giữa các hàng
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (currentLanguage == "ENG") "ENG" else "VIE", // Hiển thị ngôn ngữ hiện tại
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.Black
                )
            )
            Icon(
                painter = painterResource(id = R.drawable.swap), // Biểu tượng mũi tên
                contentDescription = "Swap Languages",
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        // Khi nhấn vào mũi tên hoán đổi ngôn ngữ
                        if (currentLanguage == "ENG") {
                            currentLanguage = "VIE"
                            val temp = inputText
                            inputText = translatedText
                            translatedText = temp
                        } else {
                            currentLanguage = "ENG"
                            val temp = inputText
                            inputText = translatedText
                            translatedText = temp
                        }
                    }
            )
            Text(
                text = if (currentLanguage == "ENG") "VIE" else "ENG", // Hiển thị ngôn ngữ đích
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    color = Color.Black
                )
            )
        }

        // Thanh ngang thứ nhất
        Divider(
            color = Color(0xFF800000), // Màu nâu
            thickness = 2.dp // Độ dày của thanh ngang
        )

        Spacer(modifier = Modifier.height(16.dp)) // Tăng khoảng cách giữa các hàng

        // Hàng thứ hai: Hiển thị văn bản cần dịch
        Text(
            text = inputText,
            style = TextStyle(fontSize = 20.sp, color = Color.Black), // Tăng kích thước chữ
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Tăng padding
        )

        // Thanh ngang thứ hai
        Divider(
            color = Color(0xFF800000), // Màu nâu
            thickness = 2.dp // Độ dày của thanh ngang
        )

        Spacer(modifier = Modifier.height(16.dp)) // Tăng khoảng cách giữa các hàng

        // Hàng thứ ba: Hiển thị bản dịch
        Text(
            text = translatedText,
            style = TextStyle(fontSize = 20.sp, color = Color.Black), // Tăng kích thước chữ
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp) // Tăng padding
        )
    }
}


@Composable
fun IconRowFirst(onIcon1Click: () -> Unit, onIcon2Click: () -> Unit, onIcon3Click: () -> Unit) {
    val context = LocalContext.current
    var isCalendarPressed by remember { mutableStateOf(false) }
    var isTranslatePressed by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Icon 1 (EN): Khi nhấn vào icon này, calendar và translate trở về trạng thái ban đầu
        Image(
            painter = painterResource(R.drawable.en),
            contentDescription = "Icon 1",
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    isCalendarPressed = false
                    isTranslatePressed = false
                    Toast
                        .makeText(context, "Icon 1 (EN) Clicked", Toast.LENGTH_SHORT)
                        .show()
                    onIcon1Click()
                }
        )

        // Icon 2 (Calendar)
        Image(
            painter = painterResource(if (isCalendarPressed) R.drawable.calendar else R.drawable.calendarcn),
            contentDescription = "Icon 2",
            modifier = Modifier
                .size(30.dp)
                .clickable {
                    if (!isCalendarPressed) {
                        isCalendarPressed = true // Chuyển sang chế độ hiển thị lịch
                        isTranslatePressed = false // Đặt lại trạng thái của icon dịch
                        Toast.makeText(context, "Icon Calendar Clicked", Toast.LENGTH_SHORT).show()
                        onIcon2Click()
                    }
                }
        )

        // Icon 4 (User): Khi nhấn vào icon này, calendar và translate trở về trạng thái ban đầu
        Image(
            painter = painterResource(R.drawable.user),
            contentDescription = "Icon 4",
            modifier = Modifier
                .size(40.dp)
                .clickable {
                    isCalendarPressed = false // Đặt lại trạng thái của icon lịch
                    isTranslatePressed = false // Đặt lại trạng thái của icon dịch
                    Toast.makeText(context, "Icon 4 Clicked", Toast.LENGTH_SHORT).show()

                }
        )
    }
}


@Composable
fun RealCalendarView() {
    var currentMonth by remember { mutableStateOf(Calendar.getInstance().get(Calendar.MONTH)) }
    var currentYear by remember { mutableStateOf(Calendar.getInstance().get(Calendar.YEAR)) }

    val calendar = Calendar.getInstance()
    calendar.set(Calendar.MONTH, currentMonth)
    calendar.set(Calendar.YEAR, currentYear)

    // Lấy số ngày trong tháng
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)

    // Lấy ngày đầu tiên của tháng để biết nó bắt đầu vào thứ mấy
    calendar.set(Calendar.DAY_OF_MONTH, 1)
    val firstDayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    // Tạo danh sách các ngày trong tháng
    val daysList = mutableListOf<String>()
    for (i in 2 until firstDayOfWeek) {
        daysList.add("") // Thêm các ô trống trước khi tháng bắt đầu
    }
    for (day in 1..daysInMonth) {
        daysList.add(day.toString())
    }

    // Giao diện lịch với kích thước nhỏ gọn hơn
    Column(
        modifier = Modifier
            .width(250.dp) // Giảm chiều rộng của lịch
            .background(
                Color(0xFFFFF59D),
                shape = RoundedCornerShape(16.dp)
            ) // Nền màu vàng và bo tròn góc toàn bộ
            .border(
                2.dp,
                Color(0xFF800000),
                shape = RoundedCornerShape(16.dp)
            ) // Viền ngoài màu nâu
            .padding(8.dp) // Giảm padding của lịch
    ) {
        // Phần tiêu đề và nút điều hướng
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Nút quay lại
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Previous Month",
                modifier = Modifier
                    .size(20.dp) // Giảm kích thước icon
                    .clickable {
                        // Chuyển sang tháng trước
                        if (currentMonth == 0) {
                            currentMonth = 11
                            currentYear--
                        } else {
                            currentMonth--
                        }
                    }
            )

            // Tiêu đề tháng và năm
            Text(
                text = "${getMonthName(currentMonth)} $currentYear",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF800000)
                ),
                modifier = Modifier.align(Alignment.CenterVertically)
            )

            // Nút chuyển tiếp
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Next Month",
                modifier = Modifier
                    .size(20.dp) // Giảm kích thước icon
                    .clickable {
                        // Chuyển sang tháng tiếp theo
                        if (currentMonth == 11) {
                            currentMonth = 0
                            currentYear++
                        } else {
                            currentMonth++
                        }
                    }
            )
        }

        // Thanh ngang đầu tiên dưới tiêu đề tháng
        Spacer(modifier = Modifier.height(4.dp))
        Divider(
            color = Color(0xFF800000),
            thickness = 1.dp
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Hàng tiêu đề các thứ trong tuần
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            listOf("Mon", "Tue", "We", "Th", "Fr", "Sa", "Sun").forEach { day ->
                Text(
                    text = day,
                    modifier = Modifier.weight(1f),
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF800000),
                        fontSize = 10.sp
                    ) // Giảm kích thước chữ tiêu đề tuần
                )
            }
        }

        // Thanh ngang thứ hai dưới các thứ trong tuần
        Spacer(modifier = Modifier.height(4.dp))
        Divider(
            color = Color(0xFF800000),
            thickness = 1.dp
        )
        Spacer(modifier = Modifier.height(4.dp))

        // Hiển thị các ngày trong tháng
        val rows = (daysList.size + 6) / 7  // Tính toán số hàng cần thiết
        for (row in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                for (col in 0..6) {
                    val index = row * 7 + col
                    val dayText = if (index < daysList.size) daysList[index] else ""
                    Text(
                        text = dayText,
                        modifier = Modifier
                            .padding(4.dp) // Giảm padding của các ngày
                            .weight(1f),
                        style = TextStyle(
                            fontSize = 12.sp,
                            color = Color(0xFF800000)
                        ) // Giảm kích thước chữ ngày
                    )
                }
            }
        }
    }
}


fun getMonthName(month: Int): String {
    return when (month) {
        0 -> "January"
        1 -> "February"
        2 -> "March"
        3 -> "April"
        4 -> "May"
        5 -> "June"
        6 -> "July"
        7 -> "August"
        8 -> "September"
        9 -> "October"
        10 -> "November"
        11 -> "December"
        else -> ""
    }
}


@Composable
fun BeeImage() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), // Padding cho khoảng cách hai bên
        verticalAlignment = Alignment.CenterVertically // Căn giữa theo trục dọc
    ) {
        // Ảnh Bee nằm bên trái
        Image(
            painter = painterResource(R.drawable.beeds),
            contentDescription = "Bee Image",
            modifier = Modifier.size(150.dp) // Kích thước ảnh Bee
        )

        Spacer(modifier = Modifier.width(16.dp)) // Khoảng cách giữa hai hình ảnh

        // Cột chứa Logo và chữ bên phải
        Column(
            modifier = Modifier
                .padding(start = 16.dp) // Padding cho khoảng cách bên trái của cột
        ) {
            // Ảnh Logo BeesLearn nằm phía trên
            Image(
                painter = painterResource(R.drawable.logo), // Thay thế bằng ảnh logo của bạn
                contentDescription = "BeesLearn Logo",
                modifier = Modifier
                    .height(50.dp) // Giảm chiều cao của logo
                    .fillMaxWidth() // Đảm bảo logo sẽ giữ tỷ lệ chiều rộng
                    .aspectRatio(3f) // Giữ tỷ lệ tùy chỉnh nếu muốn, điều chỉnh tỷ lệ theo nhu cầu
            )

            Spacer(modifier = Modifier.height(4.dp)) // Điều chỉnh khoảng cách giữa logo và chữ

            // Văn bản nằm dưới logo
            Text(
                text = "Start to learning with Bee", // Nội dung văn bản
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF800000) // Màu chữ
                ),
                modifier = Modifier.padding(top = 2.dp) // Điều chỉnh padding nếu cần
            )
        }

    }
}


@Composable
fun FunctionButtonsGrid(navController: NavController) {
    // Bố trí các nút trong lưới 2x2
    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ActionButton(navController = navController, item = listItems[0])
            ActionButton(navController = navController, item = listItems[1])
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            ActionButton(navController = navController, item = listItems[2])
            ActionButton(navController = navController, item = listItems[3])
        }
    }
}

data class ListItem(
    val title: String,
    val iconResIdgs: Int,
    val funtion: String,
)

val listItems = listOf(
    ListItem(
        title = "Pratice", iconResIdgs = R.drawable.icpractice, funtion = "selectExercise"
    ),
    ListItem(
        title = "Podcast", iconResIdgs = R.drawable.icpodcast, funtion = "podcastScreen"
    ),
    ListItem(
        title = "Movie", iconResIdgs = R.drawable.icmovie, funtion = "movieScreen"
    ),
    ListItem(
        title = "Music", iconResIdgs = R.drawable.icmusic, funtion = "musicScreen"
    )
)

@Composable
fun ActionButton(navController: NavController, item: ListItem) {
    Box(
        modifier = Modifier
            .width(184.dp)
            .height(130.dp)
            .padding(7.dp)

    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .clickable { navController.navigate(item.funtion) },
            shape = RectangleShape, // Đặt góc vuông cho Card
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF192)), // Màu nền #FFF192
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 7.dp),
                verticalArrangement = Arrangement.SpaceBetween // Sắp xếp icon và chữ cách đều nhau
            ) {
                // Văn bản căn lên trên cùng và lề trái
                Text(
                    text = item.title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Nunito_Bold,
                        color = Color(0xFF000000)
                    ),
                    modifier = Modifier.align(Alignment.Start)
                        .padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))


                // Icon được căn giữa nhưng dịch sang trái

                Image(
                    painterResource(id = item.iconResIdgs),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                        .align(Alignment.End)
                    , // Đặt icon căn từ bên phải và dịch sang trái
                    contentScale = ContentScale.Fit
                )
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginPreview() {
    var navController = rememberNavController()
    HomeScreen(navController)
}
