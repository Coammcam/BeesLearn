package fpl.md07.beeslearn.translate

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.screens.tabs.TranslateScreen

@Composable
fun TranslationHistoryItem() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(colorResource(R.color.fourth_color), shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.translate),
                contentDescription = "Translate Icon",
                modifier = Modifier.size(16.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Văn bản gốc: ",
                style = MaterialTheme.typography.bodyMedium
            )
        }

        HorizontalDivider(thickness = 0.5.dp, color = colorResource(R.color.secondary_color))
        Spacer(modifier = Modifier.height(8.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.ic_arrow_forward),
                contentDescription = "Translate Icon",
                modifier = Modifier.size(16.dp),
                colorFilter = ColorFilter.tint(colorResource(id = R.color.secondary_color))

            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Kết quả: ", style = MaterialTheme.typography.bodySmall)
        }
    }
}
@Preview(showBackground = true)
@Composable
fun TranslatePreview() {
    var navController = rememberNavController()
    TranslationHistoryItem()
}