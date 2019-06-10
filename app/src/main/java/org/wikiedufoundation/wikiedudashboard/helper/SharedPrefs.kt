package org.wikiedufoundation.wikiedudashboard.helper

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class SharedPrefs(private val _context: Context?) {

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
            Log.d(TAG, "WikiEdu Dashboard Cookies modified!")
        }

    var cookies: String?
        get() = pref.getString(KEY_COOKIES, null)
        set(cookies) {
            editor.putString(KEY_COOKIES, cookies)
            editor.commit()
            Log.d(TAG, "Cookies modified!")
        }

    var outreachDashboardCookies: String?
        get() = pref.getString(KEY_OUTREACH_DASHBOARD_COOKIES, null)
        set(cookies) {
            editor.putString(KEY_OUTREACH_DASHBOARD_COOKIES, cookies)
            editor.commit()
            Log.d(TAG, "Outreach Dashboard Cookies modified!")
        }

    var userName: String?
        get() = pref.getString(KEY_USERNAME, null)
        set(username) {
            editor.putString(KEY_USERNAME, username)
            editor.commit()
            Log.d(TAG, "Username modified!")
        }

    init {
        pref = _context!!.getSharedPreferences(PREF_NAME, PRIVATE_MODE)
        editor = pref.edit()
    }


    fun setLogin(isLoggedIn: Boolean) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn)
        editor.commit()
        Log.d(TAG, "User login session modified!")
    }

    companion object {
        private val TAG = "Shared Preference"

        private val PREF_NAME = "prefs"
        private val KEY_IS_LOGGEDIN = "isLoggedIn"
        private val KEY_COOKIES = "cookies"
        private val KEY_WIKI_EDU_DASHBOARD_COOKIES = "wiki_edu_dashboard_cookies"
        private val KEY_OUTREACH_DASHBOARD_COOKIES = "outreach_dashboard_cookies"
        private val KEY_USERNAME = "username"
    }

}