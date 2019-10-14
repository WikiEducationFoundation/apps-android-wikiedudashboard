package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

/**
 * Interface that defines [requestStudentList] method
 * ***/
interface StudentListProvider {

    /**
     * Implementing [requestStudentList] to get list of student data
     * @param url api url
     * @param presenterCallback PresenterCallback variable
     * ***/
    fun requestStudentList(url: String, presenterCallback: PresenterCallback<*>)
}
