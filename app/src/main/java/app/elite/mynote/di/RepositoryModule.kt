package app.elite.mynote.di

import app.elite.mynote.data.local.dao.NoteDao
import app.elite.mynote.data.network.RemoteDataSource
import app.elite.mynote.data.prefs.PrefsStore
import app.elite.mynote.repository.note.NoteRepository
import app.elite.mynote.repository.note.NoteRepositoryImpl
import app.elite.mynote.repository.user.UserRepository
import app.elite.mynote.repository.user.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideNoteRepository(dao: NoteDao, networkClient: RemoteDataSource): NoteRepository =
        NoteRepositoryImpl(dao, networkClient)


    @Provides
    fun provideUserRepository(
        remoteDataSource: RemoteDataSource,
        store: PrefsStore
    ): UserRepository =
        UserRepositoryImpl(remoteDataSource, store)


}