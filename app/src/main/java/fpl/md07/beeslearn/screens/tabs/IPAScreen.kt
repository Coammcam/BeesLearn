package fpl.md07.beeslearn.screens.tabs


import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.widget.VideoView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.PaymentButton
import fpl.md07.beeslearn.models.IPAModel
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import androidx.compose.ui.platform.LocalContext


private val vowels = listOf(
    IPAModel("ɑ", "hot", R.raw.hot, R.raw.hot, R.raw._ho_),
    IPAModel("æ", "cat", R.raw.cat, R.raw.cat, R.raw._ca_),
    IPAModel("ʌ", "but", R.raw.but, R.raw.but, R.raw._bu_),
    IPAModel("ɛ", "bed", R.raw.bed, R.raw.bed, R.raw._bed_),
    IPAModel("eɪ", "say", R.raw.but, R.raw.say, R.raw._say_),
    IPAModel("ɪ", "sit", R.raw.but, R.raw.sit, R.raw._sit_),
    IPAModel("i", "see", R.raw.but, R.raw.see, R.raw._se_),
    IPAModel("ə", "about", R.raw.but, R.raw.about, R.raw._about_),
    IPAModel("oʊ", "go", R.raw.but, R.raw.go, R.raw._go_),
    IPAModel("ʊ", "book", R.raw.but, R.raw.book, R.raw._book_),
    IPAModel("u", "you", R.raw.but, R.raw.you, R.raw._u__),
    IPAModel("aʊ", "now", R.raw.but, R.raw.now, R.raw._now_),
    IPAModel("aɪ", "my", R.raw.but, R.raw.my, R.raw._my_),
    IPAModel("ɔɪ", "boy", R.raw.but, R.raw.boy, R.raw._boy_),
    IPAModel("ɔ", "thought", R.raw.but, R.raw.thought, R.raw._th_),
    IPAModel("ɒ", "lot", R.raw.but, R.raw.lot, R.raw._ho_),
    IPAModel("e", "met", R.raw.but, R.raw.met, R.raw._e_),
    IPAModel("ø", "bird", R.raw.but, R.raw.bird, R.raw._bu_),
    IPAModel("œ", "girl", R.raw.but, R.raw.girl, R.raw._g_)
)
private val consonants = listOf(
    IPAModel("b", "bat", R.raw.but, R.raw.bat, R.raw._b_),
    IPAModel("d", "dog", R.raw.but, R.raw.dog, R.raw._d_),
    IPAModel("f", "fish", R.raw.but, R.raw.fish, R.raw._f_),
    IPAModel("g", "goat", R.raw.but, R.raw.goat, R.raw._g_),
    IPAModel("h", "hat", R.raw.but, R.raw.hat, R.raw._h_),
    IPAModel("j", "yes", R.raw.but, R.raw.yes, R.raw._j_),
    IPAModel("k", "kite", R.raw.but, R.raw.kite, R.raw._k_),
    IPAModel("l", "lip", R.raw.but, R.raw.lip, R.raw._l_),
    IPAModel("m", "man", R.raw.but, R.raw.man, R.raw._m_),
    IPAModel("n", "nose", R.raw.but, R.raw.nose, R.raw._n_),
    IPAModel("p", "pig", R.raw.but, R.raw.pig, R.raw._p_),
    IPAModel("r", "red", R.raw.but, R.raw.red, R.raw._r_),
    IPAModel("s", "sun", R.raw.but, R.raw.sun, R.raw._s_),
    IPAModel("t", "top", R.raw.but, R.raw.top, R.raw._t_),
    IPAModel("v", "van", R.raw.but, R.raw.van, R.raw._v_),
    IPAModel("w", "water", R.raw.but, R.raw.water, R.raw._w_),
    IPAModel("z", "zebra", R.raw.but, R.raw.zebra, R.raw._z_),
    IPAModel("ʃ", "ship", R.raw.but, R.raw.ship, R.raw._sh_),
    IPAModel("ʒ", "measure", R.raw.but, R.raw.measure, R.raw.measure),
    IPAModel("θ", "think", R.raw.but, R.raw.think, R.raw._th_),
    IPAModel("ð", "this", R.raw.but, R.raw.thisword, R.raw._thi_),
    IPAModel("ŋ", "sing", R.raw.but, R.raw.sing, R.raw._si_),
    IPAModel("tʃ", "cheese", R.raw.but, R.raw.cheese, R.raw._che_),
    IPAModel("dʒ", "judge", R.raw.but, R.raw.judge, R.raw._ju_)
)

@Composable
fun IPAExercise(navController: NavController) {
    var selectedVideoResId by remember { mutableStateOf<Int?>(null) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16 / 9f)
                .background(Color.White),
            contentAlignment = Alignment.TopCenter
        ) {
            VideoPlayer(videoResId = selectedVideoResId,
                onVideoEnded = { selectedVideoResId = null })
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item {
                SectionTitle(title = "Nguyên âm")
                Spacer(modifier = Modifier.height(16.dp))
                IPAGrid(symbols = vowels) { videoResId ->
                    selectedVideoResId = videoResId
                }
            }
            item {
                Spacer(modifier = Modifier.height(50.dp))
            }
            item {
                SectionTitle(title = "Phụ âm")
                Spacer(modifier = Modifier.height(16.dp))
                IPAGrid(symbols = consonants) { videoResId ->
                    selectedVideoResId = videoResId
                }
            }
        }
    }
}

@Composable
fun VideoPlayer(videoResId: Int?, onVideoEnded: () -> Unit) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(16 / 9f)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        if (videoResId != null) {
            AndroidView(
                factory = { ctx ->
                    val videoView = VideoView(ctx)
                    val mediaController = android.widget.MediaController(ctx).apply {
                        setAnchorView(videoView)
                    }
                    videoView.apply {
                        layoutParams = android.widget.FrameLayout.LayoutParams(
                            android.widget.FrameLayout.LayoutParams.MATCH_PARENT,
                            android.widget.FrameLayout.LayoutParams.MATCH_PARENT
                        ) // Kiểm soát kích thước
                        setVideoURI(Uri.parse("android.resource://${ctx.packageName}/$videoResId"))
                        setMediaController(mediaController)
                        setOnCompletionListener { onVideoEnded() }
                        start()
                    }
                    videoView
                }, modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            )
        } else {
            Image(
                painter = painterResource(R.drawable.music4),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .aspectRatio(16 / 9f),
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
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
fun IPAGrid(symbols: List<IPAModel>, onSymbolClick: (Int) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 800.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(symbols.size) { index ->
            val (symbol, example, _, _, videoResId) = symbols[index]
            if (videoResId != 0) {
                IPACard(symbol = symbol,
                    example = example,
                    isClicked = false,
                    onClick = { onSymbolClick(videoResId) })
            }
        }
    }
}

@Composable
fun IPACard(
    symbol: String, example: String, isClicked: Boolean, onClick: () -> Unit
) {
    val borderColor = if (isClicked) {
        colorResource(id = R.color.secondary_color)
    } else {
        Color.Transparent
    }
    Surface(
        modifier = Modifier
            .size(80.dp)
            .clickable(onClick = onClick),
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
    val navController = rememberNavController()
    IPAExercise(navController)
}

