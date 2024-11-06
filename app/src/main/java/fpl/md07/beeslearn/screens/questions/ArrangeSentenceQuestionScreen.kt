package fpl.md07.beeslearn.screens.questions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme
import fpl.md07.beeslearn.data.arrangeSentenceQuestions // Import fake data
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

class ArrangeSentenceQuestionScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeesLearnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ArrangeSentenceScreen()
                }
            }
        }
    }
}

@Composable
fun ArrangeSentenceScreen() {
    val currentQuestion = arrangeSentenceQuestions[0]
    var sentenceParts by remember { mutableStateOf(currentQuestion.sentenceParts) }
    var selectedParts by remember { mutableStateOf(mutableListOf<String>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            IconButton(onClick = { /* Back action */ }) {
                Icon(
                    painter = painterResource(id = fpl.md07.beeslearn.R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color(0xFFB71C1C),
                    modifier = Modifier.size(24.dp)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .background(Color(0xFFFFF59D), shape = RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = fpl.md07.beeslearn.R.drawable.heart),
                            contentDescription = "Heart",
                            tint = Color(0xFFFF1744),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "5",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF5D4037),
                            fontFamily = Nunito_Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Box(
                    modifier = Modifier
                        .background(Color(0xFFFFF59D), shape = RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = fpl.md07.beeslearn.R.drawable.honey),
                            contentDescription = "Coins",
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "100",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF5D4037),
                            fontFamily = Nunito_Bold
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .graphicsLayer {
                    shadowElevation = 8.dp.toPx()
                    shape = RoundedCornerShape(16.dp)
                    clip = true
                    translationY = -8.dp.toPx()
                }
                .background(Color(0xFFFFF59D), shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            if (selectedParts.isEmpty()) {
                Divider(
                    color = Color(0xFF5D4037),
                    thickness = 2.dp,
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .padding(horizontal = 8.dp)
                )
            } else {
                Row(
                    modifier = Modifier.padding(horizontal = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    selectedParts.forEach { part ->
                        DraggableWordOption(
                            word = part,
                            isSelected = false,
                            onClick = {
                                selectedParts.remove(part)
                                sentenceParts = sentenceParts + part
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            sentenceParts.chunked(3).forEach { chunk ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    chunk.forEach { word ->
                        DraggableWordOption(
                            word = word,
                            isSelected = false,
                            onClick = {
                                selectedParts.add(word)
                                sentenceParts = sentenceParts - word
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DraggableWordOption(word: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(
                color = if (isSelected) Color(0xFFFFE082) else Color(0xFFFFF59D),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 4.dp, vertical = 4.dp)
    ) {
        Text(
            text = word,
            fontSize = 16.sp,
            color = Color(0xFF5D4037),
            fontWeight = FontWeight.Bold,
            fontFamily = Nunito_Bold
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ArrangeSentencePreview() {
    BeesLearnTheme {
        ArrangeSentenceScreen()
    }
}
