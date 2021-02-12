package app.elite.mynote.ui.user.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import app.elite.mynote.R
import app.elite.mynote.databinding.ActivityLoginBinding
import app.elite.mynote.ui.MainActivity
import app.elite.mynote.utils.stringText
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginAction.setOnClickListener { view ->
            viewModel.sendLogin(
                binding.emailText.stringText(),
                binding.passwordText.stringText()
            )
                .observe(this) {
                    Timber.d(it.data.toString())
                    if (it.code == 200) {
                        Snackbar.make(
                            view,
                            getString(R.string.welcome_message),
                            Snackbar.LENGTH_LONG
                        ).show()
                        viewModel.saveToken(it.data!!)
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    } else {
                        Snackbar.make(view, it.error.toString(), Snackbar.LENGTH_LONG).show()
                    }
                }
        }
    }
}