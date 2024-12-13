package fpl.md07.beeslearn.screens.lessons

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColor
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import fpl.md07.beeslearn.components.BeeAnimaComponent
import fpl.md07.beeslearn.components.TextBoxComponent
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.GlobalVariable.UserSession
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.TopBarComponent
import fpl.md07.beeslearn.components.TopBarComponent_A
import fpl.md07.beeslearn.components.customFont
import fpl.md07.beeslearn.models.GrammarQuestionModel
import fpl.md07.beeslearn.models.TrueFalseQuestionModel_A
import fpl.md07.beeslearn.models.responseModel.QuestionResponseModel
import fpl.md07.beeslearn.screens.CongratulationsScreen
import fpl.md07.beeslearn.screens.questions.ArrangeSentenceScreen
import fpl.md07.beeslearn.screens.questions.FillInTheBlankScreen
import fpl.md07.beeslearn.screens.questions.MultipleChoiceScreen
import fpl.md07.beeslearn.screens.questions.SpeakingQuestionScreen
import fpl.md07.beeslearn.screens.questions.TrueFalseScreen
import fpl.md07.beeslearn.viewmodels.QuestionViewModel
import fpl.md07.beeslearn.viewmodels.UserDataViewModel
import kotlinx.coroutines.delay
import kotlin.math.cos
import kotlin.math.sin
import kotlin.random.Random

enum class QuestionMode{
    TRUEFALSE, GRAMMAR, MULTIPLECHOICE, FILLIN, FINISH, SPEAKBITCH
}

@Composable
fun SelectLessonScreen(navController: NavController) {

    val userDataViewModel: UserDataViewModel = viewModel()
    userDataViewModel.getCurrencyData()

    var isQuestionLoaded by remember { mutableStateOf(false) }
    var isLessonSelected by remember { mutableStateOf(false) }

    val questionViewModel: QuestionViewModel = viewModel()
    var questions by remember { mutableStateOf(QuestionResponseModel(
        words = null,
        trueFalseQuestions = null,
        grammarQuestions = null
    )) }

    val totalAmountOfQuestion = 10
    var questionIndex by remember { mutableIntStateOf(1) }
    var questionMode by remember { mutableStateOf( QuestionMode.GRAMMAR ) }

    LaunchedEffect(isLessonSelected) {
        if(!isLessonSelected){
            isQuestionLoaded = false
            questionViewModel.getRandomQuestions(
                total = totalAmountOfQuestion,
                onSuccess = { isQuestionLoaded = true; questions = it},
                onError = {println(it)}
            )
            questionIndex = 1
            questionMode = QuestionMode.GRAMMAR
            UserSession.bonusHoneyJar = 0
        }
    }

    LaunchedEffect(questionIndex) {
        if(questionIndex == totalAmountOfQuestion + 1){
            questionMode = QuestionMode.FINISH
        }else{
            if(questionIndex == 5 || questionIndex == 7){
                questionMode = QuestionMode.TRUEFALSE
            }else if(questionIndex == 3 || questionIndex == 9){
                questionMode = QuestionMode.MULTIPLECHOICE
            }else if(questionIndex == 2 || questionIndex == 6){
                questionMode = QuestionMode.FILLIN
            }else{
                questionMode = QuestionMode.GRAMMAR
            }
        }
    }

    BackHandler (enabled = isLessonSelected) {
        isLessonSelected = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TopBarComponent_A {
            if(isLessonSelected){
                isLessonSelected = false
            }else{
                navController.popBackStack()
            }
        }
        if(!isLessonSelected){
            Spacer(modifier = Modifier.height(16.dp))
            Box(
            ) {
                TextBoxComponent(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 50.dp)
                )
            }
            Spacer(modifier = Modifier.height(200.dp))

            HexGridd(){
                isLessonSelected = true
            }
        }else{
            if(isQuestionLoaded){
                ShowQuestionScreens(
                    questionMode = questionMode,
                    questions = questions,
                    goBack = { isLessonSelected = false },
                    onCompleteQuestion = { questionIndex += 1 }
                )
            }else{
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ){
                    CircularProgressIndicator(
                        modifier = Modifier.width(64.dp).height(64.dp),
                        color = colorResource(R.color.primary_color),
                        trackColor = colorResource(R.color.secondary_color)
                    )
                }
            }
        }
    }
}

