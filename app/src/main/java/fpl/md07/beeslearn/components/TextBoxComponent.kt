package fpl.md07.beeslearn.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import fpl.md07.beeslearn.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun TextBoxComponent(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .shadow(8.dp, shape = RoundedCornerShape(16.dp))
            .background(color = colorResource(id = R.color.primary_color), shape = RoundedCornerShape(16.dp))
            .padding(50.dp) // Inner padding
            .clip(RoundedCornerShape(16.dp))
    ) {
        Text(
            text = "Xin mời chọn level",
            textAlign = TextAlign.Center,
            modifier = Modifier.align(Alignment.Center),
            fontFamily = customFont,
            fontSize = 16.sp,
            color = colorResource(id = R.color.secondary_color)
        )
    }
}

@Preview
@Composable
fun PreviewTextBoxComponent() {
    TextBoxComponent()
}
