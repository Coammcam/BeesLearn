package fpl.md07.beeslearn.screens.questions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.BeesLearnTheme
import fpl.md07.beeslearn.data.trueFalseQuestions // Import the fake data
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

class TrueFalseQuestionScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BeesLearnTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TrueFalseScreen()
                }
            }
        }
    }
}

@Composable
fun TrueFalseScreen() {
    var currentQuestionIndex by remember { mutableStateOf(0) }

    val currentQuestion = trueFalseQuestions[currentQuestionIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            IconButton(onClick = { /* Back action */ }) {
                Icon(
                    painter = painterResource(id = fpl.md07.beeslearn.R.drawable.ic_back),
                    contentDescription = "Back",
                    tint = Color(0xFFB71C1C),
                    modifier = Modifier.size(24.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
            ) {
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
                        Text(
                            text = "5",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF5D4037),
                            fontFamily = Nunito_Bold
                        )
                    }
                }

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
                        Text(
                            text = "100",
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            color = Color(0xFF5D4037),
                            fontFamily = Nunito_Bold
                        )
                    }
                }
            }
        }

        Text(
            text = "Swipe to the left or right",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5D4037),
            fontFamily = Nunito_Bold,
            modifier = Modifier.padding(top = 16.dp)
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .graphicsLayer {
                    shadowElevation = 8.dp.toPx()
                    shape = RoundedCornerShape(16.dp)
                    clip = true
                    translationY = -8.dp.toPx()
                }
                .background(Color(0xFFFFF59D), shape = RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currentQuestion.questionText,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF5D4037),
                fontFamily = Nunito_Bold
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 32.dp)
        ) {
            TrueFalseButton(
                icon = R.drawable.ic_false,
                backgroundColor = Color(0xFFFFEB3B),
                iconTint = Color(0xFFFF1744),
                onClick = {
                    if (!currentQuestion.isTrue) {
                        currentQuestionIndex = (currentQuestionIndex + 1) % trueFalseQuestions.size
                    }
                }
            )
            TrueFalseButton(
                icon = R.drawable.ic_true,
                backgroundColor = Color(0xFFFFEB3B),
                iconTint = Color(0xFF00E676),
                onClick = {
                    if (currentQuestion.isTrue) {
                        currentQuestionIndex = (currentQuestionIndex + 1) % trueFalseQuestions.size
                    }
                }
            )
        }
    }
}

@Composable
fun TrueFalseButton(
    icon: Int,
    backgroundColor: Color,
    iconTint: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(80.dp)
            .background(backgroundColor, shape = CircleShape)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(48.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TrueFalsePreview() {
    BeesLearnTheme {
        TrueFalseQuestionScreen()
    }
}
