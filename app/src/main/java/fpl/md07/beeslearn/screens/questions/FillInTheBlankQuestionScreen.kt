package fpl.md07.beeslearn.screens.questions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
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
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import androidx.compose.foundation.lazy.grid.items

//class FillInTheBlankQuestionScreen : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            BeesLearnTheme  {
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    FillInTheBlankScreen()
//                }
//            }
//        }
//    }
//}

@Composable
fun FillInTheBlankScreen() {
    var selectedWord by remember { mutableStateOf("") }

    // Lấy câu hỏi đầu tiên từ dữ liệu
    val currentQuestion = fillInTheBlankQuestions[0]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(16.dp))

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
            // Thay thế "____" bằng từ đã chọn hoặc dấu "______" nếu chưa chọn
            Text(
                text = currentQuestion.questionText.replace("____", if (selectedWord.isEmpty()) "______" else selectedWord),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037),
                fontFamily = Nunito_Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // LazyVerticalGrid để hiển thị các từ điền trong dạng lưới
        LazyVerticalGrid(
            columns = GridCells.Fixed(3), // 3 cột trong lưới
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(currentQuestion.wordOptions) { word ->
                WordOptionButton(
                    word = word, // Truyền từ vào WordOptionButton
                    isSelected = selectedWord == word, // Kiểm tra nếu từ đã được chọn
                    onClick = { toggleWordSelection(word, selectedWord, onSelect = { selectedWord = it }) }
                )
            }
        }

        Spacer(modifier = Modifier.height(32.dp))
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
            color = Color(0xFF5D4037),
            fontFamily = Nunito_Bold
        )
    }
}

fun toggleWordSelection(word: String, selectedWord: String, onSelect: (String) -> Unit) {
    if (word == selectedWord) {
        onSelect("") // Hủy chọn nếu từ đã được chọn
    } else {
        onSelect(word) // Chọn từ nếu chưa chọn
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FillInTheBlankPreview() {
    FillInTheBlankScreen()
}
