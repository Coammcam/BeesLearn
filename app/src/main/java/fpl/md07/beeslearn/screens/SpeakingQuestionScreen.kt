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
    // State to track recording progress (from 0 to 1)
    var isRecording by remember { mutableStateOf(false) }
    var recordingProgress by remember { mutableStateOf(0f) }
    var secondsElapsed by remember { mutableStateOf(0) }

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
                    painter = painterResource(id = R.drawable.ic_back), // Replace with your back icon resource
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
                            .background(
                                color = Color(0xFFFFF59D),
                                shape = RoundedCornerShape(50)
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.heart), // Replace with your heart icon
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
                            .background(
                                color = Color(0xFFFFF59D),
                                shape = RoundedCornerShape(50)
                            )
                            .padding(horizontal = 12.dp, vertical = 4.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = painterResource(id = R.drawable.honey), // Replace with your coin icon
                                contentDescription = "Coins",
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(text = "100", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF5D4037))
                        }
                    }
                }
            }

            // Bee icon and audio message (moved further down and centered horizontally)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center, // Center horizontally
                modifier = Modifier
                    .padding(top = 48.dp) // Further increase top padding to move this section down
                    .fillMaxWidth()
            ) {
                // Bee Icon
                Image(
                    painter = painterResource(id = R.drawable.logo), // Replace with your bee icon
                    contentDescription = "Bee Icon",
                    modifier = Modifier.size(80.dp)
                )

                // Volume icon outside waveform box
                Spacer(modifier = Modifier.width(16.dp)) // Add space between the bee and the volume icon
                Image(
                    painter = painterResource(id = R.drawable.volume), // Replace with your volume icon
                    contentDescription = "Play Audio",
                    modifier = Modifier.size(30.dp)
                )

                // Waveform inside rounded background
                Spacer(modifier = Modifier.width(16.dp)) // Add space between the volume and waveform
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp)) // Rounded corners for the waveform background
                        .background(Color(0xFFFFF1B0)) // Light yellow background
                        .padding(horizontal = 12.dp, vertical = 8.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.adi), // Replace with your waveform icon
                            contentDescription = "Waveform",
                            modifier = Modifier
                                .width(120.dp)
                                .height(40.dp) // Adjusted height
                        )
                        Text(text = "x1", fontSize = 14.sp, fontWeight = FontWeight.Bold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Recording progress bar
            if (isRecording) {
                LinearProgressIndicator(
                    progress = recordingProgress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            // Timer in a rounded box
            if (isRecording) {
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

        // Mic and text at the bottom
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter) // This aligns the mic and text to the bottom center
                .padding(bottom = 16.dp), // Adjust bottom padding as needed
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Mic icon inside a circle
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFFFEB46))
                    .clickable {
                        isRecording = true
                        recordingProgress = 0f
                        secondsElapsed = 0

                        // Simulate recording progress and seconds elapsed
                        scope.launch {
                            while (recordingProgress < 1f) {
                                delay(1000) // Update every second
                                secondsElapsed += 1
                                recordingProgress += 0.05f // Adjust speed of progress bar
                            }
                            isRecording = false // Stop recording when done
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.mic), // Replace with your mic icon
                    contentDescription = "Speak",
                    modifier = Modifier.size(50.dp)
                )
            }

            // Text below the mic button
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Hold to speak",
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
