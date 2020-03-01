package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.data

import java.io.Serializable

data class CampaignDetails(
    val id: Int,
    val title: String,
    val slug: String,
    val description: String,
    val template_description: String,
    val default_course_type: String,
    val default_passcode: String,
    val courses_count: Int,
    val user_count: Int,
    val new_article_count_human: String,
    val word_count_human: String,
    val references_count_human: String,
    val view_sum_human: String,
    val article_count_human: String,
    val upload_count_human: String,
    val uploads_in_use_count_human: String,
    val uploads_in_use_count: Int,
    val upload_usage_count_human: String,
    val upload_usage_count: Int,
    val trained_percent_human: String,
    val course_string_prefix: String,
    val show_the_create_course_button: Boolean,
    val editable: String,
    val register_accounts: Boolean,
    val start: String,
    val end: String,
    val created_at: String

) : Serializable