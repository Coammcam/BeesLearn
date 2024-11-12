package fpl.md07.beeslearn.screens.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@Composable
fun SettingScreen (navController: NavController) {
    Column(
        modifier = Modifier
            .background(Color(0xffffffff))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Setting",
            fontFamily = Nunito_Bold,
            fontSize = 35.sp,
            color = colorResource(id = R.color.secondary_color),
            modifier = Modifier.fillMaxWidth().align(alignment = Alignment.Start).padding(20.dp)
        )

        Column (
            modifier = Modifier
                .background(Color(0xffffffff))
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Image(
                painter = painterResource(id = R.drawable.avatarsetting),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(170.dp)
                    .clip(CircleShape)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "BeesLearn",
                fontFamily = Nunito_Bold,
                fontSize = 38.sp,
                color = colorResource(id = R.color.secondary_color)
            )

            Text(
                text = "beeslearn1@gmail.com",
                fontFamily = Nunito_Bold,
                fontSize = 13.sp,
                color = colorResource(id = R.color.secondary_color)
            )

            Spacer(modifier = Modifier.height(25.dp))

            Button(
                onClick = { navController.navigate("editProfile")},
                modifier = Modifier
                    .height(55.dp)
                    .fillMaxWidth()
                    .padding(horizontal = 80.dp)
                    .shadow(
                        elevation = 10.dp, shape = RoundedCornerShape(25.dp), clip = false
                    ),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.secondary_color))
            ) {
                Text(
                    text = "Edit Profile",
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
                    .padding(horizontal = 20.dp)
                    .shadow(
                        elevation = 10.dp, shape = RoundedCornerShape(12.dp), clip = false
                    ),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary_color))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth().padding(start = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.favorite),
                        contentDescription = "Favorite",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Favorites",
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
                    .padding(horizontal = 20.dp)
                    .shadow(
                        elevation = 10.dp, shape = RoundedCornerShape(12.dp), clip = false
                    ),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary_color))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth().padding(start = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.download),
                        contentDescription = "download",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Downloads",
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
                    .padding(horizontal = 20.dp)
                    .shadow(
                        elevation = 10.dp, shape = RoundedCornerShape(12.dp), clip = false
                    ),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.primary_color))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier.fillMaxWidth().padding(start = 50.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.world),
                        contentDescription = "languages",
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "Languages",
                        fontFamily = Nunito_Bold,
                        color = colorResource(id = R.color.secondary_color),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }
    }
}

@Preview (showSystemUi = true, showBackground = true)
@Composable
fun PreviewSettingScreen () {
    var navController = rememberNavController()
    SettingScreen(navController)
}