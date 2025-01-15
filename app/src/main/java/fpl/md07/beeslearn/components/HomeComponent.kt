package fpl.md07.beeslearn.components

import android.content.Intent
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.main.PaymentActivity
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.viewmodels.UserDataViewModel

@Composable
fun HomeComponent(
    honeyCombCount: Int?,
    honeyJarCount: Int?,
    userScore: Int?,
    navController: NavController,
    goBack : () -> Unit
) {

    val context = LocalContext.current
    val userDataViewModel: UserDataViewModel = viewModel()
    val currencyData by userDataViewModel.currencyData.observeAsState()

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
            IconTopComponent(
                honeyCombCount = honeyCombCount,
                honeyJarCount = honeyJarCount,
                showHoneyCombStatus = {},
                userScore = userScore
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

//        Card(
//            modifier = Modifier
//                .width(275.dp)
//                .shadow(
//                    elevation = 8.dp,
//                    shape = RoundedCornerShape(16.dp)
//                )
//                .clip(RoundedCornerShape(16.dp))
//                .background(Color(0xFFFFF192))
//                .padding(horizontal = 16.dp, vertical = 12.dp)
//                .clickable {
//                    Toast.makeText(context, "Tính năng đang được phát triển", Toast.LENGTH_SHORT).show()
//                }
//        ) {
//            Row(
//                modifier = Modifier
//                    .background(Color(0xFFFFF192)),
//                verticalAlignment = Alignment.Top,
//                horizontalArrangement = Arrangement.Center,
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.heart),
//                    contentDescription = null,
//                    tint = Color.Red,
//                    modifier = Modifier.size(32.dp)
//                )
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = "Unlimited",
//                        fontFamily = Nunito_Bold,
//                        fontSize = 22.sp,
//                        color = Color(0xFF000000)
//                    )
//
//                    Spacer(modifier = Modifier.height(4.dp))
//
//                    Text(
//                        text = "20$ / Month",
//                        fontFamily = Nunito_Bold,
//                        fontSize = 22.sp,
//                        color = Color(0xFFFFA500)
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))

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
                    val newData = currencyData
                    if (newData != null) {
                        if (newData.honeyJar >= 50) {
                            newData.honeyJar -= 50
                            newData.honeyComb += 1
                            userDataViewModel.updateCurrencyData(newData)
                            Toast
                                .makeText(context, "Mua thành công 1 tim !", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast
                                .makeText(context, "Hết tiền", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
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
                        text = "Refill 1",
                        fontFamily = Nunito_Bold,
                        fontSize = 22.sp,
                        color = Color(0xFF000000)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "50",
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
                    val payment = Intent(context, PaymentActivity::class.java)
                    context.startActivity(payment)
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
                        text = "Refill 1",
                        fontFamily = Nunito_Bold,
                        fontSize = 22.sp,
                        color = Color(0xFF000000)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Thanh Toán Bằng ZaloPay!",
                            fontFamily = Nunito_Bold,
                            fontSize = 22.sp,
                            color = Color(0xFFFFA500)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth()
                .shadow(4.dp, shape = RoundedCornerShape(12.dp)),
            onClick = {
                goBack()
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFFFD528)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Trở lại", color = Color.White)
        }

//        Spacer(modifier = Modifier.height(16.dp))
//
//        Card(
//            modifier = Modifier
//                .width(275.dp)
//                .shadow(
//                    elevation = 8.dp,
//                    shape = RoundedCornerShape(16.dp)
//                )
//                .clip(RoundedCornerShape(16.dp))
//                .background(Color(0xFFFFF192))
//                .padding(horizontal = 16.dp, vertical = 12.dp)
//                .clickable {
//                    Toast.makeText(context, "Tính năng đang được phát triển", Toast.LENGTH_SHORT).show()
//                }
//        ) {
//            Row(
//                modifier = Modifier
//                    .background(Color(0xFFFFF192)),
//                verticalAlignment = Alignment.Top,
//                horizontalArrangement = Arrangement.Center,
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.heart),
//                    contentDescription = null,
//                    tint = Color.Red,
//                    modifier = Modifier.size(32.dp)
//                )
//
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth(),
//                    verticalArrangement = Arrangement.Center,
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    Text(
//                        text = "Refill 2/3",
//                        fontFamily = Nunito_Bold,
//                        fontSize = 22.sp,
//                        color = Color(0xFF000000)
//                    )
//
//                    Spacer(modifier = Modifier.height(4.dp))
//
//                    Text(
//                        text = "Watching ads",
//                        fontFamily = Nunito_Bold,
//                        fontSize = 22.sp,
//                        color = Color(0xFFFFA500)
//                    )
//                }
//            }
//        }
    }
}

//@Preview(showBackground = true, showSystemUi = false)
//@Composable
//fun PreviewHomeComponent() {
//    HomeComponent()
//}