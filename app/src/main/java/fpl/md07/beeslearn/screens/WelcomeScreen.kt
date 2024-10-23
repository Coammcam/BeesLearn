package com.example.myapplication.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R


@Composable
fun WelcomeScreen() {
//    Column (modifier = Modifier.fillMaxSize().background(color = Color.Yellow),
//
//        horizontalAlignment = Alignment.CenterHorizontally,
//
//        ){
//        Column(modifier = Modifier.padding(top = 150.dp)) {
//            Text("Bắt đầu học cùng Bee!" ,color = Color.Red, fontSize = 24.sp)
//            Button(
//                onClick = {}, Modifier.background(Color.Red)) {
//                Image(
//                    painterResource(id = R.drawable.email),
//                    contentDescription ="Cart button icon",
//                    modifier = Modifier.size(20.dp))
//                Text(
//                    text = "Tiep tuc với Email",
//                    modifier = Modifier
//                        .padding(10.dp),
//                    color = Color.Red
//                )
//            }
//            Button(
//                onClick = {}) {
//                Image(
//                    painterResource(id = R.drawable.email),
//                    contentDescription ="Cart button icon",
//                    modifier = Modifier.size(20.dp))
//                Text(text = "Tiep tuc với Email", Modifier.padding(start = 10.dp))
//            }
//            Button(
//                onClick = {}) {
//                Image(
//                    painterResource(id = R.drawable.email),
//                    contentDescription ="Cart button icon",
//                    modifier = Modifier.size(20.dp))
//                Text(text = "Tiep tuc với Email", Modifier.padding(start = 10.dp))
//            }
//            Button(
//                onClick = {}) {
//                Image(
//                    painterResource(id = R.drawable.email),
//                    contentDescription ="Cart button icon",
//                    modifier = Modifier.size(20.dp))
//                Text(text = "Tiep tuc với Email", Modifier.padding(start = 10.dp))
//            }
//        }
//    }


    Column(
        modifier = Modifier
            .background(Color(0xfffff192))
            .padding(10.dp)
            .fillMaxSize()
            ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Bắt đầu học cùng Bee !",
            fontSize = 35.sp,
            color = Color(0xFF591429),
            fontWeight = FontWeight.Bold
        )
        Button(
            modifier = Modifier
                .padding(10.dp, top = 20.dp)
                .width(262.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(Color(0xff591429))
        ) {
            Image(
                painterResource(id = R.drawable.email),
                contentDescription = "Cart button icon",
                modifier = Modifier.size(40.dp),
            )
            Text(
                text = "Tiếp tục với Email",
                modifier = Modifier
                    .padding(10.dp),
            )
        }
        Button(
            modifier = Modifier
                .padding(10.dp, top = 20.dp)
                .width(262.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(Color(0xff130101))
        ) {
            Image(
                painterResource(id = R.drawable.apple),
                contentDescription = "Cart button icon",
                modifier = Modifier.size(40.dp),
            )
            Text(
                text = "Tiếp tục với Apple",
                modifier = Modifier
                    .padding(10.dp),
            )
        }
        Button(
            modifier = Modifier
                .padding(10.dp, top = 20.dp)
                .width(262.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(Color(0xff475993))
        ) {
            Image(
                painterResource(id = R.drawable.facebook),
                contentDescription = "Cart button icon",
                modifier = Modifier.size(40.dp),
            )
            Text(
                text = "Tiếp tục với Facebook",
                modifier = Modifier
                    .padding(10.dp),
            )
        }
        Button(
            modifier = Modifier
                .padding(10.dp, top = 20.dp)
                .width(262.dp),
            onClick = {},
            colors = ButtonDefaults.buttonColors(Color(0xffffffff))
        ) {
            Image(
                painterResource(id = R.drawable.google),
                contentDescription = "Cart button icon",
                modifier = Modifier.size(40.dp),
            )
            Text(
                text = "Tiếp tục với Google",
                modifier = Modifier
                    .padding(10.dp),
                color = Color.Black
            )
        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun WelcomeScreenPreview() {
    WelcomeScreen()
}