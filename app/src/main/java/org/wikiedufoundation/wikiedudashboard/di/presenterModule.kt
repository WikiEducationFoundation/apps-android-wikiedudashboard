package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.campaign.CampaignListContract
import org.wikiedufoundation.wikiedudashboard.ui.campaign.CampaignListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.presenter.ArticlesEditedPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.presenter.ArticlesEditedPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider.ArticlesEditedProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view.ArticlesEditedView
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.presenter.CourseDetailPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.presenter.CourseDetailPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.provider.CourseDetailProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.CourseDetailView
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RecentActivityContract
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.RecentActivityPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.presenter.StudentListPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.presenter.StudentListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider.StudentListProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.view.StudentListView
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.presenter.CourseUploadsPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.presenter.CourseUploadsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider.CourseUploadsProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsView
import org.wikiedufoundation.wikiedudashboard.ui.courselist.presenter.CourseListPresenter
import org.wikiedufoundation.wikiedudashboard.ui.courselist.presenter.CourseListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.courselist.provider.CourseListProvider
import org.wikiedufoundation.wikiedudashboard.ui.courselist.view.CourseListView
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.MyDashboardContract
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.MyDashboardPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsContract
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileContract
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfilePresenterImpl

val presenterModule = module {

    factory<CampaignListContract.Presenter> { (view: CampaignListContract.View, provider: CampaignListContract.Provider) ->
        CampaignListPresenterImpl(view, provider)
    }

    factory<CourseListPresenter> { (view: CourseListView, provider: CourseListProvider) ->
        CourseListPresenterImpl(view, provider)
    }

    factory<MyDashboardContract.Presenter> { (view: MyDashboardContract.View, provider: MyDashboardContract.Provider) ->
        MyDashboardPresenterImpl(view, provider)
    }

    factory<MediaDetailsContract.Presenter> { (view: MediaDetailsContract.View, provider: MediaDetailsContract.Provider) ->
        MediaDetailsPresenterImpl(view, provider)
    }

    factory<ProfileContract.Presenter> { (view: ProfileContract.View, provider: ProfileContract.Provider) ->
        ProfilePresenterImpl(view, provider)
    }

    factory<ArticlesEditedPresenter> { (view: ArticlesEditedView, provider: ArticlesEditedProvider) ->
        ArticlesEditedPresenterImpl(view, provider)
    }

    factory<CourseDetailPresenter> { (view: CourseDetailView, provider: CourseDetailProvider) ->
        CourseDetailPresenterImpl(view, provider)
    }

    factory<RecentActivityContract.Presenter> { (view: RecentActivityContract.View, provider: RecentActivityContract.Provider) ->
        RecentActivityPresenterImpl(view, provider)
    }

    factory<StudentListPresenter> { (view: StudentListView, provider: StudentListProvider) ->
        StudentListPresenterImpl(view, provider)
    }

    factory<CourseUploadsPresenter> { (view: CourseUploadsView, provider: CourseUploadsProvider) ->
        CourseUploadsPresenterImpl(view, provider)
    }

}