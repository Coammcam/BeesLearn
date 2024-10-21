package com.example.myapplication.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.R


@Composable
fun TuanSuatScreen() {



    Column(
        modifier = Modifier
            .background(Color(0xfffff192))
            .padding(10.dp)
            .padding(top = 120.dp)
            .fillMaxSize()
        ,
    ) {
        Image( painterResource(id = R.drawable.logo),
            contentDescription = "Cart button icon",
            modifier = Modifier.width(170.dp).height(210.dp)
        )
        Spacer(modifier = Modifier.height(66.dp))
        Button(
            onClick = { /* TODO: handle login */ },
            modifier = Modifier
                .padding(top=30.dp,start = 30.dp)
                .width(300.dp)
                .height(48.dp)
                .shadow(elevation = 10.dp,
                    shape = RoundedCornerShape(12.dp),
                    clip = false
                ),

            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFF9D3   ))
        ) {

            Text(
                text = "15' mỗi ngày",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                color = Color(0xff591429)
            ) }

        Button(
            onClick = { /* TODO: handle login */ },
            modifier = Modifier
                .padding(top=30.dp,start = 30.dp)
                .width(300.dp)
                .height(48.dp)
                .shadow(elevation = 10.dp,
                    shape = RoundedCornerShape(12.dp),
                    clip = false
                ),

            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFF9D3   ))
        ) {

            Text(
                text = "30' mỗi ngày",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                color = Color(0xff591429)
            ) }
        Button(
            onClick = { /* TODO: handle login */ },
            modifier = Modifier
                .padding(top=30.dp,start = 30.dp)
                .width(300.dp)
                .height(48.dp)
                .shadow(elevation = 10.dp,
                    shape = RoundedCornerShape(12.dp),
                    clip = false
                ),

            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFFFFF9D3   ))
        ) {

            Text(
                text = "60' mỗi ngày",
                style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.Bold),
                color = Color(0xff591429)
            ) }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun TuanSuatScreenPreview() {
    TuanSuatScreen()
}