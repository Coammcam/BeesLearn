package fpl.md07.beeslearn.screens.tabs

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.models.IPAModel
import fpl.md07.beeslearn.ui.theme.Nunito_Bold


private val vowels = listOf(
    IPAModel("ɑ", "hot", R.raw.hot, R.raw.hot),
    IPAModel("æ", "cat", R.raw.cat, R.raw.cat),
    IPAModel("ʌ", "but", R.raw.but, R.raw.but),
    IPAModel("ɛ", "bed", R.raw.bed, R.raw.bed),
    IPAModel("eɪ", "say", R.raw.but, R.raw.say),
    IPAModel("ɪ", "sit", R.raw.but, R.raw.sit),
    IPAModel("i", "see", R.raw.but, R.raw.see),
    IPAModel("ə", "about", R.raw.but, R.raw.about),
    IPAModel("oʊ", "go", R.raw.but, R.raw.go),
    IPAModel("ʊ", "book", R.raw.but, R.raw.book),
    IPAModel("u", "you", R.raw.but, R.raw.you),
    IPAModel("aʊ", "now", R.raw.but, R.raw.now),
    IPAModel("aɪ", "my", R.raw.but, R.raw.my),
    IPAModel("ɔɪ", "boy", R.raw.but, R.raw.boy),
    IPAModel("ɔ", "thought", R.raw.but, R.raw.thought),
    IPAModel("ɒ", "lot", R.raw.but, R.raw.lot),
    IPAModel("e", "met", R.raw.but, R.raw.met),
    IPAModel("ø", "bird", R.raw.but, R.raw.bird),
    IPAModel("œ", "girl", R.raw.but, R.raw.girl)
)
private val consonants = listOf(
    IPAModel("b", "bat", R.raw.but, R.raw.bat),
    IPAModel("d", "dog", R.raw.but, R.raw.dog),
    IPAModel("f", "fish", R.raw.but, R.raw.fish),
    IPAModel("g", "goat", R.raw.but, R.raw.goat),
    IPAModel("h", "hat", R.raw.but, R.raw.hat),
    IPAModel("j", "yes", R.raw.but, R.raw.yes),
    IPAModel("k", "kite", R.raw.but, R.raw.kite),
    IPAModel("l", "lip", R.raw.but, R.raw.lip),
    IPAModel("m", "man", R.raw.but, R.raw.man),
    IPAModel("n", "nose", R.raw.but, R.raw.nose),
    IPAModel("p", "pig", R.raw.but, R.raw.pig),
    IPAModel("r", "red", R.raw.but, R.raw.red),
    IPAModel("s", "sun", R.raw.but, R.raw.sun),
    IPAModel("t", "top", R.raw.but, R.raw.top),
    IPAModel("v", "van", R.raw.but, R.raw.van),
    IPAModel("w", "water", R.raw.but, R.raw.water),
    IPAModel("z", "zebra", R.raw.but, R.raw.zebra),
    IPAModel("ʃ", "ship", R.raw.but, R.raw.ship),
    IPAModel("ʒ", "measure", R.raw.but, R.raw.measure),
    IPAModel("θ", "think", R.raw.but, R.raw.think),
    IPAModel("ð", "this", R.raw.but, R.raw.thisword),
    IPAModel("ŋ", "sing", R.raw.but, R.raw.sing),
    IPAModel("tʃ", "cheese", R.raw.but, R.raw.cheese),
    IPAModel("dʒ", "judge", R.raw.but, R.raw.judge)
)

@Composable
fun IPAExercise(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        SectionTitle(title = "Vowels")
        Spacer(modifier = Modifier.height(16.dp))
        IPAGrid(symbols = vowels)
        Spacer(modifier = Modifier.height(50.dp))

        SectionTitle(title = "Consonants")
        Spacer(modifier = Modifier.height(16.dp))
        IPAGrid(symbols = consonants)

    }
}

@Composable
fun SectionTitle(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(colorResource(id = R.color.secondary_color))
        )

        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.secondary_color),
            modifier = Modifier
                .weight(1.4f)
                .padding(horizontal = 8.dp),
            textAlign = TextAlign.Center,
            fontFamily = Nunito_Bold
        )

        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(colorResource(id = R.color.secondary_color))
        )
    }
}

@Composable
fun IPAGrid(symbols: List<IPAModel>) {

    val clickedIndex = remember { mutableStateOf<Int?>(null) }
    val isPlayingSound = remember { mutableStateOf<Boolean>(false) }

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 800.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(symbols.size) { index ->
            val (symbol, example, soundSymbol, soundExample) = symbols[index]
            if (soundSymbol != null) {
                if (soundExample != null) {
                    IPACard(
                        symbol = symbol,
                        example = example,
                        soundSymbol = soundSymbol,
                        soundExample = soundExample,
                        isClicked = clickedIndex.value == index,
                        isPlayingSound = isPlayingSound.value,
                        onClick = {
                            clickedIndex.value = index
                            isPlayingSound.value = true
                        }
                    )
                }
            }
        }
    }
    // Tự động reset sau 1 giây nếu một mục được click
    clickedIndex.value?.let {
        LaunchedEffect(it) {
            kotlinx.coroutines.delay(1100)
            clickedIndex.value = null
            isPlayingSound.value = false
        }
    }

}

fun playSound(
    fileName: String,
    mediaPlayer: MutableState<MediaPlayer?>,
    context: Context,
    onCompletion: (() -> Unit)? = null
) {
    val resId =
        context.resources.getIdentifier(fileName.removeSuffix(".mp3"), "raw", context.packageName)
    if (resId != 0) {
        mediaPlayer.value?.release()
        mediaPlayer.value = MediaPlayer.create(context, resId)
        mediaPlayer.value?.setOnCompletionListener {

            onCompletion?.invoke()
        }
        mediaPlayer.value?.start()
    } else {
        Log.e("IPAGrid", "Sound file $fileName not found in the raw resource directory.")
    }
}

@Composable
fun IPACard(
    symbol: String,
    example: String,
    soundSymbol: Int,
    soundExample: Int,
    isClicked: Boolean,
    isPlayingSound: Boolean,
    onClick: () -> Unit,

) {
    val borderColor = if (isClicked) {
        colorResource(id = R.color.secondary_color)
    } else {
        Color.Transparent
    }
    val context = LocalContext.current
    val mediaPlayer = remember { mutableStateOf<MediaPlayer?>(null) }
    //val isPlayingSound = remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .size(80.dp)
            .clickable(
                enabled = !isClicked && !isPlayingSound,
                onClick = {
                    onClick()
                    playSound("$soundSymbol.mp3", mediaPlayer, context) {
                        playSound("$soundExample.mp3", mediaPlayer, context) {
                            // Khi âm thanh thứ 2 hoàn tất, reset trạng thái clicked và playing sound

                        }
                    }
                }
            ),
        color = colorResource(id = R.color.fourth_color),
        shape = RoundedCornerShape(11.dp),
        tonalElevation = 2.dp,
        border = BorderStroke(2.dp, borderColor)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = symbol,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(id = R.color.secondary_color),
                textAlign = TextAlign.Center,
                fontFamily = Nunito_Bold
            )
            Text(
                text = example,
                fontSize = 15.sp,
                color = colorResource(id = R.color.third_color),
                textAlign = TextAlign.Center
            )

        }
    }
}


@Preview(showBackground = true)
@Composable
fun IPAScreenPreview() {
    var navController = rememberNavController()
    IPAExercise(navController)
}