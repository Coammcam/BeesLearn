package fpl.md07.beeslearn.screens.tabs

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import fpl.md07.beeslearn.R
import fpl.md07.beeslearn.components.BackComponent
import fpl.md07.beeslearn.components.CustomTextField
import fpl.md07.beeslearn.screens.NunitoBold

@Composable
fun EditProfile(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var dateOfBirth by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 10.dp, end = 10.dp, top = 10.dp, bottom = 30.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            BackComponent(navController)
            Text(
                "Edit Profile",
                fontSize = 18.sp,
                fontFamily = NunitoBold,
                color = colorResource(id = R.color.secondary_color),
                modifier = Modifier.padding(end = 30.dp)

            )
            Text("") // blank for SpaceBetween
        }
    }

    Column(
        modifier = Modifier.fillMaxSize().padding(horizontal = 40.dp, vertical = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = R.drawable.avatarsetting),
            contentDescription = "Avatar",
            modifier = Modifier
                .size(140.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            "Change the profile image",
            fontSize = 10.sp,
            fontFamily = NunitoBold,
            color = Color(0xFF777777),
            style = TextStyle(textDecoration = TextDecoration.Underline)
        )

        CustomTextField(
            labelText = "Name",
            value = name,
            onValueChange = { name = it },
            placeholder = "Beeslearn"
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            labelText = "Email",
            value = email,
            onValueChange = { email = it },
            placeholder = "Beeslearn@email.com"
        )

        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(
            labelText = "Date of birth",
            value = dateOfBirth,
            onValueChange = { dateOfBirth = it },
            placeholder = "08/04/2004"
        )

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            "Joined September 2024",
            fontSize = 15.sp,
            fontFamily = NunitoBold,
            color = Color(0xFF777777),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EditProfilePre(){
    val navController = rememberNavController()
    EditProfile(navController)
}