package com.example.myapplication.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpl.md07.beeslearn.R


@Composable
fun LoginScreen() {

    var email by remember { mutableStateOf("") }

    var password by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }



    Column(
        modifier = Modifier
            .background(Color(0xffffffff))
            .padding(10.dp)
            .fillMaxSize()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       Image( painterResource(id = R.drawable.logo),
           contentDescription = "Cart button icon",
           modifier = Modifier.width(267.dp)
       )
        Text("Email:",Modifier.padding(end = 320.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Nhập văn bản") },

            placeholder = { Text("Placeholder") },
            modifier = Modifier.fillMaxWidth()
        )
        Text("Password",Modifier.padding(end = 290.dp, top = 20.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Nhập văn bản") },

            modifier = Modifier.fillMaxWidth()


        )


        Text("Quên mật khẩu?",Modifier.padding(start = 240.dp))

        Button(
            modifier = Modifier
                .padding(10.dp, top = 20.dp)
                .width(262.dp),

            onClick = {},
            colors = ButtonDefaults.buttonColors(Color(0xffffd528))
        ) {

            Text(
                text = "ĐĂNG NHẬP",
                modifier = Modifier
                    .padding(10.dp),
            )
        }
        Row (modifier = Modifier.padding(top = 50.dp)) {
            Text("Không có tài khoản?")
            Text("Đăng Ký", color = Color(0xffffd528))

        }

    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}