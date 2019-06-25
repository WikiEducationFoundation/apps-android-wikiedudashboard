package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data

class User(
    val admin: Boolean,
    val characterSumDraft: Long,
    val characterSumMs: Long,
    val characterSumUs: Long,
    val contentExpert: Boolean,
    val contributionUrl: String,
    val roleDescription: String,
    val sandboxUrl: String,
    val totalUploads: Long,
    var username: String,
    val courseTrainingProgress: String,
    val enrolledAt: String,
    val id: Long,
    val programManager: Boolean,
    val recentRevisions: Long,
    val role: Long
)
