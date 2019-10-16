package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetail
import org.wikiedufoundation.wikiedudashboard.util.Progressive
import org.wikiedufoundation.wikiedudashboard.util.Toaster

/**
 * Course detail view interface defining [setData]
 * ***/
interface CourseDetailView : Progressive, Toaster {
    /**
     * Set course detail view data
     * @param data course detail data
     * ***/
    fun setData(data: CourseDetail)
}
