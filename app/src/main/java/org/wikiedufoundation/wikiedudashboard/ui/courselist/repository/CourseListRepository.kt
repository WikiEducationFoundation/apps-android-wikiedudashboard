package org.wikiedufoundation.wikiedudashboard.ui.courselist.repository

import androidx.lifecycle.LiveData
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData

/**
 * Declares the CourseListRepository interface
 * */
interface CourseListRepository {

    /**
     * This function gets all the courses
     **/
    fun allCourseList(): LiveData<List<CourseListData>>

    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    suspend fun requestCourseList(cookies: String)
}
