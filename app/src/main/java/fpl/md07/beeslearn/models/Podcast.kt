

//data class Podcast(
//    val id: String,
//    val title: String,
//    val description: String,
//    val imageRes: Int,
//    val view: String,
//    val time: String,
//    val youtubeUrl: String = "https://youtube.com"
//)
package fpl.md07.beeslearn.models

data class Podcast(
    val duration: String,
    val image_url: String,
    val link_on_youtube: String,
    val description: String,
    val title: String,
    val views : String
)