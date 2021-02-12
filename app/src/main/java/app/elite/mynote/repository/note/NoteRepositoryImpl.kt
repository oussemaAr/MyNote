package app.elite.mynote.repository.note

import app.elite.mynote.data.local.dao.NoteDao
import app.elite.mynote.data.local.entity.Note
import app.elite.mynote.data.network.RemoteDataSource
import app.elite.mynote.utils.NOTE_STATUS
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val localDataSource: NoteDao,
    private val remoteDataSource: RemoteDataSource
) : NoteRepository {

    override fun getNotesFromLocalDatabase(synchronized: NOTE_STATUS): Flow<List<Note>> {
        return when (synchronized) {
            NOTE_STATUS.SYNC -> localDataSource.getNotesByStatus(true)
            NOTE_STATUS.NOT_SYNC -> localDataSource.getNotesByStatus(false)
            NOTE_STATUS.ALL -> localDataSource.getAllNotes()
        }
    }


    override suspend fun loadNotesFromNetwork(): Boolean {
        try {
            val response = remoteDataSource.loadNotes()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null)
                    insertAll(response.body()!!.data!!)
                return true
            }
            return false
        } catch (ex: Exception) {
            return false
        }
    }

    override suspend fun insertAll(notes: List<Note>) = localDataSource.insertAll(notes)

    override suspend fun insert(note: Note) = localDataSource.insert(note)

    override suspend fun updateNote(note: Note) = localDataSource.updateNote(note)

    override suspend fun deleteNote(note: Note) = localDataSource.deleteNote(note)


}