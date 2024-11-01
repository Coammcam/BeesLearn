package fpl.md07.beeslearn.components


import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import fpl.md07.beeslearn.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun IconTopComponent() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text("5", fontSize = 20.sp, color = Color.Red)
        Icon(
            painter = painterResource(id = R.drawable.ic_heart), // Replace with heart icon resource
            contentDescription = "Hearts",
            tint = Color.Red,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text("100", fontSize = 20.sp, color = Color.Yellow)
        Icon(
            painter = painterResource(id = R.drawable.ic_coin), // Replace with coin icon resource
            contentDescription = "Coins",
            tint = Color.Yellow,
            modifier = Modifier.size(24.dp)
        )
    }
}