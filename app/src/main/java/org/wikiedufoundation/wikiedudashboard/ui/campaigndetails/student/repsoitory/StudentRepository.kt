package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.repsoitory

import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.data.Students

/**
 *Declares StudentRepository interface
 * */
interface StudentRepository {

    /**
     *Creates a suspend function [requestStudent]
     * */
    suspend fun requestStudent(url: String): List<Students>
}
