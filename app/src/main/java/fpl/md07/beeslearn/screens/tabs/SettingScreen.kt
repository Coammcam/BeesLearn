package fpl.md07.beeslearn.screens.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import fpl.md07.beeslearn.GlobalVariable.UserSession
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.LoginViewModel

@Composable
fun SettingScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
//    val userData by loginViewModel.userData.collectAsState()

    val user = UserSession.currentUser
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .background(Color(0xffffffff))
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
                .background(Color(0xffffffff))
                .verticalScroll(scrollState)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            AsyncImage(
                model = if(user?.profileImageUrl?.isEmpty() == true || user?.profileImageUrl == null) null else user.profileImageUrl,
                contentDescription = "Avatar",
                placeholder = painterResource(id = R.drawable.avatarsetting),
                fallback = painterResource(id = R.drawable.avatarsetting),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(170.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(10.dp))

            user?.let { userInfo ->
                Text(
                    text = userInfo.username,
                    fontFamily = Nunito_Bold,
                    fontSize = 38.sp,
                    color = colorResource(id = R.color.secondary_color)
                )

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
                        painter = painterResource(id = R.drawable.wave_icon_dark),
                        contentDescription = "Languages",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Thống Kê",
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
                    loginViewModel.logout(context = navController.context)

                    navController.navigate("welcomeScreen") {
                        popUpTo(0) // Xóa toàn bộ stack điều hướng để ngăn quay lại màn hình cũ
                    }
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

            Spacer(modifier = Modifier.height(15.dp))
        }
    }
}

@Composable
fun SettingButton(){

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewSettingScreen() {
    val navController = rememberNavController()
    SettingScreen(navController)
}
