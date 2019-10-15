package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.StudentListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.presenter.StudentListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider.RetrofitStudentListProvider
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileActivity
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A [Fragment] that displays list of students
 * ***/
class StudentListFragment : Fragment(), StudentListView {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvNoStudents: TextView

    private lateinit var studentListPresenter: StudentListPresenterImpl

    private lateinit var url: String
    private lateinit var studentListRecyclerAdapter: StudentListRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_explore_students, container, false)
        url = arguments?.getString("url", null).toString()
        recyclerView = view.findViewById(R.id.rv_students_list)
        progressBar = view.findViewById(R.id.progressBar)
        tvNoStudents = view.findViewById(R.id.tv_no_students)

        studentListPresenter = StudentListPresenterImpl(this, RetrofitStudentListProvider())

        studentListRecyclerAdapter = StudentListRecyclerAdapter(R.layout.item_rv_students) { openStudentProfile(it) }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = studentListRecyclerAdapter
        }

        url.let { studentListPresenter.requestStudentList(it) }

        return view
    }

    override fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showMessage(message: String) {
        context?.showToast(message)
    }

    override fun setData(data: StudentListResponse) {
        if (data.course.users.isNotEmpty()) {
            Timber.d(data.toString())
            studentListRecyclerAdapter.setData(data.course.users)
            studentListRecyclerAdapter.notifyDataSetChanged()
        } else {
            recyclerView.visibility = View.GONE
            tvNoStudents.visibility = View.VISIBLE
        }
    }

    /**
     * Use [openStudentProfile] to send [username] to [ProfileActivity]
     *
     * @param username user's username in String
     * ***/
    fun openStudentProfile(username: String) {
        val i = Intent(context, ProfileActivity::class.java)
        i.putExtra("username", username)
        startActivity(i)
    }
}