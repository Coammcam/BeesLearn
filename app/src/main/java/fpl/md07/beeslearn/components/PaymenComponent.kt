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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
fun PaymentComponent() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Button 2 tháng
        ButtonCardPay(
            text = "1 Month",
            price = "20$",
            onClick = {
                Toast.makeText(context, "You selected 1 Month package", Toast.LENGTH_SHORT).show()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button 3 tháng
        ButtonCardPay(
            text = "3 Months",
            price = "50$",
            onClick = {
                Toast.makeText(context, "You selected 3 Months package", Toast.LENGTH_SHORT).show()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Third button: 6 Months
        ButtonCardPay(
            text = "6 Months",
            price = "90$",
            onClick = {
                Toast.makeText(context, "You selected 6 Months package", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
fun ButtonCardPay(text: String, price: String, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .width(275.dp)
            .shadow(
                elevation = 8.dp, shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFFFF192))
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFFFF192)),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.heart8), // You can update this icon if needed
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
                    text = text,
                    fontFamily = Nunito_Bold,
                    fontSize = 22.sp,
                    color = Color(0xFF000000)
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = price,
                    fontFamily = Nunito_Bold,
                    fontSize = 22.sp,
                    color = Color(0xFFFFA500)
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun PayComponentPreview() {
    PaymentComponent()
}