package app.elite.mynote.ui.notes.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.elite.mynote.data.local.entity.Note
import app.elite.mynote.repository.note.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CreateNoteViewModel @Inject constructor(private val repository: NoteRepository) :
    ViewModel() {

    fun addNote(note: Note) {
        viewModelScope.launch {
            repository.insert(note)
        }
    }
}