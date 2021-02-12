package app.elite.mynote.ui.user.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import app.elite.mynote.data.network.model.UserLoginResponse
import app.elite.mynote.repository.note.NoteRepository
import app.elite.mynote.repository.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val noteRepository: NoteRepository
) : ViewModel() {

    init {
        Timber.d("Init LoginViewModel")
    }

    fun sendLogin(email: String, password: String) =
        userRepository.loginAction(email, password).asLiveData()

    fun saveToken(response: UserLoginResponse) = viewModelScope.launch {
        userRepository.saveToken(response.token)
        noteRepository.insertAll(response.notes!!)
        userRepository.changeConnectionState()
    }
}