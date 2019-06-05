package org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.provider

import org.wikiedufoundation.wikiedudashboard.helper.PresenterCallback

interface StudentListProvider {
    fun requestStudentList(url: String, presenterCallback: PresenterCallback<*>)
}
