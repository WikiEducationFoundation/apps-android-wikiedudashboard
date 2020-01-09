package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.presenter

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider.StudentListProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.view.StudentListView

import org.wikiedufoundation.wikiedudashboard.util.PresenterCallback

/**
 * Retrofit http request to get student list data and display it in view
 * ***/
class StudentListPresenterImpl(
        private val studentListView: StudentListView,
        private val studentListProvider: StudentListProvider
) : StudentListPresenter {

    override fun requestStudentList(url: String) {
        studentListView.showProgressBar(true)
        studentListProvider.requestStudentList(url, object : PresenterCallback<StudentListResponse> {
            override fun onSuccess(studentListResponse: StudentListResponse) {
                studentListView.showProgressBar(false)
                studentListView.setData(studentListResponse)
            }

            override fun onFailure() {
                studentListView.showProgressBar(false)
                studentListView.showMessage("Unable to connect to server.")
            }
        })
    }
}
