package fpl.md07.beeslearn.screens.questions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
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
    var showMicButton by remember { mutableStateOf(true) } // Điều khiển hiển thị nút mic

    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Nội dung phía trên (toolbar, bee, audio, etc.)
        Column(
            modifier = Modifier.align(Alignment.TopCenter),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(48.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp) // Adjusted height to make it more compact
                    .graphicsLayer {
                        shadowElevation = 8.dp.toPx()
                        shape = RoundedCornerShape(16.dp)
                        clip = true
                        translationY = -8.dp.toPx() // Move the question box up slightly
                    }
                    .background(Color(0xFFFFF59D), shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo_without_text),
                        contentDescription = "Bee Icon",
                        modifier = Modifier.size(90.dp)
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
                            .background(Color(0xFFE3DFCA))
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Text(
                                text = "Quê hương em nổi tiếng khắp nơi với khu chợ nổi trên sông. Vốn bởi nơi đây có rất nhiều kênh rạch. Người dân di chuyển chủ yếu bằng thuyền, bằng ghe. Vậy nên, mới thành những buổi họp chợ trên mặt nước. Mới đầu, là để phục vụ người dân, sau nó trở thành một nét văn hóa đặc trưng hấp dẫn bà con tứ xứ đến xem. Trên mặt nước dập dềnh, những chiếc thuyền lớn có bé có tè tựu với đủ thứ mặt hàng thơm ngon, hấp dẫn. Có những chiếc thuyền đơn sơ, mộc mạc như người dân nơi đây.Cũng có những chiếc thuyền được trang trí cầu kì, sặc sỡ để thu hút khách du lịch. Nhưng điểm chung của tất cả những gian hàng di động ấy, chính là tấm lòng đôn hậu, chân chất của người bán hàng. Cả buổi chợ nơi đây lúc nào cũng rộn rã và vui tươi, xáo xào âm thanh người mua kẻ bán. Tất cả cứ lênh đênh, dập dềnh, thật là thú vị.",
                                fontSize = 14.sp,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .verticalScroll(rememberScrollState()),
                                textAlign = TextAlign.Center
                            )
                        }
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
        }

        // Hiển thị nút mic nếu `showMicButton` là true
        if (showMicButton) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 160.dp),
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
                                showMicButton = false // Ẩn nút mic sau khi dừng
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

        // Hiển thị các nút hành động nếu `showActionButtons` là true
        if (showActionButtons) {
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Đặt các nút ở dưới cùng thay vì trên
                    .padding(bottom = 16.dp), // Padding dưới để cách cạnh
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth()
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
                                .clickable { /* Logic để nghe lại */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.listen),
                                contentDescription = "Listen again",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Nghe lại lần nữa",
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
                                .clickable {
                                    // Logic để xóa và hiển thị lại nút mic
                                    showActionButtons = false
                                    showMicButton = true
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.delete),
                                contentDescription = "Delete",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Xóa",
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
                                .clickable { /* Logic để gửi */ },
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.send),
                                contentDescription = "Send",
                                modifier = Modifier.size(24.dp),
                                tint = Color.Black
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Gửi",
                            fontSize = 12.sp,
                            color = Color.Black
                        )
                    }
                }
            }
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
