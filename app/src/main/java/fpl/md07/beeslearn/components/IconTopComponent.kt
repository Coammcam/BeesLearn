package fpl.md07.beeslearn.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IconTopComponent(honeyCombCount: Int?, honeyJarCount: Int?, userScore: Int?, showHoneyCombStatus: ()->Unit) {

    // Heart and coins row
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Heart with background
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.primary_color), shape = RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 4.dp)
                .clickable { showHoneyCombStatus() }
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "Heart",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$honeyCombCount/3 +",
                    fontSize = 16.sp,
                    fontFamily = customFont,
                    color = colorResource(id = R.color.secondary_color)
                )
            }
        }

        // Coins with background
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.primary_color), shape = RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.honey),
                    contentDescription = "Coins",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$honeyJarCount",
                    fontFamily = customFont,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.secondary_color)
                )
            }
        }

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
                    text = "$userScore",
                    fontFamily = customFont,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.secondary_color)
                )
            }
        }
    }
}
//@Preview
//@Composable
//fun PreviewSelectExercise() {
//    IconTopComponent()
//}
