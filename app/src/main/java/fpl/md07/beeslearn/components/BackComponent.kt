package fpl.md07.beeslearn.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fpl.md07.beeslearn.R

@Composable
fun BackComponent(navController: NavController) {
    Icon(
        painter = painterResource(id = R.drawable.back_left), // Replace with actual back arrow resource
        contentDescription = "Back",
        modifier = Modifier.size(24.dp).clickable {navController.popBackStack() }
    )
}