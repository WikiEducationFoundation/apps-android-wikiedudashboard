package org.wikiedufoundation.wikiedudashboard.util

/**
 * Retrofit2 HTTP request result callbacks
 */
interface PresenterCallback<T> {
    fun onSuccess(t: T)
    fun onFailure()
}