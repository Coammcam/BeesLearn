package fpl.md07.beeslearn.models

// Data model for fill-in-the-blank question
data class FillInTheBlankQuestionModel(
    val questionText: String,  // The question with the blank
    val wordOptions: List<String>  // List of word options for filling in the blank
)
