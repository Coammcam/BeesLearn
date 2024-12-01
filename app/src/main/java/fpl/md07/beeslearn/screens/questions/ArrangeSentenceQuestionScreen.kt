package fpl.md07.beeslearn.screens.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import com.google.accompanist.flowlayout.FlowRow


@Composable
fun ArrangeSentenceScreen(grammarQuestionModel: GrammarQuestionModel, onComplete: () -> Unit) {
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
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize(),
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
            Box(modifier = Modifier
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
                            .padding(8.dp)
                    )
                } else {
                    // Using FlowRow to arrange words with reduced spacing
                    FlowRow(
                        modifier = Modifier.fillMaxWidth().padding(8.dp),
                        mainAxisSpacing = 4.dp, // Reduced horizontal space between words
                        crossAxisSpacing = 4.dp // Reduced vertical space between words
                    ) {
                        selectedParts.forEach { word ->
                            DraggableWordOption(word = word, isSelected = false, onClick = {
                                selectedParts.remove(word)
                                sentenceParts = sentenceParts + word
                            })
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
                    verticalArrangement = Arrangement.spacedBy(4.dp),  // Further reduced space between rows
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        mainAxisSpacing = 2.dp,  // Reduced space between words horizontally
                        crossAxisSpacing = 2.dp // Reduced space between words vertically
                    ) {
                        sentenceParts.forEach { word ->
                            DraggableWordOption(word = word, isSelected = false, onClick = {
                                selectedParts.add(word)
                                sentenceParts = sentenceParts - word
                            })
                        }
                    }
                }
            }
            if (result == AnswerResult.CORRECT) {
                ConfirmQuestionYes(
                    Continue = {
                        onComplete()
                        selectedParts.clear()
                        result = null
                    }
                )
            } else if (result == AnswerResult.INCORRECT) {
                ConfirmQuestionNo(
                    Continue = {
                        onComplete()
                        selectedParts.clear()
                        result = null
                    }
                )
            }
        }
    }
}

@Composable
fun DraggableWordOption(word: String, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .border(
                width = 2.dp, // Độ dày của viền
                color = if (isSelected) Color(0xFF795548) else Color(0xFFBCAAA4), // Màu viền tùy thuộc vào trạng thái
                shape = RoundedCornerShape(8.dp) // Hình dạng của viền
            )
            .background(
                color = if (isSelected) Color(0xFFFFE082) else Color(0xFFFFF59D),
                shape = RoundedCornerShape(8.dp),
            )
            .clickable { onClick() }
            .padding(10.dp)
    ) {
        Text(
            text = word,
            fontSize = 18.sp,
            color = Color(0xFF5D4037),
            fontWeight = FontWeight.Bold,
            fontFamily = Nunito_Bold,
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
