package org.wikiedufoundation.wikiedudashboard.ui.adapters

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_rv_students.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.coures_students.data.User

import java.util.ArrayList

class StudentListRecyclerAdapter(private val context: Context) : RecyclerView.Adapter<StudentListRecyclerAdapter.MyAdapter>() {
    private var studentList: List<User> = ArrayList()


    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyAdapter {
        val view = LayoutInflater.from(context).inflate(R.layout.item_rv_students, viewGroup, false)

        return MyAdapter(view)
    }

    override fun onBindViewHolder(myAdapter: StudentListRecyclerAdapter.MyAdapter, i: Int) {
        myAdapter.textView.text = studentList[i].username
    }

    fun setData(studentList: List<User>) {
        this.studentList = studentList
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    inner class MyAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView : TextView = itemView.tv_students_name
    }
}