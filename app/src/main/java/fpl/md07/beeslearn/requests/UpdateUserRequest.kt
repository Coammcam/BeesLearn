package fpl.md07.beeslearn.requests

data class UpdateUserRequest(
    val email: String,
    val username: String,
    val phoneNumber: String?,
    val dateOfBirth: String?,
    val profileImageUrl: String
)
