package org.wikiedufoundation.wikiedudashboard.ui.courselist.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData

/**
 * This creates an interface for
 * querying the database
 **/
@Dao
interface CourseListDao {

    /**
     * This get all the courses
     *
     **/
    @Query("SELECT * from course_list")
    fun getAllCourses(): LiveData<List<CourseListData>>

    /**
     * This insert the data into the database
     *
     **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(courseList: List<CourseListData>)
}
