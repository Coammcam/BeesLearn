package fpl.md07.beeslearn.screens.tabs

import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.OptIn
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.media3.common.util.NotificationUtil.createNotificationChannel
import androidx.media3.common.util.UnstableApi
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import fpl.md07.beeslearn.GlobalVariable.UserSession
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.LoginViewModel

@Composable
fun SettingScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    val user = UserSession.currentUser
    val scrollState = rememberScrollState()
    var showAlert by remember { mutableStateOf(false) }
    var showLogoutAlert by remember { mutableStateOf(false) }
    val context = navController.context

    // Tạo kênh thông báo
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "beeslearn_notifications",
                "BeesLearn Notifications",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Thông báo từ ứng dụng BeesLearn"
            }
            val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }

    // Gửi thông báo
    fun sendNotification(context: Context, title: String, message: String) {
        val channelId = "beeslearn_notifications"
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val permission = android.Manifest.permission.POST_NOTIFICATIONS
            if (context.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                    (context as Activity),
                    arrayOf(permission),
                    1
                )
                return
            }
        }
        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.beeds)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        NotificationManagerCompat.from(context).notify(0, notification)
    }

    // Khởi tạo kênh thông báo khi màn hình được tạo
    LaunchedEffect(Unit) {
        createNotificationChannel(context)
    }

    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Cài đặt",
            fontFamily = Nunito_Bold,
            fontSize = 35.sp,
            color = colorResource(id = R.color.secondary_color),
            modifier = Modifier
                .fillMaxWidth()
                .align(alignment = Alignment.Start)
                .padding(10.dp)
        )

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            AsyncImage(
                model = user?.profileImageUrl.takeIf { !it.isNullOrEmpty() },
                contentDescription = "Avatar",
                placeholder = painterResource(id = R.drawable.avatarsetting),
                fallback = painterResource(id = R.drawable.avatarsetting),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(170.dp)
                    .clip(CircleShape)
                    .clickable {
                        sendNotification(
                            context,
                            "BeesLearn",
                            "Gói Premium của bạn sẽ hết hạn vào ngày: 31/12/2024."
                        )
                    }
            )

            Spacer(modifier = Modifier.height(10.dp))

            user?.let { userInfo ->
                Row (
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = userInfo.username,
                        fontFamily = Nunito_Bold,
                        fontSize = 38.sp,
                        color = colorResource(id = R.color.secondary_color)
                    )
                    Image(
                        painter = painterResource(R.drawable.blue_tick),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp)
                            .clickable {
                                sendNotification(
                                    context,
                                    "BeesLearn",
                                    "Tài khoản của bạn đang sử dụng gói Premium."
                                )
                            showAlert = true
                            }
                    )
                }
                Text(
                    text = userInfo.email,
                    fontFamily = Nunito_Bold,
                    fontSize = 13.sp,
                    color = colorResource(id = R.color.secondary_color)
                )
            } ?: Text(
                text = "Không có người dùng nào đăng nhập",
                fontFamily = Nunito_Bold,
                fontSize = 20.sp,
                color = Color.Red
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = { navController.navigate("editProfile") },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp)
                    .shadow(
                        elevation = 10.dp,
                        shape = RoundedCornerShape(25.dp),
                        clip = false
                    ),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.secondary_color))
            ) {
                Text(
                    text = "Chỉnh sửa hồ sơ",
                    fontFamily = Nunito_Bold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = {
                    navController.navigate("paymentComponent")
                },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary_color))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.heart_buy),
                        contentDescription = "Languages",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Mua Heart",
                        fontFamily = Nunito_Bold,
                        color = colorResource(id = R.color.secondary_color),
                        modifier = Modifier.padding(start = 8.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = { /* Add your action here */ },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary_color))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.favorite),
                        contentDescription = "Favorite",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Yêu thích",
                        fontFamily = Nunito_Bold,
                        color = colorResource(id = R.color.secondary_color),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = { /* Add your action here */ },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary_color))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.download),
                        contentDescription = "Download",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Tải xuống",
                        fontFamily = Nunito_Bold,
                        color = colorResource(id = R.color.secondary_color),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = { /* Add your action here */ },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary_color))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.world),
                        contentDescription = "Languages",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Ngôn ngữ",
                        fontFamily = Nunito_Bold,
                        color = colorResource(id = R.color.secondary_color),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(15.dp))

            Button(
                onClick = {
                    showLogoutAlert = true
                },
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(Color(0xFFD33D5C))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp)
                ) {
                    Image(

                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = "Logout",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Đăng xuất",
                        fontFamily = Nunito_Bold,
                        color = Color(0xFFFFFFFF),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
    // Hiển thị AlertDialog xác nhận đăng xuất
    if (showLogoutAlert) {
        AlertDialog(
            onDismissRequest = { showLogoutAlert = false }, // Đóng hộp thoại khi nhấn ra ngoài
            title = { Text("Xác nhận đăng xuất", fontSize = 20.sp) },
            text = { Text("Bạn có chắc chắn muốn đăng xuất không?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        showLogoutAlert = false // Đóng hộp thoại
                        sendNotification(context, "Bạn đã đăng xuất", "Thời gian học tập của bạn chưa đủ")
                        loginViewModel.logout(context = navController.context)
                        navController.navigate("welcomeScreen") {
                            popUpTo(0) // Xóa toàn bộ stack điều hướng để ngăn quay lại màn hình cũ
                        }
                    }
                ) {
                    Text("OK", color = colorResource(id = R.color.secondary_color))
                }
            },
            dismissButton = {
                TextButton(onClick = { showLogoutAlert = false }) {
                    Text("Hủy", color = Color.Gray)
                }
            }
        )
    }
    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            confirmButton = {
                TextButton(onClick = { showAlert = false }) {
                    Text("OK")
                }
            },
            title = {
                Text(
                    "Thông báo",
                    fontSize = 30.sp
                )
            },
            text = {
                Text(
                    "Gói Premium của bạn sẽ hết hạn vào ngày: 31/12/2024.",
                )
            }
        )
    }
}

@Composable
fun SettingButton() {

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewSettingScreen() {
    val navController = rememberNavController()
    SettingScreen(navController)
}