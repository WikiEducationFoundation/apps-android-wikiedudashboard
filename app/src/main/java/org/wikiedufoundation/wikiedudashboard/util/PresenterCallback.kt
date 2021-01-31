package org.wikiedufoundation.wikiedudashboard.util

/**
 * Retrofit2 HTTP request result callbacks
 * @param T
 */
interface PresenterCallback<T> {

    /**
     * @param response
     */
    fun onSuccess(response: T)

    /**
     *
     */
    fun onFailure()
}
