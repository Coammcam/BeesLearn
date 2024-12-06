package fpl.md07.beeslearn.screens.tabs

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.platform.LocalContext
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
import fpl.md07.beeslearn.ui.theme.Nunito_Bold
import androidx.lifecycle.viewmodel.compose.viewModel

import androidx.compose.ui.layout.ContentScale
import androidx.core.content.ContextCompat
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import fpl.md07.beeslearn.GlobalVariable.UserSession
import fpl.md07.beeslearn.requests.UpdateUserRequest
import fpl.md07.beeslearn.viewmodels.EditProfileViewModel
import fpl.md07.beeslearn.createMultipartBody

@Composable
fun EditProfile(navController: NavController,editProfileViewModel: EditProfileViewModel = viewModel()) {

    val user = UserSession.currentUser
    Log.d("user information: ", user.toString())

    var name by remember { mutableStateOf(user?.username ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var dateOfBirth by remember { mutableStateOf(user?.dateOfBirth ?: "") }
    var phoneNumber by remember { mutableStateOf(user?.phoneNumber ?: "") }

    var avatarUri by remember { mutableStateOf<Uri?>(Uri.parse(user?.profileImageUrl)) }

    val context = LocalContext.current
    val scrollState = rememberScrollState()

    // Sử dụng ActivityResultContracts.GetContent() để chọn ảnh
    val getImageLauncher: ActivityResultLauncher<String> =
        rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            avatarUri = uri // Cập nhật URI của ảnh đã chọn
        }

    val permissionLauncher = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
        if (granted) {
            // Permission granted, proceed to pick an image
            getImageLauncher.launch("image/*")
        } else {
            Toast.makeText(context, "Permission denied to access images", Toast.LENGTH_SHORT).show()
        }
    }

    val avatarModel = if (avatarUri.toString().isNullOrEmpty()) {
        R.drawable.avatarsetting
    } else {
        avatarUri
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Nội dung có thể cuộn
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 80.dp), // Thêm khoảng trống để không bị che bởi nút
            horizontalAlignment = Alignment.CenterHorizontally
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
                Text("")
            }

            Spacer(modifier = Modifier.height(50.dp))

            AsyncImage(
                model = avatarModel,
                fallback = painterResource(id = R.drawable.avatarsetting),
                contentDescription = "Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(140.dp)
                    .clip(CircleShape)
                    .clickable {
                        // Check for permission before picking an image
                        when {
                            // If permission is already granted, open the image picker
                            ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES) == PackageManager.PERMISSION_GRANTED -> {
                                getImageLauncher.launch("image/*")
                            }
                            // If the app is running on Android 13+, request permission
                            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU -> {
                                permissionLauncher.launch(Manifest.permission.READ_MEDIA_IMAGES)
                            }
                            // If running on Android 12 or lower, request the old permission
                            else -> {
                                permissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                            }
                        }
                    }
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                "Change the profile image",
                fontSize = 10.sp,
                fontFamily = NunitoBold,
                color = Color(0xFF777777),
                style = TextStyle(textDecoration = TextDecoration.Underline),

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
                placeholder = "dd/MM/yyyy"
            )

            Spacer(modifier = Modifier.height(20.dp))

            CustomTextField(
                labelText = "Phone number",
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                placeholder = "09xxxxxxxx"
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    "Đổi mật khẩu",
                    color = Color(0xFF777777),
                    fontFamily = NunitoBold,
                    fontSize = 13.sp,
                    modifier = Modifier.clickable {
                        navController.navigate("ChangePasswordScreen")
                    }
                )
            }
        }

        // Nút lưu thay đổi luôn ở dưới cùng
        Button(
            onClick = {
                if (name.isEmpty() || email.isEmpty()) {
                    Toast.makeText(context, "Vui lòng nhập đầy đủ thông tin.", Toast.LENGTH_SHORT)
                        .show()
                    return@Button
                }

                val updatedUser = UpdateUserRequest(
                    email = email,
                    username = name,
                    phoneNumber = if (phoneNumber.isBlank()) null else phoneNumber,
                    dateOfBirth = if (dateOfBirth.isBlank()) null else dateOfBirth,
                    file = avatarUri?.let { createMultipartBody(it, context) }
                )

                editProfileViewModel.updateProfile(
                    updatedUser = updatedUser,
                    onSuccess = { updatedUserModel ->
                        UserSession.currentUser = updatedUserModel
                        Toast.makeText(context, "Cập nhật thành công!", Toast.LENGTH_SHORT).show()
                        navController.popBackStack()
                    },
                    onError = { errorMessage ->
                        Toast.makeText(context, "Lỗi: $errorMessage", Toast.LENGTH_LONG).show()
                        Log.e("EditProfile", "Lỗi cập nhật: $errorMessage")
                    }
                )
            },
            colors = ButtonDefaults.buttonColors(Color(0xFFFFD528)),
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter) // Đặt nút ở dưới cùng
                .padding(16.dp)
        ) {
            Text(
                text = "LƯU THAY ĐỔI",
                color = Color.White,
                fontFamily = Nunito_Bold
            )
        }
    }
}
fun createImageUri(context: Context): Uri? {
    val contentValues = ContentValues().apply {
        put(MediaStore.Images.Media.TITLE, "avatar_${System.currentTimeMillis()}")
        put(MediaStore.Images.Media.DESCRIPTION, "Avatar image")
        put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
    }

    // Insert the image in the media store
    val imageUri: Uri? = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

    return imageUri
}


@Preview(showBackground = true)
@Composable
private fun EditProfilePre() {
    val navController = rememberNavController()
    EditProfile(navController)
}


