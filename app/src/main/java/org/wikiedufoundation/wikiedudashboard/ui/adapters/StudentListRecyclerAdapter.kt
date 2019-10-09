package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.User

/**
 * RecyclerView adapter for recent activity list data
 * @param layoutId item layout id
 * @param onClickListener using a high order function to implement o item click through a lambda
 * ***/
class StudentListRecyclerAdapter(
        layoutId: Int,
        private val onClickListener: (String) -> Unit
) : SingleLayoutAdapter<User>(layoutId) {

    fun onStudentClicked(user: User) {
        onClickListener(user.userName)
    }

}