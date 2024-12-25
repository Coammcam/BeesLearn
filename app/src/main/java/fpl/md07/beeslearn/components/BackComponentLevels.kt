package fpl.md07.beeslearn.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.screens.tabs.hexagonShape

@Composable
fun BackComponentLevers(navController: NavController) {
    Row (
        modifier = Modifier
            .padding(top = 16.dp, end = 16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_back),
            contentDescription = "Back",
            modifier = Modifier
                .size(30.dp)
                .clickable { navController.popBackStack()}
        )

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.primary_color),
                    shape = RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "Coins",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "5",
                    fontFamily = customFont,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.secondary_color)
                )
            }
        }

        Spacer(modifier = Modifier.width(7.dp))

        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.primary_color),
                    shape = RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically,
                ) {
                Image(
                    painter = painterResource(id = R.drawable.honey),
                    contentDescription = "Coins",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "100",
                    fontFamily = customFont,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.secondary_color)
                )
            }
        }

        Spacer(modifier = Modifier.width(7.dp))

        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.primary_color),
                    shape = RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.gem_blue),
                    contentDescription = "Coins",
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))

                Text(
                    text = "5",
                    fontFamily = customFont,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.secondary_color)
                )
            }
        }
    }
}
@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewBackComponentLevers() {
    var navController = rememberNavController()
    BackComponentLevers(navController)
}
