package fpl.md07.beeslearn.requests

import okhttp3.MultipartBody

data class UpdateUserRequest(
    val email: String,
    val username: String,
    val phoneNumber: String?,
    val dateOfBirth: String?,
    val file: MultipartBody.Part?
)