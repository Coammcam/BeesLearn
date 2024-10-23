package fpl.md07.beeslearn.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fpl.md07.beeslearn.ui.theme.Nunito_Bold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = labelText,
            fontFamily = Nunito_Bold, // Đặt font cho Text
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF777777)

        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent
            ),
            placeholder = {
                Text(
                    placeholder,
                    fontFamily = Nunito_Bold,
                    fontSize = 14.sp
                )
            },
            textStyle = TextStyle(
                fontFamily = Nunito_Bold,
                fontSize = 14.sp
            ),
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomTextFieldPreview() {
    var email by remember { mutableStateOf("") }

    CustomTextField(
        labelText = "Email:",
        value = email,
        onValueChange = { email = it },
        placeholder = "Nhập email của bạn"
    )
}
