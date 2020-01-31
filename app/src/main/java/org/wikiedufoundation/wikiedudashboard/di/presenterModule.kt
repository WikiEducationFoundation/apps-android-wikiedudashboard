package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
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
}