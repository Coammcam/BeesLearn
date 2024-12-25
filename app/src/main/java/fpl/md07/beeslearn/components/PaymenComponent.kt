package fpl.md07.beeslearn.components


import android.content.Context
import android.content.Intent
import android.net.Uri
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@Composable
fun PaymentComponent(navController: NavController) {
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
            text = "Gói 1 Tháng",
            price = "99,000 VNĐ",
            onClick = {
                startZaloPayPayment(context, 99000)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Button 3 tháng
        ButtonCardPay(
            text = "Gói 3 Tháng",
            price = "255,000 VNĐ",
            onClick = {
                startZaloPayPayment(context, 255000)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Third button: 6 Months
        ButtonCardPay(
            text = "Gói 6 Tháng",
            price = "450,000 VNĐ",
            onClick = {
                startZaloPayPayment(context, 450000)
            }
        )
    }
}

//fun startZaloPayPayment(context: Context, amount: Int) {
//    // Dữ liệu giả lập để QC
//    val paymentData = mapOf(
//        "app_id" to "554", // Thay bằng app_id thực tế
//        "amount" to amount.toString(),
//        "description" to "Thanh toán gói học phí",
//        "zptranstoken" to "mock_transaction_token" // Dữ liệu giả QC
//    )
//
//    // Khởi động Intent để thanh toán qua ZaloPay
//    val intent = Intent(Intent.ACTION_VIEW).apply {
//        data = Uri.parse("zalopay://payment?${paymentData.map { "${it.key}=${it.value}" }.joinToString("&")}")
//    }
//
//    if (intent.resolveActivity(context.packageManager) != null) {
//        context.startActivity(intent)
//    } else {
//        Toast.makeText(context, "Không tìm thấy ứng dụng ZaloPay trên thiết bị", Toast.LENGTH_SHORT).show()
//    }
//}

fun startZaloPayPayment(context: Context, amount: Int) {
    val paymentData = mapOf(
        "app_id" to "554", // app_id của bạn
        "amount" to amount.toString(),
        "description" to "Thanh toán gói học phí",
        "zptranstoken" to "mock_transaction_token" // Giả lập token giao dịch cho mục đích kiểm thử
    )
    val queryString = paymentData.map { "${it.key}=${it.value}" }.joinToString("&")
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("zalopay://payment?$queryString"))

    if (intent.resolveActivity(context.packageManager) != null) {
        context.startActivity(intent)
    } else {
        Toast.makeText(context, "Không tìm thấy ứng dụng ZaloPay trên thiết bị", Toast.LENGTH_SHORT).show()
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
                .background(Color(0xFFFFF192))
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically, // Đặt căn giữa theo chiều dọc
            horizontalArrangement = Arrangement.Center // Đặt căn giữa theo chiều ngang
        ) {
            Icon(
                painter = painterResource(id = R.drawable.heart8), // You can update this icon if needed
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier
                    .size(45.dp)
                    .align(Alignment.CenterVertically) // Đảm bảo Icon được căn giữa dọc trong Row
            )

            Spacer(modifier = Modifier.width(8.dp)) // Khoảng cách giữa Icon và Text

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
    val navController = rememberNavController()
    PaymentComponent(navController)
}