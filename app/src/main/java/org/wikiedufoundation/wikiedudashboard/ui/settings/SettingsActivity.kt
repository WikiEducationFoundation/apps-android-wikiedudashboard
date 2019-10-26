package org.wikiedufoundation.wikiedudashboard.ui.settings

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.koin.android.ext.android.inject
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.welcome.WelcomeActivity
import org.wikiedufoundation.wikiedudashboard.util.showCustomChromeTabs

/**
 * Activity for user settings of the profile part
 * ***/
class SettingsActivity : AppCompatActivity() {

    private val sharedPrefs: SharedPrefs by inject()

    private lateinit var toolbar: Toolbar
    private lateinit var tvFeedback: TextView
    private lateinit var tvShareApp: TextView
    private lateinit var tvLicenses: TextView
    private lateinit var tvPrivacyPolicy: TextView
    private lateinit var tvTermsAndConditions: TextView
    private lateinit var tvLogout: TextView
    private lateinit var tvVersionCode: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        toolbar = findViewById(R.id.toolbar)
        tvFeedback = findViewById(R.id.tv_feedback)
        tvShareApp = findViewById(R.id.tv_share_app)
        tvLicenses = findViewById(R.id.tv_licenses)
        tvPrivacyPolicy = findViewById(R.id.tv_privacy_policy)
        tvTermsAndConditions = findViewById(R.id.tv_terms_and_conditions)
        tvLogout = findViewById(R.id.tv_logout)
        tvVersionCode = findViewById(R.id.tv_version_code)
        toolbar.setNavigationOnClickListener { onBackPressed() }
        tvFeedback.setOnClickListener { sendEmailFeedback() }
        tvShareApp.setOnClickListener { shareApp() }
        tvLicenses.setOnClickListener { openLicenses() }
        tvPrivacyPolicy.setOnClickListener { openPrivacyPolicy() }
        tvTermsAndConditions.setOnClickListener { openTermsAndConditions() }
        tvLogout.setOnClickListener { logOut() }
        tvVersionCode.text = "1.001"

    }

    private fun sendEmailFeedback() {
        val emailArray:Array<String> = arrayOf("android-app@wikiedu.org")
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, emailArray)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Android App 1.01 Feedback")
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun shareApp() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Download the App from https://play.google.com/store/apps/details?id=org.wikiedufoundation.wikiedudashboard ")
        val mailer = Intent.createChooser(intent, null)
        startActivity(mailer)
    }

    private fun openLicenses() {
        this.showCustomChromeTabs("https://creativecommons.org/licenses/by-sa/3.0/")
    }

    private fun openTermsAndConditions() {
        this.showCustomChromeTabs("https://wikiedu.org/terms-of-service/")
    }

    private fun openPrivacyPolicy() {
        this.showCustomChromeTabs("https://wikiedu.org/privacy-policy/")
    }

    private fun logOut() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Log Out")
                .setMessage("Are you sure? Logging out will remove all data from this device. ")
                .setCancelable(false)
                .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                .setPositiveButton("Yes") { dialog, _ ->
                    sharedPrefs.userName = ""
                    sharedPrefs.cookies = ""
                    sharedPrefs.setLogin(false)
                    val i = Intent(this, WelcomeActivity::class.java)
                    startActivity(i)

                    dialog.dismiss()
                }.show()
    }
}
