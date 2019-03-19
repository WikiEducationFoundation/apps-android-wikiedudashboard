package org.wikiedufoundation.wikiedudashboard.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPrefs {
    private static String TAG = "Shared Preference";

    private static final String PREF_NAME = "prefs";
    private static final String KEY_IS_LOGGEDIN = "isLoggedIn";
    private static final boolean KEY_IS_LOGGEDIN_WIKI_EDU_DASHBOARD = true;
    private static final String KEY_COOKIES = "cookies";
    private static final String KEY_WIKI_EDU_DASHBOARD_COOKIES = "wiki_edu_dashboard_cookies";
    private static final String KEY_OUTREACH_DASHBOARD_COOKIES = "outreach_dashboard_cookies";
    private static final String KEY_USERNAME = "username";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context _context;

    private int PRIVATE_MODE = 0;

    public SharedPrefs(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setLogin(boolean isLoggedIn) {
        editor.putBoolean(KEY_IS_LOGGEDIN, isLoggedIn);
        editor.commit();
        Log.d(TAG, "User login session modified!");
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGEDIN, false);
    }

    public void setWikiEduDashboardCookies(String cookies) {
        editor.putString(KEY_WIKI_EDU_DASHBOARD_COOKIES, cookies);
        editor.commit();
        Log.d(TAG, "WikiEdu Dashboard Cookies modified!");
    }

    public String getWikiEduDashboardCookies() {
        return pref.getString(KEY_WIKI_EDU_DASHBOARD_COOKIES, null);
    }

    public void setCookies(String cookies) {
        editor.putString(KEY_COOKIES, cookies);
        editor.commit();
        Log.d(TAG, "Cookies modified!");
    }

    public String getCookies() {
        return pref.getString(KEY_COOKIES, null);
    }

    public void setOutreachDashboardCookies(String cookies) {
        editor.putString(KEY_OUTREACH_DASHBOARD_COOKIES, cookies);
        editor.commit();
        Log.d(TAG, "Outreach Dashboard Cookies modified!");
    }

    public String getOutreachDashboardCookies() {
        return pref.getString(KEY_OUTREACH_DASHBOARD_COOKIES, null);
    }

    public void setUserName(String username) {
        editor.putString(KEY_USERNAME, username);
        editor.commit();
        Log.d(TAG, "Username modified!");
    }

    public String getUserName() {
        return pref.getString(KEY_USERNAME, null);
    }

}