package org.wikiedufoundation.wikiedudashboard.di

import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsContract
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsPresenterImpl

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


}