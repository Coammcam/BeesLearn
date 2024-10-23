package fpl.md07.beeslearn.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SpeakingQuestionScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeesLearnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SpeakingQuestionContent()
                }
            }
        }
    }
}

@Composable
fun SpeakingQuestionContent() {
    var isRecording by remember { mutableStateOf(false) }
    var recordingProgress by remember { mutableStateOf(0f) }
    var secondsElapsed by remember { mutableStateOf(0) }
    var showActionButtons by remember { mutableStateOf(false) }

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Other content at the top (toolbar, bee, audio, etc.)
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Back button and score area (Toolbar)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                // Back button
                Image(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "Back",
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { /* Back action */ }
                )

                // Heart and coins row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Heart with background
                    Box(
                        modifier = Modifier
                            .background(color = Color(0xFFFFF59D), shape = RoundedCornerShape(50))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.heart),
                                contentDescription = "Heart",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "5", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF5D4037))
                        }
                    }

                    // Coins with background
                    Box(
                        modifier = Modifier
                            .background(color = Color(0xFFFFF59D), shape = RoundedCornerShape(50))
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.honey),
                                contentDescription = "Coins",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "100", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF5D4037))
                        }
                    }
                }
            }

            // Bee icon and audio message
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(top = 48.dp)
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Bee Icon",
                    modifier = Modifier.size(80.dp)
                )

                Spacer(modifier = Modifier.width(16.dp))
                Image(
                    painter = painterResource(id = R.drawable.volume),
                    contentDescription = "Play Audio",
                    modifier = Modifier.size(30.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(Color(0xFFFFF1B0))
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.adi),
                            contentDescription = "Waveform",
                            modifier = Modifier
                                .width(120.dp)
                                .height(40.dp)
                        )
                        Text(text = "x1", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (isRecording) {
                LinearProgressIndicator(
                    progress = recordingProgress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .background(Color(0xFFE3F2FD))
                        .padding(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "$secondsElapsed giây",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1976D2)
                    )
                }
            }

            if (showActionButtons) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE0E0E0))
                                .clickable { /* Logic to listen again */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.listen), // Substitua pelo seu ícone de "Listen again"
                                contentDescription = "Listen again",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Listen again",
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE0E0E0))
                                .clickable { /* Logic to delete */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete), // Substitua pelo ícone de "delete"
                                contentDescription = "Delete",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Delete",
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .size(60.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFE0E0E0))
                                .clickable { /* Logic to send */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.send), // Substitua pelo ícone de "Send"
                                contentDescription = "Send",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Send",
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                    }
                }
            }


        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFEB46))
                    .clickable {
                        if (isRecording) {
                            // Dừng ghi âm
                            isRecording = false
                            showActionButtons = true // Hiển thị các nút hành động sau khi dừng
                        } else {
                            // Bắt đầu ghi âm
                            isRecording = true
                            recordingProgress = 0f
                            secondsElapsed = 0
                            showActionButtons = false // Ẩn các nút trong khi ghi âm

                            // Bắt đầu bộ đếm thời gian và ghi âm
                            scope.launch {
                                while (isRecording) {
                                    delay(1000)
                                    secondsElapsed += 1
                                    recordingProgress = (secondsElapsed % 20) / 20f // Thanh tiến trình sẽ làm mới sau 20 giây
                                }
                            }
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mic),
                    contentDescription = "Speak",
                    modifier = Modifier.size(50.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = if (isRecording) "Đang ghi âm" else "Nhấn để nói",
                fontSize = 16.sp,
                color = Color(0xFF5D4037),
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSpeakingQuestionContent() {
    BeesLearnTheme {
        SpeakingQuestionContent()
    }
}

