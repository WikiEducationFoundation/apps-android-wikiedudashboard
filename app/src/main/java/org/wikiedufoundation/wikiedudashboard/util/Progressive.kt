package org.wikiedufoundation.wikiedudashboard.util

/**
 * [showProgressBar] callback
 * ***/
interface Progressive {
    /**
     * Implement [showProgressBar] to show pregress bar
     *
     * @param show boolean value that determine the visibility of a progress bar
     * ***/
    fun showProgressBar(show: Boolean)
}
