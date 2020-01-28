package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.repository

import androidx.lifecycle.LiveData
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivity

/**
 *Declares RecentActivityRepository interface
 * */
interface RecentActivityRepository {

    /**
     *Creates a suspend function [insertRecentActivity]
     * */
    suspend fun insertRecentActivity(url: String) : List<RecentActivity>
}