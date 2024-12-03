package fpl.md07.beeslearn.screens.questions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold


//class ListeningQuestionScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            BeesLearnTheme {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    ListeningQuestionContent()
//                }
//            }
//        }
//    }
//}

@Composable
fun ListeningQuestionContent() {
    var selectedAnswer by remember { mutableStateOf<String?>(null) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
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

            }

            Spacer(modifier = Modifier.height(48.dp))

            // Answer options arranged in a Column with Row for two answers each
            val answers = listOf("A", "B", "C", "D") // Assuming these are your answer labels
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                answers.chunked(2).forEach { rowAnswers ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        rowAnswers.forEach { answer ->
                            AnswerButtonListen(
                                answerText = answer, // Assuming answer text is the same as the label
                                isSelected = selectedAnswer == answer,
                                onClick = { selectedAnswer = answer },
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnswerButtonListen(
    answerText: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF59D)),
        border = if (isSelected) BorderStroke(2.dp, Color(0xFF5D4037)) else BorderStroke(0.dp, Color.Transparent),
        modifier = modifier
            .height(100.dp)
            .graphicsLayer {
                shadowElevation = 8.dp.toPx()
                shape = RoundedCornerShape(16.dp)
                clip = true
                translationY = 8.dp.toPx()
            }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = answerText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037),
                fontFamily = Nunito_Bold,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewListeningQuestionContent() {
    ListeningQuestionContent()
}
