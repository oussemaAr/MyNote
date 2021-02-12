package app.elite.mynote.repository.user

import app.elite.mynote.data.network.model.RemoteNote
import app.elite.mynote.data.network.model.UserLoginResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface UserRepository {

    fun loginAction(email: String, password: String): Flow<RemoteNote<UserLoginResponse>>

    suspend fun changeConnectionState()

    suspend fun saveToken(token: String)
}