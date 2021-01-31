package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.repository

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.User

/**
*Declares StudentRepository interface
* */
interface StudentsRepository {

    /**
     *Creates a suspend function [requestStudentList]
     * */
    suspend fun requestStudentList(url: String): List<User>
}
