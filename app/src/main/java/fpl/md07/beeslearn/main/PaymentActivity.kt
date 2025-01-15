package fpl.md07.beeslearn.main

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.lifecycleScope
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.api.AppInfo.APP_ID
import fpl.md07.beeslearn.api.CreateOrder
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import kotlinx.coroutines.launch
import vn.zalopay.sdk.Environment
import vn.zalopay.sdk.ZaloPayError
import vn.zalopay.sdk.ZaloPaySDK
import vn.zalopay.sdk.listeners.PayOrderListener

class PaymentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContent(){
            PaymentComponent()
        }
    }
    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        ZaloPaySDK.getInstance().onResult(intent)
    }
}

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
        PaymentButton(
            text = "Gói 1 Tháng",
            amount = 1000,
            context = context,


            )

        Spacer(modifier = Modifier.height(16.dp))

        PaymentButton(
            text = "Gói 3 Tháng",
            amount = 2000,
            context = context,

            )

        Spacer(modifier = Modifier.height(16.dp))

        PaymentButton(
            text = "Gói 6 Tháng",
            amount = 3000,
            context = context
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth()
                .shadow(4.dp, shape = RoundedCornerShape(12.dp)),
            onClick = {

            },
            colors = ButtonDefaults.buttonColors(Color(0xFFFFD528)),
            shape = RoundedCornerShape(12.dp)
        ) {
            Text(text = "Trở lại", color = Color.White)
        }
    }
}

@Composable
fun PaymentButton(
    text: String,
    amount: Int,
    context: Context,
) {
    val activity = context as? ComponentActivity ?: return
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

                fun createNotificationChannel(context: Context) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        val channel = NotificationChannel(
                            "beeslearn_notifications",
                            "BeesLearn Notifications",
                            NotificationManager.IMPORTANCE_HIGH
                        ).apply {
                            description = "Thông báo từ ứng dụng BeesLearn"
                        }
                        val manager =
                            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                        manager.createNotificationChannel(channel)
                    }
                }

                // Gửi thông báo
                fun sendNotification(context: Context, title: String, message: String) {
                    val channelId = "beeslearn_notifications"
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        val permission = Manifest.permission.POST_NOTIFICATIONS
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

                val orderApi = CreateOrder()
                activity.lifecycleScope.launch {
                    try {
                        val data = orderApi.createOrder(totalString)
                        println("Amount $totalString")
                        println("Zalopay $data")
                        val code: String = data.getString("return_code")
                        println("ZaloPay Order created successfully: $code")

                        if (code == "1") {
                            println("test")
                            val token = data.getString("zp_trans_token")
                            ZaloPaySDK
                                .getInstance()
                                .payOrder(
                                    activity, token, "demozpdk://app",
                                    object : PayOrderListener {
                                        override fun onPaymentSucceeded(
                                            payUrl: String?,
                                            transToken: String?,
                                            appTransID: String?
                                        ) {
                                            Toast.makeText(context, "Thanh toán thành công", Toast.LENGTH_SHORT).show()
                                            println("ZaloPay Payment succeeded: payUrl=$payUrl, transToken=$transToken, appTransID=$appTransID")
//                                            navController.navigate("homeScreen") {
//                                                popUpTo("currentScreen") { inclusive = true } // Xóa màn hình hiện tại khỏi stack
//                                            }
                                            sendNotification(
                                                context,
                                                "BeesLearn",
                                                "Bạn đã thanh toán thành công bằng ZaloPay!"
                                            )
                                        }

                                        override fun onPaymentCanceled(
                                            payUrl: String?,
                                            transToken: String?
                                        ) {
                                            Toast.makeText(context, "Hủy thanh toán", Toast.LENGTH_SHORT).show()
                                            Log.d("ZaloPay", "Payment canceled: payUrl=$payUrl, transToken=$transToken")
                                            sendNotification(
                                                context,
                                                "BeesLearn",
                                                "Bạn Đã Hủy Thanh Toán"
                                            )
                                        }

                                        override fun onPaymentError(
                                            error: ZaloPayError?,
                                            payUrl: String?,
                                            transToken: String?
                                        ) {
                                            Toast.makeText(context, "Lỗi thanh toán!", Toast.LENGTH_SHORT).show()
                                            Log.e("ZaloPayError", "Payment error: payUrl=$payUrl, transToken=$transToken")
                                            sendNotification(
                                                context,
                                                "BeesLearn",
                                                "Lôĩ Thanh Toán!"
                                            )
                                        }
                                    }
                                )
                        } else {
                            Toast
                                .makeText(context, "Không thể tạo đơn hàng", Toast.LENGTH_SHORT).show()
                        }

                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast
                            .makeText(context, "Đã xảy ra lỗi", Toast.LENGTH_SHORT)
                            .show()
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
