package app.elite.mynote

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.asLiveData
import app.elite.mynote.data.prefs.PrefsStore
import app.elite.mynote.ui.MainActivity
import app.elite.mynote.ui.user.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {

    @Inject
    lateinit var store: PrefsStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        GlobalScope.launch(Dispatchers.Main) {
            delay(3000)
            store.isConnected().asLiveData().observe(this@SplashScreen) {
                val destination = if (it) MainActivity::class.java else LoginActivity::class.java
                startActivity(Intent(this@SplashScreen, destination))
                finish()
            }
        }
    }
}