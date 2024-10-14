package fpl.md07.beeslearn.models

// Data model for connecting word question
data class ConnectingWordQuestionModel(
    val columnA: List<String>,  // Left column of sentences
    val columnB: List<String>   // Right column of sentences
)
