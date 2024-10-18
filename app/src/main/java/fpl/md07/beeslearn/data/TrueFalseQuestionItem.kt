package fpl.md07.beeslearn.data

import fpl.md07.beeslearn.models.TrueFalseQuestionModel

// Fake data for True/False questions
val trueFalseQuestions = listOf(
    TrueFalseQuestionModel(
        questionText = "The sky is blue.",
        isTrue = true
    ),
    TrueFalseQuestionModel(
        questionText = "Cows can fly.",
        isTrue = false
    ),
    TrueFalseQuestionModel(
        questionText = "Water freezes at 0°C.",
        isTrue = true
    ),
    TrueFalseQuestionModel(
        questionText = "Fire is cold.",
        isTrue = false
    )
)
