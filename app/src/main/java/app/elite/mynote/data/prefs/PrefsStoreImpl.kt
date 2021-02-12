package app.elite.mynote.data.prefs

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import app.elite.mynote.utils.STORE_NAME
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class PrefsStoreImpl(
    context: Context
) : PrefsStore {

    private val dataStore = context.createDataStore(
        STORE_NAME
    )

    override
    fun isNightMode() = dataStore.data.catch { exception ->
        if (exception is IOException)
            emit(emptyPreferences())
        else
            throw exception
    }.map {
        it[PreferencesKeys.NIGHT_MODE_KEY] ?: false
    }

    override fun isConnected() = dataStore.data.catch { exception ->
        if (exception is IOException)
            emit(emptyPreferences())
        else
            throw exception
    }.map {
        it[PreferencesKeys.IS_CONNECTED_KEY] ?: false
    }

    override fun getToken() = dataStore.data.catch { exception ->
        if (exception is IOException)
            emit(emptyPreferences())
        else
            throw exception
    }.map {
        it[PreferencesKeys.TOKEN_KEY] ?: ""
    }

    override fun getCookies() = dataStore.data.catch { exception ->
        if (exception is IOException)
            emit(emptyPreferences())
        else
            throw exception
    }.map {
        it[PreferencesKeys.COOKIES_KEY] ?: ""
    }

    override suspend fun toggleNightMode() {
        dataStore.edit {
            it[PreferencesKeys.NIGHT_MODE_KEY] = !(it[PreferencesKeys.NIGHT_MODE_KEY] ?: false)
        }
    }

    override suspend fun connect() {
        dataStore.edit {
            it[PreferencesKeys.IS_CONNECTED_KEY] = !(it[PreferencesKeys.IS_CONNECTED_KEY] ?: false)
        }
    }

    override suspend fun saveToken(token: String) {
        dataStore.edit {
            it[PreferencesKeys.TOKEN_KEY] = token
        }
    }

    override suspend fun saveCookies(cookies: String) {
        dataStore.edit {
            it[PreferencesKeys.COOKIES_KEY] = cookies
        }
    }

    override fun lastSyncTimestamp() = dataStore.data.catch { exception ->
        if (exception is IOException)
            emit(emptyPreferences())
        else
            throw exception
    }.map {
        it[PreferencesKeys.LAST_SYNC_KEY] ?: 0L
    }

    override suspend fun saveSyncTimestamp() {
        dataStore.edit {
            it[PreferencesKeys.LAST_SYNC_KEY] = System.currentTimeMillis()
        }
    }
}

private object PreferencesKeys {
    val NIGHT_MODE_KEY = booleanPreferencesKey("dark_theme_enable")
    val IS_CONNECTED_KEY = booleanPreferencesKey("is_connected")
    val TOKEN_KEY = stringPreferencesKey("token_key")
    val COOKIES_KEY = stringPreferencesKey("cookies_key")
    val LAST_SYNC_KEY = longPreferencesKey("last_sync_key")
}

