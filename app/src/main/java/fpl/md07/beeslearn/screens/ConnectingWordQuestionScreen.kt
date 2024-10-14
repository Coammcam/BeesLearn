package fpl.md07.beeslearn.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme
import fpl.md07.beeslearn.data.connectingWordQuestions // Import fake data

class ConnectingWordQuestionScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeesLearnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ConnectingWordScreen()
                }
            }
        }
    }
}

@Composable
fun ConnectingWordScreen() {
    var selectedLeft by remember { mutableStateOf("") }
    var selectedRight by remember { mutableStateOf("") }

    // Use the first question from the fake data
    val currentQuestion = connectingWordQuestions[0]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Header with back button and score
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            IconButton(onClick = { /* Back action */ }) {
                Icon(
                    painter = painterResource(id = fpl.md07.beeslearn.R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color(0xFFB71C1C),
                    modifier = Modifier.size(24.dp)
                )
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                // Score hearts
                Box(
                    modifier = Modifier
                        .background(Color(0xFFFFF59D), shape = RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = fpl.md07.beeslearn.R.drawable.heart),
                            contentDescription = "Heart",
                            tint = Color(0xFFFF1744),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("5", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF5D4037))
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                // Coins
                Box(
                    modifier = Modifier
                        .background(Color(0xFFFFF59D), shape = RoundedCornerShape(50))
                        .padding(horizontal = 12.dp, vertical = 4.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = fpl.md07.beeslearn.R.drawable.honey),
                            contentDescription = "Coins",
                            tint = Color(0xFFFFD700),
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text("100", fontWeight = FontWeight.Bold, fontSize = 14.sp, color = Color(0xFF5D4037))
                    }
                }
            }
        }

        Text(
            text = "Match column A with column B",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Column Headers with background and no border
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFFFF59D), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("A", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
            }
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Color(0xFFFFF59D), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text("B", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
            }
        }

        // Columns A and B
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                currentQuestion.columnA.forEach { text ->
                    SelectableMatchingItem(
                        text = text,
                        isSelected = selectedLeft == text,
                        onClick = { selectedLeft = if (selectedLeft == text) "" else text },
                        Modifier.padding(vertical = 4.dp) // Padding between items
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                currentQuestion.columnB.forEach { text ->
                    SelectableMatchingItem(
                        text = text,
                        isSelected = selectedRight == text,
                        onClick = { selectedRight = if (selectedRight == text) "" else text },
                        Modifier.padding(vertical = 4.dp) // Padding between items
                    )
                }
            }
        }
    }
}

@Composable
fun SelectableMatchingItem(text: String, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFFFF59D),
                shape = RoundedCornerShape(16.dp)
            )
            .border(
                width = 2.dp,
                color = if (isSelected) Color(0xFF5D4037) else Color.Transparent,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
            .clickable { onClick() }
    ) {
        Text(text = text, fontWeight = FontWeight.Bold, color = Color(0xFF5D4037))
    }
}

@Preview(showBackground = true)
@Composable
fun ConnectingWordPreview() {
    BeesLearnTheme {
        ConnectingWordScreen()
    }
}
