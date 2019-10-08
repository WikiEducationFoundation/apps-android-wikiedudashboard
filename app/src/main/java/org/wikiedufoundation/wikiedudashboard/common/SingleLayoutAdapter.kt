package org.wikiedufoundation.wikiedudashboard.common

abstract class SingleLayoutAdapter(
        private val layoutId: Int
) : BaseAdapter() {

    override fun getLayoutIdForPosition(position: Int): Int = layoutId

}