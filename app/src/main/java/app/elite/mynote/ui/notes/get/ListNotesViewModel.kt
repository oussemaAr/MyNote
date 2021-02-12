package app.elite.mynote.ui.notes.get

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elite.mynote.repository.note.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ListNotesViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    fun syncData() = viewModelScope.launch {
        noteRepository.loadNotesFromNetwork()
    }

    val data = noteRepository.getNotesFromLocalDatabase().map {
        Result.success(it)
    }

}

