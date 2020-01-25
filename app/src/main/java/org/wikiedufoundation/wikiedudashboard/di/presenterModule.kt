package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
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
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.MyDashboardContract
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.MyDashboardPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsContract
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileContract
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfilePresenterImpl

/**
 * Use the [presenterModule] to creating the mvp presenter for each view
 **/
val presenterModule = module {

    /**
     * Factory for [MyDashboardContract.Presenter] injecting the [MyDashboardContract.View] and [MyDashboardContract.Provider]
     **/

    /**
     * Factory for [MyDashboardContract.Presenter] injecting the [MyDashboardContract.View] and [MyDashboardContract.Provider]
     **/
    /**
     * Factory for [MyDashboardContract.Presenter] injecting the [MyDashboardContract.View] and [MyDashboardContract.Provider]
     **/
    /**
     * Factory for [MyDashboardContract.Presenter] injecting the [MyDashboardContract.View] and [MyDashboardContract.Provider]
     **/
    factory<MyDashboardContract.Presenter> { (view: MyDashboardContract.View, provider: MyDashboardContract.Provider) ->
        MyDashboardPresenterImpl(view, provider)
    }

    /**
     * Factory for [MediaDetailsContract.Presenter] injecting the [MediaDetailsContract.View] and [MediaDetailsContract.Provider]
     **/

    /**
     * Factory for [MediaDetailsContract.Presenter] injecting the [MediaDetailsContract.View] and [MediaDetailsContract.Provider]
     **/

    /**
     * Factory for [MediaDetailsContract.Presenter] injecting the [MediaDetailsContract.View] and [MediaDetailsContract.Provider]
     **/

    /**
     * Factory for [MediaDetailsContract.Presenter] injecting the [MediaDetailsContract.View] and [MediaDetailsContract.Provider]
     **/
    factory<MediaDetailsContract.Presenter> { (view: MediaDetailsContract.View, provider: MediaDetailsContract.Provider) ->
        MediaDetailsPresenterImpl(view, provider)
    }

    /**
     * Factory for [ProfileContract.Presenter] injecting the [ProfileContract.View] and [ProfileContract.Provider]
     **/

    /**
     * Factory for [ProfileContract.Presenter] injecting the [ProfileContract.View] and [ProfileContract.Provider]
     **/

    /**
     * Factory for [ProfileContract.Presenter] injecting the [ProfileContract.View] and [ProfileContract.Provider]
     **/

    /**
     * Factory for [ProfileContract.Presenter] injecting the [ProfileContract.View] and [ProfileContract.Provider]
     **/
    factory<ProfileContract.Presenter> { (view: ProfileContract.View, provider: ProfileContract.Provider) ->
        ProfilePresenterImpl(view, provider)
    }

    /**
     * Factory for [ArticlesEditedPresenter] injecting the [ArticlesEditedView] and [ArticlesEditedProvider]
     **/

    /**
     * Factory for [ArticlesEditedPresenter] injecting the [ArticlesEditedView] and [ArticlesEditedProvider]
     **/

    /**
     * Factory for [ArticlesEditedPresenter] injecting the [ArticlesEditedView] and [ArticlesEditedProvider]
     **/

    /**
     * Factory for [ArticlesEditedPresenter] injecting the [ArticlesEditedView] and [ArticlesEditedProvider]
     **/
    factory<ArticlesEditedPresenter> { (view: ArticlesEditedView, provider: ArticlesEditedProvider) ->
        ArticlesEditedPresenterImpl(view, provider)
    }

    /**
     * Factory for [CourseDetailPresenter] injecting the [CourseDetailView] and [CourseDetailProvider]
     **/

    /**
     * Factory for [CourseDetailPresenter] injecting the [CourseDetailView] and [CourseDetailProvider]
     **/

    /**
     * Factory for [CourseDetailPresenter] injecting the [CourseDetailView] and [CourseDetailProvider]
     **/

    /**
     * Factory for [CourseDetailPresenter] injecting the [CourseDetailView] and [CourseDetailProvider]
     **/
    factory<CourseDetailPresenter> { (view: CourseDetailView, provider: CourseDetailProvider) ->
        CourseDetailPresenterImpl(view, provider)
    }

    /**
     * Factory for [RecentActivityContract.Presenter] injecting the [RecentActivityContract.View] and [RecentActivityContract.Provider]
     **/

    /**
     * Factory for [RecentActivityContract.Presenter] injecting the [RecentActivityContract.View] and [RecentActivityContract.Provider]
     **/

    /**
     * Factory for [RecentActivityContract.Presenter] injecting the [RecentActivityContract.View] and [RecentActivityContract.Provider]
     **/

    /**
     * Factory for [RecentActivityContract.Presenter] injecting the [RecentActivityContract.View] and [RecentActivityContract.Provider]
     **/
    factory<RecentActivityContract.Presenter> { (view: RecentActivityContract.View, provider: RecentActivityContract.Provider) ->
        RecentActivityPresenterImpl(view, provider)
    }

    /**
     * Factory for [StudentListPresenter] injecting the [StudentListView] and [StudentListProvider]
     **/

    /**
     * Factory for [StudentListPresenter] injecting the [StudentListView] and [StudentListProvider]
     **/

    /**
     * Factory for [StudentListPresenter] injecting the [StudentListView] and [StudentListProvider]
     **/

    /**
     * Factory for [StudentListPresenter] injecting the [StudentListView] and [StudentListProvider]
     **/
    factory<StudentListPresenter> { (view: StudentListView, provider: StudentListProvider) ->
        StudentListPresenterImpl(view, provider)
    }

    /**
     * Factory for [CourseUploadsPresenter] injecting the [CourseUploadsView] and [CourseUploadsProvider]
     **/

    /**
     * Factory for [CourseUploadsPresenter] injecting the [CourseUploadsView] and [CourseUploadsProvider]
     **/

    /**
     * Factory for [CourseUploadsPresenter] injecting the [CourseUploadsView] and [CourseUploadsProvider]
     **/

    /**
     * Factory for [CourseUploadsPresenter] injecting the [CourseUploadsView] and [CourseUploadsProvider]
     **/
    factory<CourseUploadsPresenter> { (view: CourseUploadsView, provider: CourseUploadsProvider) ->
        CourseUploadsPresenterImpl(view, provider)
    }
}