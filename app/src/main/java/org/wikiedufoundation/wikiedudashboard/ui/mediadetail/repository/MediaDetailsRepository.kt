package org.wikiedufoundation.wikiedudashboard.ui.mediadetail.repository

import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.Query

/**
 *Declares CourseUploadsRepository interface
 * */
interface MediaDetailsRepository {

    /**
     *Creates a suspend function [requestMediaDetails]
     * */
    suspend fun requestMediaDetails(cookies : String): Query
}