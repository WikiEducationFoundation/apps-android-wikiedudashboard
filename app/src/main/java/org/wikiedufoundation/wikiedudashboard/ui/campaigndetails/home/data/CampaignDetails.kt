package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.data

import java.io.Serializable

data class CampaignDetails(
    val id: Int,
    val title: String,
    val slug: String,
    val description: String,
    val course_count: String,
    val created_count: String,
    val edited_count: String,
    val word_count: String,
    val reference_count: String,
    val views_count: String,
    val student_count: String,
    val uplaod_count: String,
    val creation_date: String,
    val template_description: String,
    val default_course_type: String,
    val default_passcode: String
) : Serializable