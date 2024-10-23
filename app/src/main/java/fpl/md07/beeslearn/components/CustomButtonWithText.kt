package fpl.md07.beeslearn.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@Composable
fun CustomButtonWithText(
    onClick: () -> Unit,
    buttonText: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .height(48.dp)
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .shadow(
                elevation = 10.dp, shape = RoundedCornerShape(12.dp), clip = false
            ),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFFFF9D3))
    ) {
        Text(
            text = buttonText,
            fontFamily = Nunito_Bold,
            color = colorResource(id = R.color.secondary_color),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomButtonWithTextPreview() {
    CustomButtonWithText(
        onClick = { /* TODO: handle login */ },
        buttonText = "Tiếng Anh"
    )
}
