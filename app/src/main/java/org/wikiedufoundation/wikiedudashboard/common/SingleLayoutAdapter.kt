package org.wikiedufoundation.wikiedudashboard.common

/**
 * [SingleLayoutAdapter] to receive the correct layout for the recycler item
 * @param layoutId item layout
 * [getLayoutIdForPosition] send it up above in the hierarchy
 */
abstract class SingleLayoutAdapter<T>(
    private val layoutId: Int
) : BaseAdapter<T>() {

    override fun getLayoutIdForPosition(position: Int): Int = layoutId
}
