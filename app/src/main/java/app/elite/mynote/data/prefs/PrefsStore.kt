package app.elite.mynote.data.prefs

import kotlinx.coroutines.flow.Flow

interface PrefsStore {

    fun isNightMode(): Flow<Boolean>

    fun isConnected(): Flow<Boolean>

    fun getToken(): Flow<String>

    fun getCookies(): Flow<String>

    suspend fun toggleNightMode()

    suspend fun connect()

    suspend fun saveToken(token: String)

    suspend fun saveCookies(cookies: String)

    fun lastSyncTimestamp(): Flow<Long>

    suspend fun saveSyncTimestamp()

}