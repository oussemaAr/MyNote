package app.elite.mynote.data.network

import app.elite.mynote.data.local.entity.Note
import app.elite.mynote.data.network.model.LoginRequest
import app.elite.mynote.data.network.model.RemoteNote
import app.elite.mynote.data.network.model.UserLoginResponse
import app.elite.mynote.utils.NOTES
import app.elite.mynote.utils.USER_CREATE
import app.elite.mynote.utils.USER_LOGIN
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceAPI {

    @POST(USER_LOGIN)
    suspend fun loginAction(
        @Body loginRequest: LoginRequest
    ): Response<RemoteNote<UserLoginResponse>>

    @POST(USER_CREATE)
    suspend fun registerAction(
        @Body loginRequest: LoginRequest
    ): RemoteNote<String>


    @GET(NOTES)
    suspend fun getUserNotes(): Response<RemoteNote<List<Note>>>


}