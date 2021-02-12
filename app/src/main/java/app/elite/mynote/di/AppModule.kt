package app.elite.mynote.di

import android.content.Context
import app.elite.mynote.data.prefs.PrefsStore
import app.elite.mynote.data.prefs.PrefsStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePrefs(@ApplicationContext context: Context): PrefsStore = PrefsStoreImpl(context)

}