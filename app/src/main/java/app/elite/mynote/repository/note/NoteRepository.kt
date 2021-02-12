package app.elite.mynote.repository.note

import app.elite.mynote.data.local.entity.Note
import app.elite.mynote.utils.NOTE_STATUS
import kotlinx.coroutines.flow.Flow

interface NoteRepository {

    fun getNotesFromLocalDatabase(synchronized: NOTE_STATUS = NOTE_STATUS.ALL): Flow<List<Note>>

    suspend fun loadNotesFromNetwork(): Boolean

    suspend fun insertAll(notes: List<Note>)

    suspend fun insert(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)

}