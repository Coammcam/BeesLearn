package fpl.md07.beeslearn.screens.tabs

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
    IPAModel("ɑ", "hot", null, null),
    IPAModel("æ", "cat", null, null),
    IPAModel("ʌ", "but", null, null),
    IPAModel("ɛ", "bed", null, null),
    IPAModel("eɪ", "say", null, null),
    IPAModel("ɪ", "sit", null, null),
    IPAModel("i", "see", null, null),
    IPAModel("ə", "about", null, null),
    IPAModel("oʊ", "go", null, null),
    IPAModel("ʊ", "book", null, null),
    IPAModel("u", "you", null, null),
    IPAModel("aʊ", "now", null, null),
    IPAModel("aɪ", "my", null, null),
    IPAModel("ɔɪ", "boy", null, null),
    IPAModel("ɔ", "thought", null, null),
    IPAModel("ɒ", "lot", null, null),
    IPAModel("e", "met", null, null),
    IPAModel("ø", "bird", null, null),
    IPAModel("œ", "girl", null, null)
)
private val consonants = listOf(
    IPAModel("b", "bat", null, null),
    IPAModel("d", "dog", null, null),
    IPAModel("f", "fish", null, null),
    IPAModel("g", "goat", null, null),
    IPAModel("h", "hat", null, null),
    IPAModel("j", "yes", null, null),
    IPAModel("k", "kite", null, null),
    IPAModel("l", "lip", null, null),
    IPAModel("m", "man", null, null),
    IPAModel("n", "nose", null, null),
    IPAModel("p", "pig", null, null),
    IPAModel("r", "red", null, null),
    IPAModel("s", "sun", null, null),
    IPAModel("t", "top", null, null),
    IPAModel("v", "van", null, null),
    IPAModel("w", "water", null, null),
    IPAModel("z", "zebra", null, null),
    IPAModel("ʃ", "ship", null, null),
    IPAModel("ʒ", "measure", null, null),
    IPAModel("θ", "think", null, null),
    IPAModel("ð", "this", null, null),
    IPAModel("ŋ", "sing", null, null),
    IPAModel("tʃ", "cheese", null, null),
    IPAModel("dʒ", "judge", null, null)
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

        Box(modifier = Modifier
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
                .weight(1.3f)
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

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(max = 800.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(symbols.size) { index ->
            val (symbol, example) = symbols[index]
            IPACard(symbol, example)
        }
    }
}

@Composable
fun IPACard(symbol: String, example: String) {
    Surface(
        modifier = Modifier.size(80.dp),
        color = colorResource(id = R.color.fourth_color),
        shape = RoundedCornerShape(11.dp),
        tonalElevation = 2.dp
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