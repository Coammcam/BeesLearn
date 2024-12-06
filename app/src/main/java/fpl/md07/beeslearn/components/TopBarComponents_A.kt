package fpl.md07.beeslearn.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.GlobalVariable.UserSession
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.viewmodels.UserDataViewModel

@Composable
fun TopBarComponent_A(goBack: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BackComponent_A(){
            goBack()
        }
        IconTopComponent_A()
    }
}

@Composable
fun BackComponent_A(goBack: ()->Unit) {
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_back),
        contentDescription = "Back",
        modifier = Modifier
            .size(30.dp)
            .clickable { goBack() }
    )
}

@Composable
fun IconTopComponent_A() {

    val userDataViewModel: UserDataViewModel = viewModel()
    val currencyData by userDataViewModel.currencyData.observeAsState()

    // Heart and coins row
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Heart with background
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.primary_color), shape = RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.heart),
                    contentDescription = "Heart",
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${currencyData?.honeyComb ?: "0"}",
                    fontSize = 16.sp,
                    fontFamily = customFont,
                    color = colorResource(id = R.color.secondary_color)
                )
            }
        }

        // Coins with background
        Box(
            modifier = Modifier
                .background(color = colorResource(id = R.color.primary_color), shape = RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 4.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.honey),
                    contentDescription = "Coins",
                    modifier = Modifier.size(22.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "${currencyData?.honeyJar ?: "0"}",
                    fontFamily = customFont,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.secondary_color)
                )
            }
        }
    }
}