@Composable
fun ShowQuestionScreens(
    questionMode: QuestionMode,
    questions: QuestionResponseModel,
    goBack: () -> Unit,
    onCompleteQuestion: () -> Unit)
{

    val trueFalseQuestions = questions.trueFalseQuestions
    var trueFalseQuestionIndex by remember { mutableIntStateOf(0) }

    val grammarQuestions = questions.grammarQuestions
    var grammarQuestionIndex by remember { mutableIntStateOf(0) }

    val multipleChoiceQuestion = questions.words
    var multipleChoiceQuestionIndex by remember { mutableIntStateOf(0) }

    when (questionMode) {
        QuestionMode.TRUEFALSE -> {
            TrueFalseScreen(
                trueFalseQuestions!![trueFalseQuestionIndex],
                onComplete = {
                    onCompleteQuestion()
                    if (trueFalseQuestionIndex != trueFalseQuestions.size - 1) trueFalseQuestionIndex += 1
                },
                goBack = {goBack()},
            )
        }
        QuestionMode.GRAMMAR -> {
            ArrangeSentenceScreen(
                grammarQuestions!![grammarQuestionIndex],
                onComplete = {
                    onCompleteQuestion()
                    if (grammarQuestionIndex != grammarQuestions.size - 1) grammarQuestionIndex += 1
                },
                goBack = {goBack()}
            )
        }
        QuestionMode.MULTIPLECHOICE -> {
            MultipleChoiceScreen(
                words = multipleChoiceQuestion!!.shuffled().chunked(4)
                    .shuffled()[multipleChoiceQuestionIndex],
                randomNumber = Random.nextInt(4),
                onComplete = {
                    onCompleteQuestion()
                    if (multipleChoiceQuestionIndex != multipleChoiceQuestion.chunked(4).size - 1) multipleChoiceQuestionIndex += 1
                },
                goBack = {goBack()}
            )
        }
        QuestionMode.FILLIN -> {
            FillInTheBlankScreen(
                question = grammarQuestions!![grammarQuestionIndex],
                noiseAnswers = multipleChoiceQuestion!!.shuffled().chunked(4)
                    .shuffled()[multipleChoiceQuestionIndex],
                onComplete = {
                    onCompleteQuestion()
                    if (grammarQuestionIndex != grammarQuestions.size - 1) grammarQuestionIndex += 1
                },
                goBack = {goBack()}
            )
        }
        QuestionMode.SPEAKBITCH ->{
            SpeakingQuestionScreen()
        }
        QuestionMode.FINISH -> {
            CongratulationsScreen(){
                goBack()
            }
        }
    }
}

@Composable
fun HexGridd(onPress: () -> Unit) {
    val hexagonRadius = 40.dp
    val hexagonCount = 10
    // Tạo danh sách trạng thái cho các hexagon
    val hexagonStates = remember { mutableStateListOf(*Array(hexagonCount) { true }) } // Sử dụng mutableStateListOf

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 0 until hexagonCount) {
            HexagonWithNumber(
                radius = hexagonRadius,
                number = i + 1,
                isClicked = hexagonStates[i],
                onClick = {
                    // Cập nhật trạng thái
                    hexagonStates[i] = !hexagonStates[i]
                    onPress()
                }
            )

            // Draw a connecting line (except for the last hexagon)
            if (i < hexagonCount - 1) {
                Spacer(modifier = Modifier.width(8.dp))
                HorizontalDivider(
                    modifier = Modifier
                        .width(100.dp)
                        .height(2.dp)
                        .align(Alignment.CenterVertically),
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp)) // Adjust space after line
            }
        }
    }
}

@Composable
fun HexagonWithNumber(
    radius: Dp,
    number: Int,
    isClicked: Boolean,
    onClick: () -> Unit
) {
    val outerColor = colorResource(id = R.color.outerColor_hexagon)
    val innerColor = colorResource(id = R.color.innerColor_hexagon)

    // Màu sắc mới khi hexagon được nhấn
    val newOuterColor = colorResource(id = R.color.newOuterColor)
    val newInnerColor = colorResource(id = R.color.newInnerColor)

    Box(
        modifier = Modifier
            .size(radius * 2)
            .clickable { onClick() } // Xử lý nhấp chuột
    ) {
        Canvas(
            modifier = Modifier.size(radius * 2)
        ) {
            val radiusPx = radius.toPx()
            val centerX = size.width / 2
            val centerY = size.height / 2
            val angle = Math.PI / 3.0

            // Outer hexagon path
            val hexPathOuter = Path().apply {
                moveTo(
                    (centerX + radiusPx * cos(angle / 2)).toFloat(),
                    (centerY + radiusPx * sin(angle / 2)).toFloat()
                )
                for (i in 1..6) {
                    lineTo(
                        (centerX + radiusPx * cos(angle * i + angle / 2)).toFloat(),
                        (centerY + radiusPx * sin(angle * i + angle / 2)).toFloat()
                    )
                }
                close()
            }

            // Inner hexagon path
            val innerRadiusPx = radiusPx * 0.7f
            val hexPathInner = Path().apply {
                moveTo(
                    (centerX + innerRadiusPx * cos(angle / 2)).toFloat(),
                    (centerY + innerRadiusPx * sin(angle / 2)).toFloat()
                )
                for (i in 1..6) {
                    lineTo(
                        (centerX + innerRadiusPx * cos(angle * i + angle / 2)).toFloat(),
                        (centerY + innerRadiusPx * sin(angle * i + angle / 2)).toFloat()
                    )
                }
                close()
            }

            // Vẽ hexagon bên ngoài với màu sắc mới nếu đã nhấn
            drawPath(
                path = hexPathOuter,
                color = if (isClicked) newOuterColor else outerColor
            )

            // Vẽ hexagon bên trong với màu sắc mới nếu đã nhấn
            drawPath(
                path = hexPathInner,
                color = if (isClicked) newInnerColor else innerColor
            )
        }

        // Thêm số vào giữa hexagon
        Text(
            text = number.toString(),
            modifier = Modifier.align(Alignment.Center),
            fontSize = 22.sp,
            fontFamily = customFont,
            color = colorResource(id = R.color.secondary_color)
        )
    }
}

@Preview
@Composable
fun PreviewSelectExercise() {
    val mockNavController = rememberNavController()
    SelectLessonScreen(navController = mockNavController)
}