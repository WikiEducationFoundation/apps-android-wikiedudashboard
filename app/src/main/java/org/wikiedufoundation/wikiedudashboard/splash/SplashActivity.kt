package org.wikiedufoundation.wikiedudashboard.splash

import android.content.Context
import android.content.Intent
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.helper.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.home.HomeActivity
import org.wikiedufoundation.wikiedudashboard.welcome.WelcomeActivity

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
                startActivity(Intent(context, WelcomeActivity::class.java))
                finish()
            }
        }, 1000)
    }
}
