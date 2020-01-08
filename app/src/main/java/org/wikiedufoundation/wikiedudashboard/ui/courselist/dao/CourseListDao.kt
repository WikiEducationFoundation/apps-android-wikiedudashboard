package org.wikiedufoundation.wikiedudashboard.ui.campaign.dao

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
     * This get all the campaigns
     *
     **/
    @Query("SELECT * from course_list ORDER BY id ASC")
    fun getAllCourses(): LiveData<List<CourseListData>>

    /**
     * This insert the data into the database
     *
     **/
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCourse(courseList: List<CourseListData>)

}