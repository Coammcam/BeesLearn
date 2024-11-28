package fpl.md07.beeslearn.screens.questions

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.components.ConfirmQuestionNo
import fpl.md07.beeslearn.components.ConfirmQuestionYes
import fpl.md07.beeslearn.models.AnswerResult
import fpl.md07.beeslearn.models.GrammarQuestionModel
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import kotlinx.coroutines.delay

@Composable
fun ArrangeSentenceScreen(grammarQuestionModel: GrammarQuestionModel, onComplete: () -> Unit, goBack: () -> Unit) {
    var sentenceParts by remember { mutableStateOf(emptyList<String>()) }
    val selectedParts by remember { mutableStateOf(emptyList<String>().toMutableList()) }
    var result: AnswerResult? by remember { mutableStateOf(null) }

    LaunchedEffect(grammarQuestionModel) {
        sentenceParts = grammarQuestionModel.question.split(" ").shuffled()
        println(sentenceParts)
    }

    LaunchedEffect(sentenceParts) {
        if (sentenceParts.isEmpty()) {
            val completeSentence = selectedParts.joinToString(" ")
            println(completeSentence)

            if (completeSentence == grammarQuestionModel.question) {
                println("correct")
                result = AnswerResult.CORRECT
            } else {
                println("incorrect")
                result = AnswerResult.INCORRECT
            }
            println("Donezo")
            delay(1000)
            onComplete()
            selectedParts.clear()
            result = null
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Arrange the words in correct order",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            fontFamily = Nunito_Bold,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        Text(
            grammarQuestionModel.meaning,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(48.dp))

        // Box to hold the arranged sentence
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
                // Using a Column to arrange words in a flow-like manner
                Column(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .fillMaxWidth()
                ) {
                    var rowWords = mutableListOf<String>()
                    selectedParts.forEachIndexed { index, part ->
                        rowWords.add(part)
                        // When 3 words are collected, create a new row
                        if (rowWords.size == 3 || index == selectedParts.size - 1) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(bottom = 8.dp),
                                horizontalArrangement = Arrangement.spacedBy(4.dp)  // Reduced space between words
                            ) {
                                rowWords.forEach { word ->
                                    DraggableWordOption(word = word, isSelected = false, onClick = {
                                        selectedParts.remove(word)
                                        sentenceParts = sentenceParts + word
                                    })
                                }
                            }
                            rowWords.clear()  // Reset the list for the next row
                        }
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(48.dp))

        Box {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),  // Reduced space between word rows
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                sentenceParts.chunked(3).forEach { chunk ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)  // Reduced space between words
                    ) {
                        chunk.forEach { word ->
                            DraggableWordOption(word = word, isSelected = false, onClick = {
                                selectedParts.add(word)
                                sentenceParts = sentenceParts - word
                            })
                        }
                    }
                }
            }
            if (result == AnswerResult.CORRECT) {
                ConfirmQuestionYes()
            } else if (result == AnswerResult.INCORRECT) {
                ConfirmQuestionNo()
            }
        }
    }
}

@Composable
fun DraggableWordOption(word: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .background(
                color = if (isSelected) Color(0xFFFFE082) else Color(0xFFFFF59D),
                shape = RoundedCornerShape(8.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 4.dp, vertical = 2.dp)  // Reduced padding between the word and border
    ) {
        Text(
            text = word,
            fontSize = 18.sp,
            color = Color(0xFF5D4037),
            fontWeight = FontWeight.Bold,
            fontFamily = Nunito_Bold,
            modifier = Modifier.padding(6.dp)  // Adjust padding for text inside the box
        )
    }
}

//
//@Preview(showBackground = true)
//@Composable
//fun ArrangeSentencePreview() {
//    val navController = rememberNavController()
//    ArrangeSentenceScreen(){}
//}
