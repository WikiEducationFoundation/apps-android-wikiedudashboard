package org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.wikiedufoundation.wikiedudashboard.R

/**
 * This is the host activity for the welcome onboarding fragments
 */
class WelcomeHostActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_host)
    }
}
