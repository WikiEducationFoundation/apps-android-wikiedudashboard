package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivity


/**
 * This creates an interface for
 * querying the database
 **/
@Dao
interface RecentActivityDao {

    /**
     * Get all recent activities
     *
     **/
    @Query("SELECT * from recent_activity")
    suspend fun getAllRecentActiivty(): List<RecentActivity>

    /**
     * Insert recent activities into the database
     *
     **/
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecentActivity(recent: List<RecentActivity>)
}