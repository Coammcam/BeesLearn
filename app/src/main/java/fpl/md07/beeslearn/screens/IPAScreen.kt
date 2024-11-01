package fpl.md07.beeslearn.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import fpl.md07.beeslearn.R


private val vowels = listOf(
    "ɑ" to "hot", "æ" to "cat", "ʌ" to "but", "ɛ" to "bed",
    "eɪ" to "say", "ɪ" to "sit", "i" to "see", "ə" to "about",
    "oʊ" to "go", "ʊ" to "book", "u" to "you", "aʊ" to "now",
    "aɪ" to "my", "ɔɪ" to "boy", "ɔ" to "thought", "ɒ" to "lot",
    "e" to "met", "ø" to "bird", "œ" to "girl"
)

private val consonants = listOf(
    "b" to "bat", "d" to "dog", "f" to "fish", "g" to "goat",
    "h" to "hat", "j" to "yes", "k" to "kite", "l" to "lip",
    "m" to "man", "n" to "nose", "p" to "pig", "r" to "red",
    "s" to "sun", "t" to "top", "v" to "van", "w" to "water",
    "z" to "zebra", "ʃ" to "ship", "ʒ" to "measure", "θ" to "think",
    "ð" to "this", "ŋ" to "sing", "tʃ" to "cheese", "dʒ" to "judge"
)

class IPAScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                IPAExercise()
            }
        }
    }
}

@Composable
fun IPAExercise() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            SectionTitle(title = "Vowels")
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            IPAGrid(symbols = vowels)
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            SectionTitle(title = "Consonants")
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            IPAGrid(symbols = consonants)
        }
    }
}


@Composable
fun SectionTitle(title: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Left horizontal line
        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(colorResource(id = R.color.secondary_color))
        )

        // Title text, using weight to fill available space
        Text(
            text = title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(id = R.color.secondary_color),
            modifier = Modifier
                .weight(1.3f) // This allows the text to be centered between the lines
                .padding(horizontal = 8.dp), // Padding for separation
            textAlign = TextAlign.Center
        )

        // Right horizontal line
        Box(
            modifier = Modifier
                .height(1.dp)
                .weight(1f)
                .background(colorResource(id = R.color.secondary_color))
        )
    }
}


@Composable
fun IPAGrid(symbols: List<Pair<String, String>>) {
    Box(modifier = Modifier.height(700.dp)) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier
                .fillMaxWidth(), // Set a fixed height for the grid
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(symbols.size) { index ->
                val (symbol, example) = symbols[index]
                IPACard(symbol, example)
            }
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
                textAlign = TextAlign.Center
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
    MaterialTheme {
        IPAExercise()
    }
}
