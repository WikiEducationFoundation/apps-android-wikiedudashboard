package org.wikiedufoundation.wikiedudashboard.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.home.HomeActivity
import org.wikiedufoundation.wikiedudashboard.ui.welcome.WelcomeActivity
import org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding.WelcomeHostActivity

/**
 * Splash screen activity
 * ***/
class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val sharedPrefs = SharedPrefs(this)
        val handler = Handler()
        handler.postDelayed({
            if (sharedPrefs.isLoggedIn) {
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, WelcomeHostActivity::class.java))
                finish()
            }
        }, 1000)
    }
}
