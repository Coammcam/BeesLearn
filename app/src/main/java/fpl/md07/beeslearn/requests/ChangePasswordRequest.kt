package fpl.md07.beeslearn.requests

data class ChangePasswordRequest(
    val email: String,
    val oldPassword: String,
    val newPassword: String
)
