package app.elite.mynote

import android.app.Application
import androidx.viewbinding.BuildConfig
import app.elite.mynote.data.prefs.PrefsStore
import app.elite.mynote.utils.cookies
import app.elite.mynote.utils.token
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


@HiltAndroidApp
class App : Application() {

    @Inject
    lateinit var store: PrefsStore

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        applicationScope.launch {
            store.getToken().collect {
                token = it
            }
            store.getCookies().collect {
                cookies = it
            }
        }
    }

    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
}