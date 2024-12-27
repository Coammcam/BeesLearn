package fpl.md07.beeslearn.components

import android.app.Activity
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.api.AppInfo.APP_ID
import fpl.md07.beeslearn.api.CreateOrder
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import kotlinx.coroutines.launch
import vn.zalopay.sdk.Environment
import vn.zalopay.sdk.ZaloPayError
import vn.zalopay.sdk.ZaloPaySDK
import vn.zalopay.sdk.listeners.PayOrderListener
import fpl.md07.beeslearn.R

@Composable
fun PaymentComponent(navController: NavHostController) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PaymentButton(
            text = "Gói 1 Tháng",
            amount = 99000,
            context = context
        )

        Spacer(modifier = Modifier.height(16.dp))

        PaymentButton(
            text = "Gói 3 Tháng",
            amount = 255000,
            context = context
        )

        Spacer(modifier = Modifier.height(16.dp))

        PaymentButton(
            text = "Gói 6 Tháng",
            amount = 450000,
            context = context
        )
    }
}

@Composable
fun PaymentButton(
    text: String,
    amount: Int,
    context: Context
) {
    val activity = context as? ComponentActivity ?: return // Bảo đảm context là ComponentActivity
    val totalString = amount.toString()

    Card(
        modifier = Modifier
            .width(275.dp)
            .shadow(
                elevation = 8.dp, shape = RoundedCornerShape(16.dp)
            )
            .clip(RoundedCornerShape(16.dp))
            .background(Color(0xFFFFF192))
            .clickable {
                Log.d("ZaloPay", "Button clicked")

                try {
                    ZaloPaySDK.init(APP_ID, Environment.SANDBOX)
                    Log.d("ZaloPayInit", "ZaloPay SDK initialized successfully")
                } catch (e: Exception) {
                    Log.e("ZaloPayInitError", "Error initializing ZaloPay SDK: ${e.message}")
                    e.printStackTrace()
                    return@clickable
                }

                val orderApi = CreateOrder()

                activity.lifecycleScope.launch {
                    try {
                        val data = orderApi.createOrder(totalString)
                        Log.d("Amount", totalString)

                        val code = data.getString("return_code")
                        Log.d("ZaloPay", "Order created successfully: $code")

                        if (code == "1") {
                            val token = data.getString("zp_trans_token")
                            ZaloPaySDK.getInstance().payOrder(
                                activity,
                                token,
                                "demozpdk://app",
                                object : PayOrderListener {
                                    override fun onPaymentSucceeded(
                                        payUrl: String?,
                                        transToken: String?,
                                        appTransID: String?
                                    ) {
                                        Toast.makeText(
                                            context,
                                            "Thanh toán thành công",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Log.d(
                                            "ZaloPay",
                                            "Payment succeeded: payUrl=$payUrl, transToken=$transToken, appTransID=$appTransID"
                                        )
                                    }

                                    override fun onPaymentCanceled(
                                        payUrl: String?,
                                        transToken: String?
                                    ) {
                                        Toast.makeText(
                                            context,
                                            "Hủy thanh toán",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Log.d(
                                            "ZaloPay",
                                            "Payment canceled: payUrl=$payUrl, transToken=$transToken"
                                        )
                                    }

                                    override fun onPaymentError(
                                        error: ZaloPayError?,
                                        payUrl: String?,
                                        transToken: String?
                                    ) {
                                        Toast.makeText(
                                            context,
                                            "Lỗi thanh toán",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        Log.e(
                                            "ZaloPayError",
                                            "Payment error: payUrl=$payUrl, transToken=$transToken"
                                        )
                                    }
                                })
                        } else {
                            Toast.makeText(
                                context,
                                "Không thể tạo đơn hàng",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(context, "Đã xảy ra lỗi", Toast.LENGTH_SHORT).show()
                        Log.e("ZaloPayError", "Exception: ${e.message}")
                    }
                }
            }
    ) {
        Row(
            modifier = Modifier
                .background(Color(0xFFFFF192))
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.heart8),
                contentDescription = null,
                tint = Color.Red,
                modifier = Modifier
                    .size(45.dp)
                    .align(Alignment.CenterVertically)
            )

            Spacer(modifier = Modifier.width(8.dp))

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
                    text = "${amount}đ",
                    fontFamily = Nunito_Bold,
                    fontSize = 22.sp,
                    color = Color(0xFFFFA500)
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewPaymentComponent() {
    val navController = rememberNavController()
    PaymentComponent(navController)
}
