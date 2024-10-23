package fpl.md07.beeslearn.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
fun CustomPasswordField(
    labelText: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) } // Trạng thái hiển thị/ẩn mật khẩu

    Column(modifier = modifier) {
        Text(
            text = labelText,
            fontFamily = Nunito_Bold,
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
            visualTransformation = if (passwordVisible) {
                // Show password
                androidx.compose.ui.text.input.VisualTransformation.None
            } else {
                // Hide password
                androidx.compose.ui.text.input.PasswordVisualTransformation()
            },
            trailingIcon = {
                Text(
                    text = if (passwordVisible) "Ẩn" else "Hiện",
                    modifier = Modifier
                        .clickable { passwordVisible = !passwordVisible },
                    color = Color(0xffffd528),
                    fontFamily = Nunito_Bold
                )
            },
            modifier = Modifier.fillMaxWidth().height(50.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CustomPasswordFieldPreview() {
    var password by remember { mutableStateOf("") }

    CustomPasswordField(
        labelText = "Password:",
        value = password,
        onValueChange = { password = it },
        placeholder = "Nhập mật khẩu của bạn"
    )
}
