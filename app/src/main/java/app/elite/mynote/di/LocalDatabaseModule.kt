package app.elite.mynote.di

import android.content.Context
import app.elite.mynote.data.local.dao.NoteDao
import com.example.firstapp.data.local.database.NoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object LocalDatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): NoteDatabase =
        NoteDatabase.getInstance(context = appContext)

    @Provides
    fun provideMovieDao(db: NoteDatabase): NoteDao =
        db.noteDao()


}