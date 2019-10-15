package org.wikiedufoundation.wikiedudashboard.util

/**
 * [showMessage] callback
 * ***/
interface Toaster {
    /**
     * Implement [showMessage] to pass a text message
     *
     * @param message text message in String
     * ***/
    fun showMessage(message: String)
}
