package fpl.md07.beeslearn.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.models.CurrencyModel
import fpl.md07.beeslearn.viewmodels.UserDataViewModel

@Composable
fun TopBarComponent(goBack: () -> Unit, showHoneyCombStatus: ()->Unit) {

    val userDataViewModel: UserDataViewModel = viewModel()
    val currencyData by userDataViewModel.currencyData.observeAsState(CurrencyModel())

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        BackComponent_A(){
            goBack()
        }
        IconTopComponent(
            honeyCombCount = currencyData?.honeyComb,
            honeyJarCount = currencyData?.honeyJar,
            userScore = currencyData?.score
        ){
            showHoneyCombStatus()
        }
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

//@Composable
//fun IconTopComponent_A(showHoneyCombStatus: () -> Unit) {
//
//    val userDataViewModel: UserDataViewModel = viewModel()
//    val currencyData by userDataViewModel.currencyData.observeAsState()
//
//    // Heart and coins row
//    Row(
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.spacedBy(16.dp)
//    ) {
//        // Heart with background
//        Box(
//            modifier = Modifier
//                .background(color = colorResource(id = R.color.primary_color), shape = RoundedCornerShape(50))
//                .padding(horizontal = 12.dp, vertical = 4.dp)
//                .clickable { showHoneyCombStatus() }
//        ) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    painter = painterResource(id = R.drawable.heart),
//                    contentDescription = "Heart",
//                    modifier = Modifier.size(16.dp)
//                )
//                Spacer(modifier = Modifier.width(4.dp))
//                Text(
//                    text = "${currencyData?.honeyComb ?: "0"}",
//                    fontSize = 16.sp,
//                    fontFamily = customFont,
//                    color = colorResource(id = R.color.secondary_color)
//                )
//            }
//        }
//
//        // Coins with background
//        Box(
//            modifier = Modifier
//                .background(color = colorResource(id = R.color.primary_color), shape = RoundedCornerShape(50))
//                .padding(horizontal = 12.dp, vertical = 4.dp)
//        ) {
//            Row(verticalAlignment = Alignment.CenterVertically) {
//                Image(
//                    painter = painterResource(id = R.drawable.honey),
//                    contentDescription = "Coins",
//                    modifier = Modifier.size(22.dp)
//                )
//                Spacer(modifier = Modifier.width(4.dp))
//                Text(
//                    text = "${currencyData?.honeyJar ?: "0"}",
//                    fontFamily = customFont,
//                    fontSize = 16.sp,
//                    color = colorResource(id = R.color.secondary_color)
//                )
//            }
//        }
//    }
//}