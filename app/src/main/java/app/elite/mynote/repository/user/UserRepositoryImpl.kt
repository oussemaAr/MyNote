package app.elite.mynote.repository.user


import app.elite.mynote.data.network.RemoteDataSource
import app.elite.mynote.data.network.model.LoginRequest
import app.elite.mynote.data.network.model.RemoteNote
import app.elite.mynote.data.network.model.UserLoginResponse
import app.elite.mynote.data.prefs.PrefsStore
import app.elite.mynote.utils.cookies
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val store: PrefsStore
) : UserRepository {

    override fun loginAction(email: String, password: String): Flow<RemoteNote<UserLoginResponse>> = flow {
        val login = remoteDataSource.login(LoginRequest(email, password))
        emit(login.body()!!)
    }


    override suspend fun changeConnectionState() {
        store.connect()
    }

    override suspend fun saveToken(token: String) {
        store.saveToken(token)
        store.saveCookies(cookies)
    }

}