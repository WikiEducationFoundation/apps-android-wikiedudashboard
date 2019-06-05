package org.wikiedufoundation.wikiedudashboard.dashboard.data

class CourseListData(
    val id: Int,
    val title: String,
    val created_at: String,
    val updated_at: String,
    val start: String,
    val end: String,
    val school: String,
    val term: String,
    val character_sum: String,
    val view_sum: String,
    val user_count: String,
    val article_count: String,
    val revision_count: String,
    val slug: String,
    val subject: String,
    val expected_students: Int,
    val description: String,
    val isSubmitted: Boolean,
    val passcode: String,
    val timeline_start: String,
    val timeline_end: String,
    val day_exceptions: String,
    val weekdays: String,
    val new_article_count: Int,
    val isNo_day_exceptions: Boolean,
    val trained_count: Int

) {
    override fun toString(): String {
        return "CourseListData(id=$id, title='$title', created_at='$created_at', updated_at='$updated_at', start='$start', end='$end', school='$school', term='$term', character_sum='$character_sum', view_sum='$view_sum', user_count='$user_count', article_count='$article_count', revision_count='$revision_count', slug='$slug', subject='$subject', expected_students=$expected_students, description='$description', isSubmitted=$isSubmitted, passcode='$passcode', timeline_start='$timeline_start', timeline_end='$timeline_end', day_exceptions='$day_exceptions', weekdays='$weekdays', new_article_count=$new_article_count, isNo_day_exceptions=$isNo_day_exceptions, trained_count=$trained_count)"
    }
}
