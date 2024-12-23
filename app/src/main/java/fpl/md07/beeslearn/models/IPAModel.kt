package fpl.md07.beeslearn.models

data class IPAModel(
    val symbol: String,
    val example: String,
    val soundSymbol: Int? = null, // Tùy chọn âm thanh
    val soundExample: Int? = null, // Tùy chọn âm thanh
//    val videoResId: Int? = null // Tùy chọn video
)
