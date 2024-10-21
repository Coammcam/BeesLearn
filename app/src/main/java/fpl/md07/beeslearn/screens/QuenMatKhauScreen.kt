package com.example.myapplication.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fpl.md07.beeslearn.R

@Composable
fun QuenMatKhauScreen() {


    var number by remember { mutableStateOf("") }



    Column(
        modifier = Modifier
            .background(Color(0xffffffff))
            .padding(10.dp, top = 43.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painterResource(id = R.drawable.logo),
            contentDescription = "Cart button icon",
            modifier = Modifier.width(267.dp)
        )
        Text("Nhập số điện thoại:", Modifier.padding(top = 50.dp, end = 200.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth(),
            ) {
            OutlinedTextField(
                value = "", // Giá trị nhập
                onValueChange = { /* Xử lý thay đổi văn bản */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 60.dp)
                    .clip(RoundedCornerShape(10.dp))// Để lại không gian cho nút
            )
            Button(
                onClick = { /* Xử lý nhấp nút */ },
                modifier = Modifier
                    .align(Alignment.CenterEnd) // Đặt nút ở bên phải
                    .padding(end = 8.dp)
                    .clip(RoundedCornerShape(10.dp))// Khoảng cách bên phải
            ) {
                Text(
                    "Tiếp Tục",
                )
            }
        }


    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun QuenMatKhauScreenPreview() {
    QuenMatKhauScreen()
}