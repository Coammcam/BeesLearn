package fpl.md07.beeslearn.screens.questions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.data.connectingWordQuestions
import fpl.md07.beeslearn.models.ConnectingWordQuestionModel
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

class ConnectingWordQuestionScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeesLearnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConnectingWordScreen()
                }
            }
        }
    }
}

@Composable
fun ConnectingWordScreen() {
    var selectedLeft by remember { mutableStateOf("") }
    var selectedRight by remember { mutableStateOf("") }
    var matchedPairs by remember { mutableStateOf(listOf<Pair<String, String>>()) }
    var incorrectPairs by remember { mutableStateOf(listOf<Pair<String, String>>()) }
    val scrollState = rememberScrollState()

    val currentQuestion = connectingWordQuestions[0]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Text(
            text = "Match column A with column B",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            fontFamily = Nunito_Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFFFF59D), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("A", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFFFF59D), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("B", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                currentQuestion.columnA.forEach { text ->
                    if (text !in matchedPairs.map { it.first }) {
                        SelectableMatchingItem(
                            text = text,
                            isSelected = selectedLeft == text,
                            isCorrect = incorrectPairs.none { it.first == text },
                            onClick = {
                                selectedLeft = if (selectedLeft == text) "" else text
                                checkMatch(
                                    selectedLeft,
                                    selectedRight,
                                    currentQuestion,
                                    { matched -> matchedPairs = matched },
                                    { incorrect -> incorrectPairs = incorrect },
                                    { left, right ->
                                        selectedLeft = left
                                        selectedRight = right
                                    }
                                )
                            },
                            Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                currentQuestion.columnB.forEach { text ->
                    if (text !in matchedPairs.map { it.second }) {
                        SelectableMatchingItem(
                            text = text,
                            isSelected = selectedRight == text,
                            isCorrect = incorrectPairs.none { it.second == text },
                            onClick = {
                                selectedRight = if (selectedRight == text) "" else text
                                checkMatch(
                                    selectedLeft,
                                    selectedRight,
                                    currentQuestion,
                                    { matched -> matchedPairs = matched },
                                    { incorrect -> incorrectPairs = incorrect },
                                    { left, right ->
                                        selectedLeft = left
                                        selectedRight = right
                                    }
                                )
                            },
                            Modifier.padding(vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

fun checkMatch(
    selectedLeft: String,
    selectedRight: String,
    currentQuestion: ConnectingWordQuestionModel,
    updateMatchedPairs: (List<Pair<String, String>>) -> Unit,
    updateIncorrectPairs: (List<Pair<String, String>>) -> Unit,
    resetSelection: (String, String) -> Unit
) {
    if (selectedLeft.isNotEmpty() && selectedRight.isNotEmpty()) {
        val isCorrect = currentQuestion.pairs.contains(selectedLeft to selectedRight)
        if (isCorrect) {
            updateMatchedPairs(listOf(selectedLeft to selectedRight))
        } else {
            updateIncorrectPairs(listOf(selectedLeft to selectedRight))
        }
        resetSelection("", "")
    }
}

@Composable
fun SelectableMatchingItem(
    text: String,
    isSelected: Boolean,
    isCorrect: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = if (isCorrect) Color(0xFFFFF59D) else Color(0xFFFFCDD2),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 2.dp,
                color = if (isSelected) Color(0xFF5D4037) else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            fontFamily = Nunito_Bold
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ConnectingWordPreview() {
    ConnectingWordScreen()
}
