package org.wikiedufoundation.wikiedudashboard.ui.settings

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.welcome.WelcomeActivity
import org.wikiedufoundation.wikiedudashboard.util.showCustomChromeTabs

/**
 * Activity for user settings of the profile part
 * ***/
class SettingsActivity : AppCompatActivity() {

    private var sharedPrefs: SharedPrefs? = null
    private var context: Context? = null
    private var toolbar: Toolbar? = null
    private var tvFeedback: TextView? = null
    private var tvShareApp: TextView? = null
    private var tvLicenses: TextView? = null
    private var tvPrivacyPolicy: TextView? = null
    private var tvTermsAndConditions: TextView? = null
    private var tvLogout: TextView? = null
    private var tvVersionCode: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        context = this
        sharedPrefs = SharedPrefs(this)
        toolbar = findViewById(R.id.toolbar)
        tvFeedback = findViewById(R.id.tv_feedback)
        tvShareApp = findViewById(R.id.tv_share_app)
        tvLicenses = findViewById(R.id.tv_licenses)
        tvPrivacyPolicy = findViewById(R.id.tv_privacy_policy)
        tvTermsAndConditions = findViewById(R.id.tv_terms_and_conditions)
        tvLogout = findViewById(R.id.tv_logout)
        tvVersionCode = findViewById(R.id.tv_version_code)
        toolbar?.setNavigationOnClickListener { onBackPressed() }
        tvFeedback?.setOnClickListener { sendEmailFeedback() }
        tvShareApp?.setOnClickListener { shareApp() }
        tvLicenses?.setOnClickListener { openLicenses() }
        tvPrivacyPolicy?.setOnClickListener { openPrivacyPolicy() }
        tvTermsAndConditions?.setOnClickListener { openTermsAndConditions() }
        tvLogout?.setOnClickListener { logOut() }
        tvVersionCode?.text = "1.001"

    }

    private fun sendEmailFeedback() {
        val emailArray:Array<String> = arrayOf("android-app@wikiedu.org")
        val intent = Intent(Intent.ACTION_SENDTO)
        intent.data = Uri.parse("mailto:") // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, emailArray)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Android App 1.01 Feedback")
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
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
        context?.showCustomChromeTabs("https://creativecommons.org/licenses/by-sa/3.0/")
    }

    private fun openTermsAndConditions() {
        context?.showCustomChromeTabs("https://wikiedu.org/terms-of-service/")
    }

    private fun openPrivacyPolicy() {
        context?.showCustomChromeTabs("https://wikiedu.org/privacy-policy/")
    }

    private fun logOut() {
        val alertDialog = AlertDialog.Builder(context)
        alertDialog.setTitle("Log Out")
                .setMessage("Are you sure? Logging out will remove all data from this device. ")
                .setCancelable(false)
                .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
                .setPositiveButton("Yes") { dialog, _ ->
                    sharedPrefs?.userName = ""
                    sharedPrefs?.cookies = ""
                    sharedPrefs?.setLogin(false)
                    val i = Intent(context, WelcomeActivity::class.java)
                    startActivity(i)

                    dialog.dismiss()
                }.show()
    }
}
