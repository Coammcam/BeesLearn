package fpl.md07.beeslearn.models

data class Podcast(
    val id: String,
    val title: String,
    val description: String,
    val imageRes: Int,
    val view: String,
    val time: String,
    val youtubeUrl: String = "https://youtube.com"
)