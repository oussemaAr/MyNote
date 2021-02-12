package app.elite.mynote.data.local.dao

import androidx.room.*
import app.elite.mynote.data.local.entity.Note
import kotlinx.coroutines.flow.Flow


@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(note: Note)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(notes: List<Note>)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("SELECT * FROM note_table")
    fun getAllNotes(): Flow<List<Note>>

    @Query("SELECT * FROM note_table WHERE note_synchronized = :status")
    fun getNotesByStatus(status: Boolean): Flow<List<Note>>

    @Query("SELECT * FROM note_table WHERE id = :noteId LIMIT 1")
    suspend fun selectNoteById(noteId: Int): Note?

}