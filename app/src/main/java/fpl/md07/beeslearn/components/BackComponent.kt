package fpl.md07.beeslearn.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpl.md07.beeslearn.R

@Composable
fun BackComponent() {
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_back), // Replace with actual back arrow resource
        contentDescription = "Back",
        modifier = Modifier
            .height(24.dp)
            .width(16.dp)
    )
}
@Preview
@Composable
fun PreviewBackComponent() {
    BackComponent()
}
