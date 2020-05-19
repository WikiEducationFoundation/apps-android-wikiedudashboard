package org.wikiedufoundation.wikiedudashboard.ui.settings

import android.app.AlertDialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.library.BuildConfig
import kotlinx.android.synthetic.main.activity_settings.*
import org.koin.android.ext.android.inject
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.welcome.WelcomeActivity
import org.wikiedufoundation.wikiedudashboard.util.showCustomChromeTabs
import org.wikiedufoundation.wikiedudashboard.R

/**
 * Activity for user settings of the profile part
 * ***/
class SettingsActivity : AppCompatActivity() {

    private val sharedPrefs: SharedPrefs by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        toolbar.setNavigationOnClickListener { onBackPressed() }
        textViewFeedback.setOnClickListener { sendEmailFeedback() }
        textViewShareApp.setOnClickListener { shareApp() }
        textViewLicenses.setOnClickListener { openLicenses() }
        textViewPrivacyPolicy.setOnClickListener { openPrivacyPolicy() }
        textViewTermsAndConditions.setOnClickListener { openTermsAndConditions() }
        textViewLogout.setOnClickListener { logOut() }
        textViewVersionCode.text = BuildConfig.VERSION_NAME
    }

    private fun sendEmailFeedback() {
        val emailArray: Array<String> = arrayOf("android-app@wikiedu.org")
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, emailArray)
        intent.putExtra(Intent.EXTRA_SUBJECT, R.string.android_app_feedback)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

    private fun shareApp() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, R.string.download_app)
        val mailer = Intent.createChooser(intent, null)
        startActivity(mailer)
    }

    private fun openLicenses() {
        this.showCustomChromeTabs(R.string.creativecommons_license_url.toString())
    }

    private fun openTermsAndConditions() {
        this.showCustomChromeTabs(R.string.wikiedu_termsofservice_url.toString())
    }

    private fun openPrivacyPolicy() {
        this.showCustomChromeTabs(R.string.wikiedu_privacyurl.toString())
    }

    private fun logOut() {
        val alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle(R.string.logout)
                .setMessage(R.string.logout_confirmation)
                .setCancelable(false)
                .setNegativeButton(R.string.no) { dialog, _ -> dialog.dismiss() }
                .setPositiveButton(R.string.yes) { dialog, _ ->
                    sharedPrefs.userName = ""
                    sharedPrefs.cookies = ""
                    sharedPrefs.setLogin(false)
                    val i = Intent(this, WelcomeActivity::class.java)
                    startActivity(i)

                    dialog.dismiss()
                }.show()
    }
}
