package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

interface StudentListProvider {
    fun requestStudentList(url: String, presenterCallback: PresenterCallback<*>)
}
