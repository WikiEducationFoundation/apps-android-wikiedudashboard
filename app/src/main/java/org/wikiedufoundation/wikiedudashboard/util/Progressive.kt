package org.wikiedufoundation.wikiedudashboard.util

/**
 *  Interface to containing [showProgressBar] function.
 * ***/
interface Progressive {
    /**
     * Implement [showProgressBar] to show pregress bar
     *
     * @param show boolean value that determine the visibility of a progress bar
     * ***/
    fun showProgressBar(show: Boolean)
}
