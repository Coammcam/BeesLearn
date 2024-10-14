package fpl.md07.beeslearn.models

// Data model for True/False question
data class TrueFalseQuestionModel(
    val questionText: String,  // The text of the question
    val isTrue: Boolean        // True if the answer is true, False if the answer is false
)
