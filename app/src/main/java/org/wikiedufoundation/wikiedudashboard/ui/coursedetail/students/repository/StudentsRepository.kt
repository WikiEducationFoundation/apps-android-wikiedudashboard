package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.repository

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.User

interface StudentsRepository {

    suspend fun requestStudentList(url: String) : List<User>
}