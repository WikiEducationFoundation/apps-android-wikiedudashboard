package org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.adapters.StudentListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.presenter.StudentListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.course_detail.coures_students.provider.RetrofitStudentListProvider
import org.wikiedufoundation.wikiedudashboard.helper.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.helper.ViewUtils


class StudentListFragment : Fragment(), StudentListView {

    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var tvNoStudents: TextView? = null

    private var layoutManager: RecyclerView.LayoutManager?=null
    private var studentListPresenter: StudentListPresenterImpl?=null

    private var url: String? = null
    private var studentListRecyclerAdapter: StudentListRecyclerAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_explore_students, container, false)
        url = arguments!!.getString("url", null)
        recyclerView = view.findViewById(R.id.rv_students_list)
        progressBar = view.findViewById(R.id.progressBar)
        tvNoStudents = view.findViewById(R.id.tv_no_students)


        val context : Context? = getContext()
        studentListPresenter = StudentListPresenterImpl(this, RetrofitStudentListProvider())
        layoutManager = LinearLayoutManager(context)
        val sharedPrefs : SharedPrefs? = SharedPrefs(context)
        tvNoStudents!!.text = sharedPrefs!!.cookies
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.setHasFixedSize(true)
        studentListRecyclerAdapter = StudentListRecyclerAdapter(context!!)
        recyclerView!!.adapter = studentListRecyclerAdapter
        studentListPresenter!!.requestStudentList(url!!)

        return view
    }

    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.GONE
        }
    }

    override fun showMessage(message: String) {
        ViewUtils.showToast(context!!, message)
    }

    override fun setData(data: StudentListResponse) {
        if (data.course.users.isNotEmpty()) {
            Log.d("StudentListFragment: ", data.toString())
            studentListRecyclerAdapter!!.setData(data.course.users)
            studentListRecyclerAdapter!!.notifyDataSetChanged()
        } else {
            recyclerView!!.visibility = View.GONE
            tvNoStudents!!.visibility = View.VISIBLE
        }

    }
}