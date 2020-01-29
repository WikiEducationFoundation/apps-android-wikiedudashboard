package org.wikiedufoundation.wikiedudashboard.util

object Urls {
    const val WIKIEDU_DASHBOARD_BASE_URL = "https://dashboard.wikiedu.org/"
    const val OUTREACH_DASHBOARD_BASE_URL = "https://outreachdashboard.wmflabs.org/"
    const val WIKIMEDIA_COMMONS_BASE_URL = "https://commons.wikimedia.org/"

    var BASE_URL = "https://outreachdashboard.wmflabs.org/"

    val SUB_URL_COURSE_DETAIL = "courses/"
    val SUB_URL_COURSE_ARTICLE = "courses/%s/articles.json"
    val SUB_URL_COURSE_USERS = "welcome/"
    val SUB_URL_COURSE_STUDENTLIST = "courses/%s/users.json"
    val SUB_URL_COURSE_RECENT = "courses/%s/revisions.json"
}
