package org.wikiedufoundation.wikiedudashboard.ui.courselist.presenter

interface CourseListPresenter {

    /**
     * Use [requestDashboard] to handle cookies and display result in Timber logger
     * @param cookies cookies in String
     * ***/
    fun requestDashboard(cookies: String)
}
