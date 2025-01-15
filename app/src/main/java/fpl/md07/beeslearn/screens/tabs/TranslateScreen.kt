package fpl.md07.beeslearn.screens.tabs

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.generateSpeechAndPlay
import fpl.md07.beeslearn.translate.Language
import fpl.md07.beeslearn.translate.Translation
import fpl.md07.beeslearn.translate.Translator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TranslateScreen(navController: NavController) {

    val sourceText = remember { mutableStateOf("") }
    val translatedText = remember { mutableStateOf("Kết quả sẽ hiển thị ở đây") }
    val sourceLanguage = remember { mutableStateOf(Language.ENGLISH) }
    val targetLanguage = remember { mutableStateOf(Language.VIETNAMESE) }
    val isLoading = remember { mutableStateOf(false) }
    val translationError = remember { mutableStateOf<String?>(null) }
    val translationHistory = remember { mutableStateListOf<Pair<String, String>>() }
    val apiKey = "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"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            LanguageDropdown(
                selectedLanguage = sourceLanguage.value,
                onLanguageSelected = { sourceLanguage.value = it }
            )

            Icon(
                painter = painterResource(id = R.drawable.swap),
                contentDescription = "Hoán đổi ngôn ngữ",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        val temp = sourceLanguage.value
                        sourceLanguage.value = targetLanguage.value
                        targetLanguage.value = temp
                    },
                tint = colorResource(id = R.color.secondary2_color)
            )
            LanguageDropdown(
                selectedLanguage = targetLanguage.value,
                onLanguageSelected = { targetLanguage.value = it }
            )
        }

        TextField(
            value = sourceText.value,
            onValueChange = {
                sourceText.value = it
                translationError.value = null
            },
            label = { Text("Nhập văn bản") },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = colorResource(id = R.color.fourth_color),
                    shape = RoundedCornerShape(10.dp)
                )
                .border(
                    width = 1.dp,
                    colorResource(R.color.secondary2_color),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(2.dp),
            maxLines = 6,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent, // Loại bỏ background mặc định
                focusedIndicatorColor = Color.Transparent, // Loại bỏ line khi focus
                unfocusedIndicatorColor = Color.Transparent // Loại bỏ line khi unfocus
            )
        )
        // Kết quả dịch
        Text(
            text = translatedText.value,
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(R.color.fourth_color), shape = RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    colorResource(R.color.secondary2_color),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(20.dp),
            style = MaterialTheme.typography.bodyMedium
        )

        // Nút Dịch
        Button(
            onClick = {
                if (sourceText.value.isNotBlank()) {
                    isLoading.value = true
                    translationError.value = null
                    translateText(
                        sourceText.value,
                        targetLanguage.value
                    ) { result, error ->
                        result?.let { translation ->
                            translatedText.value = translation.translatedText
                            // Cập nhật lịch sử
                            translationHistory.add(
                                0, // Chỉ định vị trí đầu tiên
                                sourceText.value to translation.translatedText
                            )
                        }
                        error?.let {
                            translationError.value = it
                        }
                        isLoading.value = false
                    }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .border(
                    width = 1.dp,
                    color = colorResource(R.color.secondary2_color),
                    shape = RoundedCornerShape(25.dp)
                ),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.newInnerColor),
                contentColor = Color.White // Màu chữ
            ),
            enabled = sourceText.value.isNotBlank() && !isLoading.value,
        ) {
            Text("Dịch", color = Color.White)
        }

        // Loading Indicator
        if (isLoading.value) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .size(30.dp),
                color = colorResource(R.color.secondary_color),
                strokeWidth = 2.dp // Độ dày của vòng quay
            )
        }
        // Lịch sử dịch
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Màu "Lịch sử dịch"
            val historyTextColor = colorResource(id = R.color.secondary2_color)
            Text(
                text = "Lịch sử dịch",
                style = MaterialTheme.typography.titleMedium.copy(color = historyTextColor)
            )
            // Nút xóa lịch sử
            var isClicked by remember { mutableStateOf(false) }

            val textColor = if (translationHistory.isEmpty()) {
                colorResource(id = R.color.third_color) //Disabled state when no history
            } else if (translationHistory.isNotEmpty()) {
                colorResource(id = R.color.secondary2_color) // Always show this color when history exists
            } else if (isClicked) {
                colorResource(id = R.color.third_color)
            } else {
                colorResource(id = R.color.secondary2_color) // Default color when history exists
            }
            TextButton(
                onClick = {
                    translationHistory.clear()
                    isClicked = true
                },

                ) {
                Text(
                    text = "Xóa lịch sử",
                    style = MaterialTheme.typography.titleMedium.copy(color = textColor),
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            content = {
                items(translationHistory) { historyItem ->
                    TranslationHistoryItem(historyItem, apiKey)
                }
            }
        )

        // Xử lý lỗi
        translationError.value?.let { errorMessage ->
            Text(
                text = "Lỗi: $errorMessage",
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun TranslationHistoryItem(history: Pair<String, String>, api: String) {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(colorResource(R.color.fourth_color), shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.audio),
                contentDescription = "Audio Icon",
                modifier = Modifier.size(16.dp).clickable {

                    Log.d("Audio: ", history.first)
                    generateSpeechAndPlay(apiKey = api, history.first, cacheDir = context.cacheDir )
                }

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Văn bản gốc: ${history.first}",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        HorizontalDivider(thickness = 0.5.dp, color = colorResource(R.color.secondary_color))
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = "Translate Icon",
                modifier = Modifier.size(16.dp),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.secondary_color))

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Kết quả: ${history.second}", style = MaterialTheme.typography.bodySmall)
        }
    }
}

