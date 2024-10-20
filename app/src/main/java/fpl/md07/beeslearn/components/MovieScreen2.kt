package fpl.md07.beeslearn.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpl.md07.beeslearn.R

@Composable
fun MovieScreen2 () {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Image(
            painter = painterResource(R.drawable.back_left),
            contentDescription = "Back",
            modifier = Modifier
                .padding(top = 50.dp, start = 20.dp, bottom = 20.dp)
                .clickable { /* Handle back navigation */ }
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .shadow(24.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.posterphim5_1),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth()
            )
            Image(
                painter = painterResource(R.drawable.posterphim5_2),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 20.dp, end = 20.dp, top = 10.dp)
            )
        }
    }
}

@Preview (showBackground = true, showSystemUi = true)
@Composable
fun PreviewMovieScreen2 () {
    MovieScreen2()
}
