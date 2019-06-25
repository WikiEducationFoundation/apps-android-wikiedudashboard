package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.coures_students.view

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.coures_students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster


interface StudentListView : Progressive, Toaster {
    fun setData(data: StudentListResponse)
}

