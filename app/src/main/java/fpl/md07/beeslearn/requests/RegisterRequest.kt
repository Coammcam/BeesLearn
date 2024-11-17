package fpl.md07.beeslearn.requests

data class RegisterRequest(
    val username: String,
    val email: String,
    val password: String
)
