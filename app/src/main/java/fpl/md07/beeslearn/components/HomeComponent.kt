package fpl.md07.beeslearn.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@Composable
fun HomeComponent() {

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp, end = 16.dp),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                modifier = Modifier
                    .shadow(
                        elevation = 8.dp,
                        shape = RoundedCornerShape(50),
                        clip = false
                    )
                    .clip(RoundedCornerShape(50))
                    .background(Color(0xFFFFFACD))
                    .padding(horizontal = 12.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.honey),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "100",
                    fontFamily = Nunito_Bold,
                    fontSize = 18.sp,
                    color = Color(0xFF591429)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            modifier = Modifier
                .width(275.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFFF192))
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .clickable {
                    Toast.makeText(context, "Tính năng đang được phát triển", Toast.LENGTH_SHORT).show()
                }
        ) {
            Row(
                modifier = Modifier
                    .background(Color(0xFFFFF192)),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(32.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Unlimited",
                        fontFamily = Nunito_Bold,
                        fontSize = 22.sp,
                        color = Color(0xFF000000)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "20$ / Month",
                        fontFamily = Nunito_Bold,
                        fontSize = 22.sp,
                        color = Color(0xFFFFA500)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .width(275.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFFF192))
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .clickable {
                    Toast.makeText(context, "Tính năng đang được phát triển", Toast.LENGTH_SHORT).show()
                }
        ) {
            Row(
                modifier = Modifier
                    .background(Color(0xFFFFF192)),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(32.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Refill 5/5",
                        fontFamily = Nunito_Bold,
                        fontSize = 22.sp,
                        color = Color(0xFF000000)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row (
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "500",
                            fontFamily = Nunito_Bold,
                            fontSize = 22.sp,
                            color = Color(0xFFFFA500)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.honey),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .size(24.dp)
                                .fillMaxSize()
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier
                .width(275.dp)
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(16.dp)
                )
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFFFFF192))
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .clickable {
                    Toast.makeText(context, "Tính năng đang được phát triển", Toast.LENGTH_SHORT).show()
                }
        ) {
            Row(
                modifier = Modifier
                    .background(Color(0xFFFFF192)),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.Center,
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = null,
                    tint = Color.Red,
                    modifier = Modifier.size(32.dp)
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Refill 2/3",
                        fontFamily = Nunito_Bold,
                        fontSize = 22.sp,
                        color = Color(0xFF000000)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Watching ads",
                        fontFamily = Nunito_Bold,
                        fontSize = 22.sp,
                        color = Color(0xFFFFA500)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeComponent() {
    HomeComponent()
}