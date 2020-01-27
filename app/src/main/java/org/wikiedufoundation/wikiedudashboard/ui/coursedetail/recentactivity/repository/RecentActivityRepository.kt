package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.repository

import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivity

/**
 *Declares RecentActivityRepository interface
 * */
interface RecentActivityRepository {

    /**
     *Creates a suspend function [getAllRecentActivities]
     * */
    suspend fun getAllRecentActivities(): List<RecentActivity>

    /**
     *Creates a suspend function [insertRecentActivity]
     * */
    suspend fun insertRecentActivity(url: String)
}