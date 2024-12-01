package fpl.md07.beeslearn.screens.questions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.data.fillInTheBlankQuestions // Import fake data
import fpl.md07.beeslearn.models.GrammarQuestionModel
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import androidx.compose.foundation.lazy.grid.items
import fpl.md07.beeslearn.models.Word
import androidx.compose.runtime.getValue
import androidx.compose.ui.text.style.TextAlign
import fpl.md07.beeslearn.components.ConfirmQuestionNo
import fpl.md07.beeslearn.components.ConfirmQuestionYes
import fpl.md07.beeslearn.models.AnswerResult
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun FillInTheBlankScreen(question: GrammarQuestionModel, noiseAnswers: List<Word>, onComplete: () -> Unit) {
    var selectedWord by remember { mutableStateOf("") }
    val listOfWordInQuestion by remember { mutableStateOf(question.question.split(" ")) }
    val randomWordFromQuestion by remember { mutableStateOf(listOfWordInQuestion.random()) }
    val currentQuestion = question.question.replace(randomWordFromQuestion, selectedWord.ifEmpty { "_____" } )
    var listOfAnswer by remember { mutableStateOf(emptyList<String>().toMutableList()) }
    var result: AnswerResult? by remember { mutableStateOf(null) }

    LaunchedEffect(Unit) {
        for (noise in noiseAnswers) {
            listOfAnswer.add(noise.englishWord)
        }
        listOfAnswer.add(randomWordFromQuestion)
        listOfAnswer = listOfAnswer.shuffled().toMutableList()
        println(listOfAnswer)
    }

    LaunchedEffect(selectedWord) {
        if(selectedWord.isNotEmpty()){
            if(currentQuestion == question.question){
                println("Correct")
                result = AnswerResult.CORRECT
            }else{
                println("Incorrect")
                result = AnswerResult.INCORRECT
            }
        }
    }

    Box {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Instruction Text
            Text(
                text = "Choose the word to complete the sentence",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037),
                fontFamily = Nunito_Bold,
                modifier = Modifier.padding(top = 16.dp),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp)) // Adjusted space after instruction

            // Meaning of the question
            Text(
                question.meaning,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp)) // Adjusted space after meaning

            // Box for the sentence with blank placeholder
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
                Text(
                    text = currentQuestion,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5D4037),
                    fontFamily = Nunito_Bold,
                    modifier = Modifier.padding(start = 20.dp, end = 20.dp)
                )
            }

            Spacer(modifier = Modifier.height(48.dp)) // Adjusted space between question box and word options

            // FlowRow for displaying word options
            FlowRow(
                modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 20.dp),
                mainAxisSpacing = 10.dp,  // Horizontal spacing between words
                crossAxisSpacing = 10.dp, // Vertical spacing between rows
            ) {
                listOfAnswer.forEach { word ->
                    WordOptionButton(
                        word = word,
                        isSelected = selectedWord == word,
                        onClick = { toggleWordSelection(word, selectedWord, onSelect = { selectedWord = it }) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp)) // Adjusted space after word options

            // Show confirmation buttons based on result
        }
        if(result == AnswerResult.CORRECT){
            ConfirmQuestionYes(){
                onComplete()
            }
        }else if(result == AnswerResult.INCORRECT){
            ConfirmQuestionNo(){
                onComplete()
            }
        }
    }
}


@Composable
fun WordOptionButton(
    word: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .border(
                width = 2.dp, // Border thickness
                color = if (isSelected) Color(0xFF5D4037) else Color(0xFFBCAAA4), // Border color based on selection
                shape = RoundedCornerShape(12.dp) // Rounded corners
            )
            .background(
                color = if (isSelected) Color(0xFFFFE082) else Color(0xFFFFF59D), // Background color based on selection
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() } // On click behavior
            .padding(10.dp) // Padding inside the Box
    ) {
        Text(
            text = word,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            fontFamily = Nunito_Bold
        )
    }
}

fun toggleWordSelection(word: String, selectedWord: String, onSelect: (String) -> Unit) {
    if (word == selectedWord) {
        onSelect("") // Deselect the word if it's already selected
    } else {
        onSelect(word) // Select the word if it's not selected
    }
}


//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun FillInTheBlankPreview() {
//    FillInTheBlankScreen()
//}
