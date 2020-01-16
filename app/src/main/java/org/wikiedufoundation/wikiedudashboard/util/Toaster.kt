package org.wikiedufoundation.wikiedudashboard.util

/**
 * Interface that contains [showMessage] function.
 * ***/
interface Toaster {
    /**
     * Implement [showMessage] to pass a text message
     *
     * @param message text message in String
     * ***/
    fun showMessage(message: String)
}
