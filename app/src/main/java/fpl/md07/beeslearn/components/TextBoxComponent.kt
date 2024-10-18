package fpl.md07.beeslearn.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TextBoxComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier // Apply the passed modifier to the Box
            .shadow(8.dp, shape = RoundedCornerShape(16.dp)) // Add shadow here
            .background(Color(android.graphics.Color.parseColor("#FFF192")), shape = RoundedCornerShape(16.dp)) // Background color
            .padding(50.dp) // Inner padding
            .clip(RoundedCornerShape(16.dp)) // Clip shape with rounded corners
    ) {
        Text(
            text = "Đây là đoạn văn bản trong khung có viền và nền màu vàng.",
            color = Color.Black, // Text color
            textAlign = TextAlign.Center, // Center text alignment
            modifier = Modifier.align(Alignment.Center) // Align text in the center
        )
    }
}

