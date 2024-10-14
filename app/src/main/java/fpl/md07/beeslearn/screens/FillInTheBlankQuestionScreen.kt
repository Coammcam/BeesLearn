package fpl.md07.beeslearn.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme
import fpl.md07.beeslearn.data.fillInTheBlankQuestions // Import fake data

class FillInTheBlankQuestionScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeesLearnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FillInTheBlankScreen()
                }
            }
        }
    }
}

@Composable
fun FillInTheBlankScreen() {
    var selectedWord by remember { mutableStateOf("") }

    // Use the first question from the fake data
    val currentQuestion = fillInTheBlankQuestions[0]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back button and score area
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconButton(onClick = { /* Back action */ }) {
                Icon(
                    painter = painterResource(id = fpl.md07.beeslearn.R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color(0xFFB71C1C),
                    modifier = Modifier.size(24.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                // Heart Icon with Background
                ScoreItem(iconResId = fpl.md07.beeslearn.R.drawable.heart, value = "5", tint = Color(0xFFFF1744))
                // Coins Icon with Background
                ScoreItem(iconResId = fpl.md07.beeslearn.R.drawable.honey, value = "100", tint = Color(0xFFFFD700))
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Question box with fill-in-the-blank
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .background(Color(0xFFFFF59D), shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Display the sentence with the selected word in the blank
            Text(
                text = currentQuestion.questionText.replace("____", if (selectedWord.isEmpty()) "______" else selectedWord),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Word options
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            currentQuestion.wordOptions.chunked(3).forEach { rowWords ->
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    rowWords.forEach { word ->
                        WordOptionButton(
                            word,
                            selectedWord == word,
                            onClick = { toggleWordSelection(word, selectedWord, onSelect = { selectedWord = it }) },
                            Modifier.weight(1f)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp)) // Add extra space at the bottom
    }
}

@Composable
fun WordOptionButton(
    word: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF59D)),
        border = if (isSelected) BorderStroke(2.dp, Color(0xFF5D4037)) else null,
        modifier = modifier
            .height(60.dp)
            .padding(4.dp)
    ) {
        Text(
            text = word,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037)
        )
    }
}

@Composable
fun ScoreItem(iconResId: Int, value: String, tint: Color) {
    Box(
        modifier = Modifier
            .background(Color(0xFFFFF59D), shape = RoundedCornerShape(50))
            .padding(horizontal = 12.dp, vertical = 4.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = iconResId),
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = value, fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF5D4037))
        }
    }
}

fun toggleWordSelection(word: String, selectedWord: String, onSelect: (String) -> Unit) {
    if (word == selectedWord) {
        onSelect("") // Clear the selection
    } else {
        onSelect(word) // Select the clicked word
    }
}

@Preview(showBackground = true)
@Composable
fun FillInTheBlankPreview() {
    BeesLearnTheme {
        FillInTheBlankQuestionScreen()
    }
}
