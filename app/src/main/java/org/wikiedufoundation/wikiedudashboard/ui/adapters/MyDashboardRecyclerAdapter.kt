package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.CourseListData

/**
 * RecyclerView adapter for my dasboard list data
 * @param layoutId item layout id
 * @param onClickListener using a high order function to implement o item click through a lambda
 * ***/
class MyDashboardRecyclerAdapter internal constructor(
        layoutId: Int,
        private val onClickListener: (String) -> Unit
) : SingleLayoutAdapter<CourseListData>(layoutId) {

    fun onItemClicked(course: CourseListData) {
        onClickListener(course.slug)
    }

}