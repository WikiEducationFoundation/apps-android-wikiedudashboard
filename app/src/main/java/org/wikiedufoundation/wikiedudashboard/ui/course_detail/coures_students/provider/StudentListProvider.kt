package org.wikiedufoundation.wikiedudashboard.ui.course_detail.coures_students.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

interface StudentListProvider {
    fun requestStudentList(url: String, presenterCallback: PresenterCallback<*>)
}