@Composable
fun LanguageDropdown(selectedLanguage: Language, onLanguageSelected: (Language) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val languages = remember {
        Language.values().filter { it != Language.AUTO }.sortedBy { it.formattedName }
    }

    Box() {
        TextButton(
            onClick = { expanded = true },
            modifier = Modifier
                .background(
                    colorResource(R.color.primary_color),
                    shape = RoundedCornerShape(8.dp)
                )
                .widthIn(min = 150.dp)// chieu rong toi thieu
                .border(
                    width = 1.dp,
                    colorResource(R.color.secondary2_color),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Row() {
                Text(
                    " ${selectedLanguage.formattedName}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Icon(
                    painterResource(id = R.drawable.ic_arrow_down),
                    contentDescription = "Mở danh sách ngôn ngữ",
                    tint = colorResource(R.color.secondary_color) // Màu của icon
                )
            }

        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp) // Padding hai bên
                .padding(vertical = 20.dp)
                .background(
                    color = colorResource(R.color.innerColor_hexagon),
                    shape = RoundedCornerShape(12.dp)
                )
                .heightIn(max = 500.dp) // Giới hạn chiều cao tối đa của menu
                .padding(2.dp)
        ) {
            languages.forEach { language ->
                DropdownMenuItem(
                    text = {
                        Text(
                            language.formattedName,
                            style = MaterialTheme.typography.bodyMedium.copy(
                                color = Color.Black // Màu chữ
                            )
                        )
                    },
                    onClick = {
                        onLanguageSelected(language)
                        expanded = false
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .background(
                            color = colorResource(R.color.fourth_color), // Màu nền item
                            shape = RoundedCornerShape(8.dp) // Bo góc item
                        )
                        .padding(2.dp) // Khoảng cách bên trong item
                )
            }
        }
    }
}

fun translateText(
    text: String,
    target: Language,
    onResult: (Translation?, String?) -> Unit
) {
    val translator = Translator()
    CoroutineScope(Dispatchers.IO).launch {
        try {
            val translation = translator.translateBlocking(text, target)
            withContext(Dispatchers.Main) {
                onResult(translation, null)
            }
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                onResult(null, e.message ?: "Đã xảy ra lỗi không xác định")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TranslatePreview() {
    var navController = rememberNavController()
    TranslateScreen(navController)
}