package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData

/**
 * RecyclerView adapter for my dasboard list data
 * @param layoutId item layout id
 * @param onClickListener using a high order function to implement on clicked item through a lambda
 * ***/
class MyDashboardRecyclerAdapter internal constructor(
        layoutId: Int,
        private val onClickListener: (String) -> Unit
) : SingleLayoutAdapter<CourseListData>(layoutId) {

    /**
     * Use [onItemClicked] to handle the item's click
     * @param course item's object
     ***/
    fun onItemClicked(course: CourseListData) {
        onClickListener(course.slug)
    }

}