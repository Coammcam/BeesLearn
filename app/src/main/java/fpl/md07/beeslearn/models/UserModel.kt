package fpl.md07.beeslearn.models

data class UserModel(
    val email: String,
    val username: String,
    val password: String,
    val phoneNumber: String,
    val dateOfBirth: String,
    val profileImageUrl: String
)
