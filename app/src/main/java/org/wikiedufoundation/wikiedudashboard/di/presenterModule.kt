package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.presenter.CourseDetailPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.presenter.CourseDetailPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.provider.CourseDetailProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.CourseDetailView
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.presenter.CourseUploadsPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.presenter.CourseUploadsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider.CourseUploadsProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsView
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsContract
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileContract
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfilePresenterImpl

/**
 * Use the [presenterModule] to creating the mvp presenter for each view
 **/
val presenterModule = module {
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