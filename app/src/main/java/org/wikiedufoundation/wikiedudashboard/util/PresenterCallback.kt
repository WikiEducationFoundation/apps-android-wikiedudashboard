package org.wikiedufoundation.wikiedudashboard.util

/**
 * Retrofit2 HTTP request result callbacks
 * @param T
 */
interface PresenterCallback<T> {

    /**
     * @param t
     */
    fun onSuccess(t: T)
    
    fun onFailure()
}