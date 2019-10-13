package org.wikiedufoundation.wikiedudashboard.data.preferences

import android.content.Context
import android.content.SharedPreferences
import timber.log.Timber

/**
 * SharedPreferences class to store temporary data or handle sessions
 *
 * @property _context Context
 * */
class SharedPrefs(_context: Context) {

    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor

    private val PRIVATE_MODE = 0

    val isLoggedIn: Boolean
        get() = pref.getBoolean(KEY_IS_LOGGEDIN, false)

    var wikiEduDashboardCookies: String?
        get() = pref.getString(KEY_WIKI_EDU_DASHBOARD_COOKIES, null)
        set(cookies) {
            editor.putString(KEY_WIKI_EDU_DASHBOARD_COOKIES, cookies)
            editor.commit()
            Timber.d("WikiEdu Dashboard Cookies modified!")
        }

    var cookies: String?
        get() = pref.getString(KEY_COOKIES, null)
        set(cookies) {
            editor.putString(KEY_COOKIES, cookies)
            editor.commit()
            Timber.d("Cookies modified!")
        }

    var outreachDashboardCookies: String?
        get() = pref.getString(KEY_OUTREACH_DASHBOARD_COOKIES, null)
        set(cookies) {
            editor.putString(KEY_OUTREACH_DASHBOARD_COOKIES, cookies)
            editor.commit()
            Timber.d("Outreach Dashboard Cookies modified!")
        }

    var userName: String?
        get() = pref.getString(KEY_USERNAME, null)
        set(username) {
            editor.putString(KEY_USERNAME, username)
            editor.commit()
            Timber.d("Username modified!")
        }

    init {
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }

    /**
     * Use [setLogin] to check if user is logged in, then store the login session
     *
     * @param isLoggedIn boolean value to check whether the user has logged in
     ***/
    fun setLogin(isLoggedIn: Boolean) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn)
        editor.commit()
        Timber.d("User login session modified!")
    }

    var isFirstTimeLaunch: Boolean
        get() {
            return pref.getBoolean(SharedPrefs.IS_FIRST_TIME_LAUNCH, true)
        }
        set(isFirstTime) {
            editor.putBoolean(SharedPrefs.IS_FIRST_TIME_LAUNCH, isFirstTime)
            editor.commit()
        }

    companion object {

        private val PREF_NAME = "prefs"
        private val KEY_IS_LOGGEDIN = "isLoggedIn"
        private val KEY_COOKIES = "cookies"
        private val KEY_WIKI_EDU_DASHBOARD_COOKIES = "wiki_edu_dashboard_cookies"
        private val KEY_OUTREACH_DASHBOARD_COOKIES = "outreach_dashboard_cookies"
        private val KEY_USERNAME = "username"
        private const val IS_FIRST_TIME_LAUNCH = "IS_FIRST_TIME_LAUNCH"
    }
}