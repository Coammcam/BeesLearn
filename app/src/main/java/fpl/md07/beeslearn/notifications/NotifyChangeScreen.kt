package fpl.md07.beeslearn.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.BackComponent
import java.util.*

@Composable
fun NotifyChangeScreen(
    navController: NavController
) {

    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val preferencesHelper = remember { PreferencesHelper(context) }
    val (initialHour, initialMinute) = preferencesHelper.getTime() // Lấy giờ và phút lưu trữ
    var selectedHour by remember { mutableStateOf(initialHour) }
    var selectedMinute by remember { mutableStateOf(initialMinute) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {

            BackComponent(navController)
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painterResource(id = R.drawable.bell_rmbg),
                contentDescription = "Bell",
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Xin chào,\nhãy đặt thông báo hằng ngày!",
                color = Color.Black,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = { showDialog = true },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(R.color.newOuterColor),
                    contentColor = Color.White // Màu chữ
                ),
                modifier = Modifier
                    .width(200.dp)
                    .padding(horizontal = 32.dp)
            ) {
                Text("${String.format("%02d", selectedHour)}:${String.format("%02d", selectedMinute)}")
            }
        }

        CustomTimePickerDialog(
            showDialog = showDialog,
            onDismiss = { showDialog = false },
            onTimeSelected = { hour, minute ->
                selectedHour = hour
                selectedMinute = minute
                preferencesHelper.saveTime(hour, minute)
            }
        )
        Button(
            onClick = {
                triggerCustomNotification(context, selectedHour, selectedMinute)
                navController.navigate("settingScreen")
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.newInnerColor),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
                .height(50.dp)
        ) {
            Text(
                "Turn on",
                color = Color.White
            )
        }

        Button(
            onClick = {
                navController.navigate("settingScreen")
                Toast.makeText(context, "Bạn đã tắt thông báo!", Toast.LENGTH_SHORT).show()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.secondary_color),
                contentColor = Color.White
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
                .height(50.dp)
        ) {
            Text(
                "Turn Off",
                color = Color.White
            )
        }

    }
}
@Composable
fun CustomTimePickerDialog(
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onTimeSelected: (hour: Int, minute: Int) -> Unit
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = onDismiss,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface,
                tonalElevation = 8.dp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    var selectedHour by remember { mutableStateOf(15) }
                    var selectedMinute by remember { mutableStateOf(0) }

                    Text(
                        text = "Chọn thời gian",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Hour picker
                        NumberPicker(
                            value = selectedHour,
                            onValueChange = { selectedHour = it },
                            range = 0..23,
                            modifier = Modifier.weight(1f)
                        )

                        Text(
                            text = ":",
                            style = MaterialTheme.typography.headlineLarge,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )

                        // Minute picker
                        NumberPicker(
                            value = selectedMinute,
                            onValueChange = { selectedMinute = it },
                            range = 0..59,
                            modifier = Modifier.weight(1f)
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 24.dp),
                        horizontalArrangement = Arrangement.End
                    ) {
                        TextButton(onClick = onDismiss) {
                            Text("Hủy")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Button(
                            onClick = {
                                onTimeSelected(selectedHour, selectedMinute)
                                onDismiss()
                            }
                        ) {
                            Text("Xác nhận")
                        }
                    }
                }
            }
        }
    }
}
@Composable
private fun NumberPicker(
    value: Int,
    onValueChange: (Int) -> Unit,
    range: IntRange,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {
                if (value < range.last) onValueChange(value + 1)
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Tăng"
            )
        }

        Text(
            text = String.format("%02d", value),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(vertical = 8.dp)
        )

        IconButton(
            onClick = {
                if (value > range.first) onValueChange(value - 1)
            }
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Giảm"
            )
        }
    }
}
private fun triggerCustomNotification(context: Context, hour: Int, minute: Int) {
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, hour)
        set(Calendar.MINUTE, minute)
        set(Calendar.SECOND, 0)
    }

    if (calendar.before(Calendar.getInstance())) {
        // Nếu thời gian chọn đã qua, đặt thời gian cho ngày mai
        calendar.add(Calendar.DAY_OF_YEAR, 1)
    }

    val intent = Intent(context, ReminderBroadcastReceiver::class.java).apply {
        action = "ACTION_CUSTOM_NOTIFICATION"
    }
    val pendingIntent = PendingIntent.getBroadcast(
        context,
        0,
        intent,
        PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
    )

    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    alarmManager.setExactAndAllowWhileIdle(
        AlarmManager.RTC_WAKEUP,
        calendar.timeInMillis,
        pendingIntent
    )

    Toast.makeText(context, "Nhắc nhở được đặt lúc $hour:$minute", Toast.LENGTH_SHORT).show()
}

@Preview(showBackground = true)
@Composable
fun NotifyChangeScreenPreview() {
    val mockNavController = rememberNavController()
    NotifyChangeScreen(navController = mockNavController)
}

