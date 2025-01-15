package fpl.md07.beeslearn.screens.tabs

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import fpl.md07.beeslearn.R
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import fpl.md07.beeslearn.GlobalVariable.UserSession
import fpl.md07.beeslearn.components.TopBarComponent
import fpl.md07.beeslearn.viewmodels.QuestionViewModel
import fpl.md07.beeslearn.viewmodels.UserDataViewModel

fun hexagonShape() = GenericShape { size, _ ->
    val width = size.width
    val height = size.height
    val radius = width / 2

    moveTo(radius, 0f)
    lineTo(width, height / 4)
    lineTo(width, 3 * height / 4)
    lineTo(radius, height)
    lineTo(0f, 3 * height / 4)
    lineTo(0f, height / 4)
    close()
}


@Composable
fun HomeLevelsScreen(navController: NavController) {
    val userDataViewModel: UserDataViewModel = viewModel()
    val questionViewModel: QuestionViewModel = viewModel()
    val currencyData by userDataViewModel.currencyData.observeAsState(null)
    val levels by questionViewModel.levels.observeAsState(ArrayList())

    val context = LocalContext.current

    var playerLevel by remember { mutableIntStateOf(1) }
    var levelExpReq by remember { mutableIntStateOf(0) }
    var playerExp by remember { mutableIntStateOf(0) }

    var playerLevelProgress by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(Unit) {
        userDataViewModel.getCurrencyData()
        questionViewModel.getLevels()
    }

    LaunchedEffect(currencyData) {
        if(currencyData != null){
            playerExp = currencyData!!.exp
            playerLevel = currencyData!!.level

            if(currencyData!!.level == 1){
                levelExpReq = 525
                UserSession.expReq = 525
            }else if(currencyData!!.level == 2){
                levelExpReq = 1050
                UserSession.expReq = 1050
            }

            playerLevelProgress = playerExp.toFloat() / levelExpReq.toFloat()

            if(currencyData!!.level == 3){
                playerLevelProgress = 1f
                levelExpReq = 1050
                UserSession.expReq = 1060
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
    ) {
        TopBarComponent(
            goBack = {navController.popBackStack()},
            showHoneyCombStatus = {}
        )

        Spacer(modifier = Modifier.height(35.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "LV$playerLevel",
                fontSize = 16.sp,
                color = Color.Black,
                fontFamily = Nunito_Bold,
                modifier = Modifier.padding(end = 8.dp)
            )

            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(20.dp)
//                    .background(
//                        Color(0xFFE5E5E5),
//                        shape = RoundedCornerShape(4.dp)
//                    )
            ) {
                LinearProgressIndicator(
                    progress = { playerLevelProgress },
                    modifier = Modifier.fillMaxSize().clip(RoundedCornerShape(4.dp)),
                    color = Color(0xFF8A1538)
                )

                Text(
                    text = "$playerExp/$levelExpReq",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(color = (0xFFFFAA01)),
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(bottom = 4.dp)
                )
            }

            if(playerLevel < 3){
                Text(
                    text = "LV${playerLevel + 1}",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontFamily = Nunito_Bold,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(-25.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            itemsIndexed(
                levels.toList()
            ) { index, level ->
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .width(169.dp)
                        .height(199.dp)
                        .offset(x = if (index % 2 != 0) 170.dp else 20.dp)
                        .clip(hexagonShape())
                        .border(
                            width = 8.dp,
                            color = Color(0xFFFFAA01),
                            shape = hexagonShape()
                        )
                        .background(Color(0xFFFFF176))
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable {
                                if(playerLevel >= level.level){
                                    navController.navigate("selectExercise/${level.level}")
                                }else{
                                    Toast.makeText(
                                        context,
                                        "Bạn cần hoàn thành level phía trước",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                    ) {
                        Image(
                            painter = painterResource(R.drawable.beeds),
                            contentDescription = "Level Image",
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .aspectRatio(1f),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Cấp độ ${level.level}",
                            fontFamily = Nunito_Bold,
                            fontSize = 14.sp,
                            color = Color.Black,
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewHomeLeversScreen() {
    val navController = rememberNavController()
    HomeLevelsScreen(navController)
}
