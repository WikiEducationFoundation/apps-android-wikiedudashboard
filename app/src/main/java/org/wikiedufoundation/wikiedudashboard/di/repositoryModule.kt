package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.campaign.repository.ActiveCampaignRepository
import org.wikiedufoundation.wikiedudashboard.ui.campaign.repository.ActiveCampaignRepositoryImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.repository.ArticlesEditedRepository
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.repository.ArticlesEditedRepositoryImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.repository.CourseDetailRepository
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.repository.CourseDetailRepositoryImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.repository.StudentsRepository
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.repository.StudentsRepositoryImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.repository.RecentActiivtyRepositoryImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.repository.RecentActivityRepository
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.repository.CourseUploadsRepository
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.repository.CourseUploadsRepositoryImpl
import org.wikiedufoundation.wikiedudashboard.ui.courselist.repository.CourseListRepository
import org.wikiedufoundation.wikiedudashboard.ui.courselist.repository.CourseListRepositoryImpl
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.repository.DashboardRepository
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.repository.DashboardRepositoryImpl
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.repository.MediaDetailsRepository
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.repository.MediaDetailsRepositoryImpl

/**
 * Use the [repositoryModule] to creating repository instance
 **/
val repositoryModule = module {
    single <ActiveCampaignRepository> { ActiveCampaignRepositoryImpl(get(), get()) }
    single <CourseListRepository> { CourseListRepositoryImpl(get(), get()) }
    single <DashboardRepository> { DashboardRepositoryImpl(get(), get()) }
    single <StudentsRepository> { StudentsRepositoryImpl(get()) }
    single <RecentActivityRepository> { RecentActiivtyRepositoryImpl(get()) }
    single <CourseUploadsRepository> { CourseUploadsRepositoryImpl(get()) }
    single <ArticlesEditedRepository> { ArticlesEditedRepositoryImpl(get()) }
    single <CourseDetailRepository> { CourseDetailRepositoryImpl(get()) }
    single <MediaDetailsRepository> { MediaDetailsRepositoryImpl(get()) }
}