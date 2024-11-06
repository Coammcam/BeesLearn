package fpl.md07.beeslearn.models

data class GrammarQuestionModel (
    val question: List<String>,
    val correct_answer: String,
    val meaning: String,
    val topic: String,
    val level: Int,
)