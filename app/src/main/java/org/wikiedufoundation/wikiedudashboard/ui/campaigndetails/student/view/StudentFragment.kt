package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_explore_students.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.ui.adapters.StudentRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.viewmodel.StudentViewModel
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileActivity
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

class StudentFragment : Fragment() {

    private lateinit var url: String

    private lateinit var studentListRecyclerAdapter: StudentRecyclerAdapter
    private val studentViewModel by viewModel<StudentViewModel> { parametersOf(url) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(org.wikiedufoundation.wikiedudashboard.R.layout.student_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        url = arguments?.getString("url", null).toString()

        studentListRecyclerAdapter = StudentRecyclerAdapter(org.wikiedufoundation.wikiedudashboard.R.layout.item_rv_student) { s: String?, s1: String?, s2: String?, s3: String? -> openStudentProfile(s, s1, s2, s3) }

        initializeRecyclerView()
        setData()
        initializeProgressBar()
        initializeToaster()
    }

    private fun initializeRecyclerView() {
        recyclerStudentList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = studentListRecyclerAdapter
        }
    }

    private fun initializeProgressBar() {
        studentViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun initializeToaster() {
        studentViewModel.showMsg.observe(this, androidx.lifecycle.Observer {
            val message = it.showMsg
            context?.showToast(message)
        })
    }

    private fun setData() {
        studentViewModel.studentList.observe(this, Observer {
            if (it.isNotEmpty()) {
                Timber.d(it.toString())
                studentListRecyclerAdapter.setData(it)
            } else {
                recyclerStudentList?.visibility = View.GONE
                textViewNoStudents?.visibility = View.VISIBLE
            }
        })
    }

    /**
     * Use [openStudentProfile] to send [username] to [ProfileActivity]
     *
     * @param username user's username in String, course in String, editor_count in String and role in String
     * ***/
    fun openStudentProfile(username: String?, course: String?, editor_count: String?, role: String?) {

        val studentDetailsFragment = StudentDetailsFragment()
        val bundle = Bundle()
        bundle.putString("username", username)
        bundle.putString("edit_count", editor_count)
        bundle.putString("role", role)
        bundle.putString("course", course)
        studentDetailsFragment.arguments = bundle
        studentDetailsFragment.show(parentFragmentManager, "studentFragment")
    }
}
