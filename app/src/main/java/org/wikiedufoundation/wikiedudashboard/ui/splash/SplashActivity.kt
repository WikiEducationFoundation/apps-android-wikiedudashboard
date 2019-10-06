package org.wikiedufoundation.wikiedudashboard.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.home.HomeActivity
import org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding.WelcomeHostActivity

class SplashActivity : AppCompatActivity() {

    private var sharedPrefs: SharedPrefs? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val context = this
        sharedPrefs = SharedPrefs(context)
        val handler = Handler()
        handler.postDelayed({
            if (sharedPrefs!!.isLoggedIn) {
                startActivity(Intent(context, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(context, WelcomeHostActivity::class.java))
                finish()
            }
        }, 1000)
    }
}
