package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.view

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

/**
 * Interface [StudentListView] that defines [setData]
 * ***/
interface StudentListView : Progressive, Toaster {

    /**
     * Implement [setData] in [StudentListFragment] to set list of student data from API response
     * @param data list of student data from API response
     * ***/
    fun setData(data: StudentListResponse)
}
