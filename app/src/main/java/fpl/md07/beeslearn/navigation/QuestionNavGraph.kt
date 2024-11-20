package fpl.md07.beeslearn.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import fpl.md07.beeslearn.models.responseModel.QuestionResponseModel
import fpl.md07.beeslearn.screens.questions.ArrangeSentenceScreen
import fpl.md07.beeslearn.screens.questions.MultipleChoiceScreen
import fpl.md07.beeslearn.screens.questions.TrueFalseScreen
import fpl.md07.beeslearn.viewmodels.QuestionViewModel

//fun NavGraphBuilder.addQuestionNavGraph(navController: NavController, startDestination: String) {
//    composable("multipleChoice") {
//        MultipleChoiceScreen(navController)
//    }
//    composable("trueFalse") {
//        TrueFalseScreen(navController)
//    }
//    composable("arrangeSentence") {
//        ArrangeSentenceScreen(navController)
//    }
//}

@Composable
fun QuestionNavGraph(navController: NavController) {
    val viewModel: QuestionViewModel = viewModel()
    val questionState = remember { mutableStateOf<QuestionResponseModel?>(null) }

    LaunchedEffect(Unit) {
        viewModel.getRandomQuestions(total = 10,
            onSuccess = { response -> questionState.value = response },
            onError = { error ->
                Log.e("NavGraph", "Error: $error")
            })
    }

    questionState.value?.let { questionResponse ->
        val startDestination = if (questionResponse.grammarQuestions.isNotEmpty()) {
            "grammarQuestion/${questionResponse.grammarQuestions[0].question}"
        } else {
            "multipleChoice"
        }



    }
}