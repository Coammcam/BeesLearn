package fpl.md07.beeslearn.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.GlideImage

@Composable
fun Test() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Image(
            painter = painterResource(id = R.drawable.beeds ),
            contentDescription = "Main Image",
            modifier = Modifier.size(200.dp).padding(top = 60.dp)
        )

        GlideImage(imageUrl = R.drawable.phaohoa, modifier = Modifier.size(200.dp))

        GlideImage(imageUrl = R.drawable.phaohoa, modifier = Modifier.size(200.dp))


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewTest() {
    Test()
}