package app.elite.mynote.data.network

import app.elite.mynote.data.network.model.LoginRequest
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val serviceAPI: ServiceAPI
) {

    suspend fun login(loginRequest: LoginRequest) = serviceAPI.loginAction(loginRequest)

    suspend fun loadNotes() = serviceAPI.getUserNotes()
}

