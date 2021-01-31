package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.data.Students

/**
 * RecyclerView adapter for recent activity list data
 * @param layoutId item layout id
 * @param onClickListener using a high order function to implement o item click through a lambda
 * ***/
class StudentRecyclerAdapter(
    layoutId: Int,
    private val onClickListener: (String, String, String, String) -> Unit
) : SingleLayoutAdapter<Students>(layoutId) {

    /**
     * Use [onStudentClicked] to handle the item's click
     * @param user item's object
     ***/
    fun onStudentClicked(student: Students) {
        onClickListener(student.username, student.course, student.editor_count, student.role)
    }
}
