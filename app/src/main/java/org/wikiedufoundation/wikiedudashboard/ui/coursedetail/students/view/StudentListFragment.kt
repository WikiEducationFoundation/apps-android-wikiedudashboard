package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_explore_students.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.StudentListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.StudentListResponse
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.presenter.StudentListPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.provider.RetrofitStudentListProvider
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileActivity
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A [Fragment] that displays list of students
 * ***/
class StudentListFragment : Fragment(), StudentListView {

    private val retrofitStudentListProvider: RetrofitStudentListProvider by inject()
    private val studentListPresenter: StudentListPresenter by inject {
        parametersOf(this, retrofitStudentListProvider)
    }

    private lateinit var url: String
    private lateinit var studentListRecyclerAdapter: StudentListRecyclerAdapter

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_explore_students, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        url = arguments?.getString("url", null).toString()

        studentListRecyclerAdapter = StudentListRecyclerAdapter(R.layout.item_rv_students) { openStudentProfile(it) }

        recyclerStudentList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = studentListRecyclerAdapter
        }

        url.let { studentListPresenter.requestStudentList(it) }
    }

    override fun showProgressBar(show: Boolean) {
        progressBar?.visibility = if (show) {
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
            recyclerStudentList?.visibility = View.GONE
            textViewNoStudents?.visibility = View.VISIBLE
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