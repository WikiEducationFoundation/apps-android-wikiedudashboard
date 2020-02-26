package org.wikiedufoundation.wikiedudashboard.util

object Urls {
    const val WIKIEDU_DASHBOARD_BASE_URL = "https://dashboard.wikiedu.org/"
    const val OUTREACH_DASHBOARD_BASE_URL = "https://outreachdashboard.wmflabs.org/"
    const val WIKIMEDIA_COMMONS_BASE_URL = "https://commons.wikimedia.org/"

    var BASE_URL = "https://outreachdashboard.wmflabs.org/"

    val SUB_URL_COURSE_DETAIL = "courses/%s/course.json"
    val SUB_URL_COURSE_ARTICLE = "courses/%s/articles.json"
    val SUB_URL_COURSE_STUDENTLIST = "courses/%s/users.json"
    val SUB_URL_COURSE_RECENT = "courses/%s/revisions.json"
    val SUB_URL_COURSE_UPLOADS = "courses/%s/uploads.json"
    val PROFILE_DETAIL = BASE_URL + "users/%s/?format=json"
    val SUB_URL_CAMPAIGN_DETAIL = "campaigns/%s.json"
    val SUB_URL_CAMPAIGN_STUDENT = "campaigns/%s/users.json"

//    Urls.BASE_URL + "users/" + username + "?format=json"
}
