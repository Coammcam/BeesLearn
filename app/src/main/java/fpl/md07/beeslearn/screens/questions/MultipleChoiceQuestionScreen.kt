package fpl.md07.beeslearn.screens.questions

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.zIndex
import fpl.md07.beeslearn.components.ConfirmQuestionNo
import fpl.md07.beeslearn.components.ConfirmQuestionYes
import fpl.md07.beeslearn.models.AnswerResult
import fpl.md07.beeslearn.models.Word
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@Composable
fun MultipleChoiceScreen(words: List<Word>, randomNumber: Int, onComplete: () -> Unit, goBack: () -> Unit) {
    var selectedAnswer by remember { mutableStateOf("") }
    val questionWord = words[randomNumber]
    var result: AnswerResult? by remember { mutableStateOf(null) }
    var showResult by remember { mutableStateOf(false) }

    LaunchedEffect(selectedAnswer) {
        println(randomNumber)
        if(selectedAnswer == questionWord.englishWord){
            println("correct")
            result = AnswerResult.CORRECT
        }else{
            println("incorrect")
            result = AnswerResult.INCORRECT
        }
        if(selectedAnswer.isNotEmpty()){
            showResult = true
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
//        Spacer(Modifier.height(50.dp))
        Text(
            text = "Chọn tùy chọn đúng",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            fontFamily = Nunito_Bold,
            modifier = Modifier.height(100.dp).padding(top = 16.dp)
        )
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.25f)
                    .graphicsLayer {
                        shadowElevation = 8.dp.toPx()
                        shape = RoundedCornerShape(16.dp)
                        clip = true
                        translationY = -8.dp.toPx()
                    }
                    .background(Color(0xFFFFF59D), shape = RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = questionWord.vietnameseMeaning,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5D4037),
                    fontFamily = Nunito_Bold
                )
            }
            Box(
                modifier = Modifier.fillMaxHeight(0.8f),
//                contentAlignment = Alignment.Center
            ){
                if(showResult){
                    if(result == AnswerResult.CORRECT){
                        ConfirmQuestionYes(
                            Continue = {
                                onComplete()
                                result = null
                                showResult = false
                                selectedAnswer = ""
                            }
                        )
                    }else if(result == AnswerResult.INCORRECT){
                        ConfirmQuestionNo(
                            `continue` = {
                                onComplete()
                                result = null
                                showResult = false
                                selectedAnswer = ""
                            },
                            exitWhenNotEnoughHive = {goBack()}
                        )
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth().zIndex(-1f),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    words.chunked(2).forEach { rowAnswers ->
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                            rowAnswers.forEach { answer ->
                                AnswerButton(
                                    answerText = answer.englishWord,
                                    isSelected = answer.englishWord == selectedAnswer,
                                    onClick = { selectedAnswer = answer.englishWord },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun AnswerButton(
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
//            Box(
//                modifier = Modifier
//                    .size(24.dp)
//                    .background(Color.White, shape = CircleShape)
//                    .align(Alignment.TopStart)
//                    .padding(4.dp)
//            ) {
//                Text(
//                    text = label,
//                    fontSize = 12.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = Color(0xFF5D4037),
//                    fontFamily = Nunito_Bold,
//                    modifier = Modifier.align(Alignment.Center)
//                )
//            }

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
//
//@Preview(showBackground = true)
//@Composable
//fun MultipleChoicePreview() {
//    val navController = rememberNavController()
//    MultipleChoiceScreen(navController)
//}
