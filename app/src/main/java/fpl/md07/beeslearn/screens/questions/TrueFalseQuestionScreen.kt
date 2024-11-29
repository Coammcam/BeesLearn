package fpl.md07.beeslearn.screens.questions

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.zIndex
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.ConfirmQuestionNo
import fpl.md07.beeslearn.components.ConfirmQuestionYes
import fpl.md07.beeslearn.models.AnswerResult
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme
import fpl.md07.beeslearn.models.TrueFalseQuestionModel_A
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import kotlinx.coroutines.delay

@Composable
fun TrueFalseScreen(
    truefalsequestion: TrueFalseQuestionModel_A,
    goBack: ()->Unit,
    onComplete: ()->Unit
) {
    var result: AnswerResult? by remember { mutableStateOf(null) }
    var textColor by remember { mutableStateOf(Color(0xFF5D4037)) }

    LaunchedEffect(result) {
        if(result != null){
            println("done with true false")
            delay(1000)
            onComplete()
            textColor = Color(0xFF5D4037)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Choose the right option",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            fontFamily = Nunito_Bold,
            modifier = Modifier.padding(top = 16.dp)
        )

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
            Text(
                text = truefalsequestion.content,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textColor,
                fontFamily = Nunito_Bold,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp)
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Box (
            modifier = Modifier.height(200.dp)
        ){
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.zIndex(-1f)
                    .fillMaxWidth()
                    .padding(bottom = 32.dp)
            ) {
                TrueFalseButton(
                    icon = R.drawable.ic_false,
                    backgroundColor = Color(0xFFFFEB3B),
                    iconTint = Color(0xFFFF1744),
                    onClick = {
                        if(truefalsequestion.answer == "0"){
                            println("${truefalsequestion.answer} is correct")
                            result = AnswerResult.CORRECT
                            textColor = Color.Green
                        }else{
                            result = AnswerResult.INCORRECT
                            textColor = Color.Red
                            println("WRONG")
                        }

                    }
                )
                TrueFalseButton(
                    icon = R.drawable.ic_true,
                    backgroundColor = Color(0xFFFFEB3B),
                    iconTint = Color(0xFF00E676),
                    onClick = {
                        if(truefalsequestion.answer == "1"){
                            println("${truefalsequestion.answer} is correct")
                            result = AnswerResult.CORRECT
                            textColor = Color.Green
                        }else{
                            println("WRONG")
                            result = AnswerResult.INCORRECT
                            textColor = Color.Red
                        }
                    }
                )
            }
            if(result == AnswerResult.CORRECT){
                ConfirmQuestionYes()
            }else if(result == AnswerResult.INCORRECT){
                ConfirmQuestionNo()
            }
        }
    }
}

@Composable
fun TrueFalseButton(
    icon: Int,
    backgroundColor: Color,
    iconTint: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(backgroundColor, shape = CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrueFalsePreview() {
    BeesLearnTheme {
        val navController = rememberNavController()
//        TrueFalseScreen(){
//
//        }
    }
}
