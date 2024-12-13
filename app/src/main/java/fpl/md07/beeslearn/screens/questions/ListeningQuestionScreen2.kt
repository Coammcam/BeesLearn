package fpl.md07.beeslearn.screens.questions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.google.accompanist.flowlayout.FlowMainAxisAlignment
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.ConfirmQuestionNo
import fpl.md07.beeslearn.components.ConfirmQuestionYes
import fpl.md07.beeslearn.models.AnswerResult
import fpl.md07.beeslearn.models.GrammarQuestionModel
import fpl.md07.beeslearn.ui.theme.Nunito_Bold


@Composable
fun ListeningQuestionScreen2(grammarQuestionModel: GrammarQuestionModel, onComplete: () -> Unit, goBack: () -> Unit) {
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
                text = "Hãy lắng nghe và sắp xếp để có được câu trả lời đúng",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037),
                fontFamily = Nunito_Bold,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

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
                        modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                        mainAxisSpacing = 4.dp, // Reduced horizontal space between words
                        crossAxisSpacing = 4.dp, // Reduced vertical space between words
                        lastLineMainAxisAlignment = FlowMainAxisAlignment.Center,
                        mainAxisAlignment = MainAxisAlignment.Center
                    ) {
                        selectedParts.forEach { word ->
                            DraggableWordOption2(word = word, isSelected = false, onClick = {
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
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    FlowRow(
                        modifier = Modifier.fillMaxWidth(),
                        mainAxisSpacing = 10.dp,  // Reduced space between words horizontally
                        crossAxisSpacing = 10.dp, // Reduced space between words vertically
                        lastLineMainAxisAlignment = FlowMainAxisAlignment.Center,
                        mainAxisAlignment = MainAxisAlignment.Center
                    ) {
                        sentenceParts.forEach { word ->
                            DraggableWordOption2(word = word, isSelected = false, onClick = {
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
                    `continue` = {
                        onComplete()
                        selectedParts.clear()
                        result = null
                    },
                    exitWhenNotEnoughHive = {goBack()}
                )
            }
        }
    }
}

@Composable
fun DraggableWordOption2(word: String, isSelected: Boolean, onClick: () -> Unit) {
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
            .padding(10.dp),

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

//@Preview(showBackground = true)
//@Composable
//fun PreviewListeningQuestion2Content() {
//    val mockGrammarQuestion = GrammarQuestionModel(
//        question = "This is a sample sentence",
//        meaning = "Meaning of the sentence",
//        correct_answer = "This is the correct answer",
//        topic = "1345",
//        level = 1
//    )
//
//    // Pass the mock GrammarQuestionModel to the ListeningQuestionScreen2 composable
//    ListeningQuestionScreen2(grammarQuestionModel = mockGrammarQuestion, onComplete = {})
//}

