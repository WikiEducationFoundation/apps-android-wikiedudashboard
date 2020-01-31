package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_explore_students.*
import kotlinx.android.synthetic.main.fragment_explore_students.progressBar
import kotlinx.android.synthetic.main.fragment_my_dashboard.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.StudentListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.viewmodel.StudentsViewModel
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileActivity
import org.wikiedufoundation.wikiedudashboard.util.showSnackbar
import timber.log.Timber

/**
 * A [Fragment] that displays list of students
 * ***/
class StudentListFragment : Fragment() {
    private lateinit var url: String
    private lateinit var studentListRecyclerAdapter: StudentListRecyclerAdapter
    private val studentsViewModel by viewModel<StudentsViewModel> { parametersOf(url) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_explore_students, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        url = arguments?.getString("url", null).toString()

        studentListRecyclerAdapter = StudentListRecyclerAdapter(R.layout.item_rv_students) { openStudentProfile(it) }
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
        studentsViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    override fun showMessage(message: String) {
        view?.showSnackbar(message)

    }

    private fun setData() {
        studentsViewModel.studentList.observe(this, Observer {
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
     * @param username user's username in String
     * ***/
    fun openStudentProfile(username: String) {
        val i = Intent(context, ProfileActivity::class.java)
        i.putExtra("username", username)
        startActivity(i)
    }
}