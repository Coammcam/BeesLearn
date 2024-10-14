package fpl.md07.beeslearn.data

import fpl.md07.beeslearn.models.MultipleChoiceQuestionModel
import fpl.md07.beeslearn.models.MultipleChoiceAnswer

// Fake data for multiple choice questions
val multipleChoiceQuestions = listOf(
    MultipleChoiceQuestionModel(
        questionText = "Cuốn sách",
        answers = listOf(
            MultipleChoiceAnswer(label = "A", answerText = "Book", isCorrect = true),
            MultipleChoiceAnswer(label = "B", answerText = "Cook"),
            MultipleChoiceAnswer(label = "C", answerText = "Look"),
            MultipleChoiceAnswer(label = "D", answerText = "Hook")
        )
    ),
    MultipleChoiceQuestionModel(
        questionText = "Trái táo",
        answers = listOf(
            MultipleChoiceAnswer(label = "A", answerText = "Apple", isCorrect = true),
            MultipleChoiceAnswer(label = "B", answerText = "Orange"),
            MultipleChoiceAnswer(label = "C", answerText = "Banana"),
            MultipleChoiceAnswer(label = "D", answerText = "Grapes")
        )
    )
)
