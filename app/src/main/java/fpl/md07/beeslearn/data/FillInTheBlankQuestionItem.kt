package fpl.md07.beeslearn.data

import fpl.md07.beeslearn.models.FillInTheBlankQuestionModel

// Fake data for fill-in-the-blank questions
val fillInTheBlankQuestions = listOf(
    FillInTheBlankQuestionModel(
        questionText = "Lorem ipsum ____ dolor sit amet",
        wordOptions = listOf("Lorem", "ipsum", "amet", "sit", "dolor", "aha")
    )
)
