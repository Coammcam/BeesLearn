package fpl.md07.beeslearn.screens


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme

class MultipleChoiceQuestion : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeesLearnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MultipleChoiceScreen()
                }
            }
        }
    }
}

@Composable
fun MultipleChoiceScreen() {
    // Maintain selected state for the answers
    var selectedAnswer by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Back button and score area
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconButton(onClick = { /* Back action */ }) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back), // Replace with your back icon resource
                    contentDescription = "Back",
                    tint = Color(0xFFB71C1C),
                    modifier = Modifier.size(24.dp) // Adjust icon size
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier
            ) {
                // Heart with background
                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFFFF59D),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.heart), // Replace with your heart icon
                            contentDescription = "Heart",
                            tint = Color(0xFFFF1744),
                            modifier = Modifier.size(16.dp) // Adjust icon size
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "5", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF5D4037))
                    }
                }

                // Coins with background
                Box(
                    modifier = Modifier
                        .background(
                            color = Color(0xFFFFF59D),
                            shape = RoundedCornerShape(50)
                        )
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.honey), // Replace with your coins icon
                            contentDescription = "Coins",
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(16.dp) // Adjust icon size
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "100", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF5D4037))
                    }
                }
            }
        }

        // Question box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .background(Color(0xFFFFF59D), shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Cuốn sách",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037)
            )
        }

        // Answer options
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                AnswerButton("A", "Book", selectedAnswer == "A", onClick = { selectedAnswer = "A" }, Modifier.weight(1f))
                AnswerButton("B", "Cook", selectedAnswer == "B", onClick = { selectedAnswer = "B" }, Modifier.weight(1f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                AnswerButton("C", "Look", selectedAnswer == "C", onClick = { selectedAnswer = "C" }, Modifier.weight(1f))
                AnswerButton("D", "Hook", selectedAnswer == "D", onClick = { selectedAnswer = "D" }, Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun AnswerButton(
    label: String,
    answerText: String,
    isSelected: Boolean, // Pass the state of selection
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp), // Rounded corners
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFFF59D)),
        border = if (isSelected) BorderStroke(2.dp, Color(0xFF5D4037)) else BorderStroke(0.dp, Color.Transparent), // Add border when selected
        modifier = modifier
            .height(100.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Circle label
            Box(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.White, shape = CircleShape)
                    .align(Alignment.TopStart)
                    .padding(4.dp)
            ) {
                Text(
                    text = label,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5D4037),
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            // Answer text centered
            Text(
                text = answerText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037),
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MultipleChoicePreview() { // Renamed to avoid conflict
    BeesLearnTheme {
        MultipleChoiceQuestion() // Assuming this is your function name for multiple choice questions
    }
}

