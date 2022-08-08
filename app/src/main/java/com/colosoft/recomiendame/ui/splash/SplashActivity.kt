package com.colosoft.recomiendame.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import com.colosoft.recomiendame.MainActivity
import com.colosoft.recomiendame.databinding.ActivitySplashBinding
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hiding the title bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        supportActionBar?.hide();

        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        val view = splashBinding.root
        setContentView(view)
        val timer = Timer()
        timer.schedule(
            timerTask {
                goToMainActivity()
            }, 2000
        )
    }
    private fun goToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}