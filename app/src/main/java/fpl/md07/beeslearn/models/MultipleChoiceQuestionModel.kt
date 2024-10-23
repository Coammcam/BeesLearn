package fpl.md07.beeslearn.models

// Data model for multiple choice question
data class MultipleChoiceQuestionModel(
    val questionText: String,
    val answers: List<MultipleChoiceAnswer>
)

data class MultipleChoiceAnswer(
    val label: String,
    val answerText: String,
    val isCorrect: Boolean = false
)
