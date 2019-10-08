package org.wikiedufoundation.wikiedudashboard.ui.adapters

import org.wikiedufoundation.wikiedudashboard.common.SingleLayoutAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.User
import java.util.*

/**
 * RecyclerView adapter for recent activities
 * @property studentListFragment primary constructor property
 ***/
class StudentListRecyclerAdapter(
        layoutId: Int,
        private val onClickListener: (String) -> Unit
) : SingleLayoutAdapter(layoutId) {

    private var studentList: List<User> = ArrayList()

    /**
     * Set [User] type list of edited activities list
     * @param studentList a list of students
     * ***/
    fun setData(studentList: List<User>) {
        this.studentList = studentList
    }

    override fun getObjForPosition(position: Int): Any = studentList[position]

    override fun getItemCount(): Int = studentList.size

    fun onStudentClicked(user: User) {
        onClickListener(user.userName)
    }

}