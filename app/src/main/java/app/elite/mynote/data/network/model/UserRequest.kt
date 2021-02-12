package app.elite.mynote.data.network.model

import app.elite.mynote.data.local.entity.Note

data class LoginRequest(
    val email: String,
    val password: String,
)

data class UserLoginResponse(
    val token: String,
    val notes: List<Note>? = null
)