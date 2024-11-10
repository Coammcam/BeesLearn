package fpl.md07.beeslearn.models

data class ConnectingWordQuestionModel(
    val columnA: List<String>,  // Left column of sentences
    val columnB: List<String>,  // Right column of sentences
    val pairs: List<Pair<String, String>>  // List of correct pairs
)
