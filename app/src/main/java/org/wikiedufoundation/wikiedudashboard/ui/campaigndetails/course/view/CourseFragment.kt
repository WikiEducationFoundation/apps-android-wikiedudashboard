package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.course_fragment.*
import kotlinx.android.synthetic.main.fragment_explore_students.*
import kotlinx.android.synthetic.main.fragment_explore_students.progressBar
import kotlinx.android.synthetic.main.fragment_explore_students.textViewNoStudents
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CourseRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.data.Course
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.data.DataSource
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.viewmodel.CourseViewModel
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileActivity
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

class CourseFragment : Fragment() {

    private lateinit var url: String
    private val courseList: ArrayList<Course> = ArrayList()
    private lateinit var courseRecyclerAdapter: CourseRecyclerAdapter
    private val courseViewModel by viewModel<CourseViewModel> { parametersOf(url) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.course_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        url = arguments?.getString("url", null).toString()

        courseRecyclerAdapter = CourseRecyclerAdapter(R.layout.item_rv_course) { openCourseDetail(it) }
        initializeRecyclerView()
        setData()
        initializeProgressBar()
        initializeToaster()
    }

    private fun initializeRecyclerView() {
        recyclerCourseList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = courseRecyclerAdapter
        }
    }

    private fun initializeProgressBar() {
        courseViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun initializeToaster() {
        courseViewModel.showMsg.observe(this, androidx.lifecycle.Observer {
            val message = it.showMsg
            context?.showToast(message)
        })
    }

// TODO uncomment the setData() when the API is ready
// and delete the other setData() with the dummy data

//    private fun setData() {
//        courseViewModel.courseList.observe(this, Observer {
//            if (it.isNotEmpty()) {
//                Timber.d(it.toString())
//                courseRecyclerAdapter.setData(it)
//            } else {
//                recyclerCourseList?.visibility = View.GONE
//                textViewNoStudents?.visibility = View.VISIBLE
//            }
//        })
//    }

    // TODO these are dummy data, this method will be remove when the api is ready and replaced with
    // the above commented one
    private fun setData() {
        val list = DataSource.getCourseList()
            if (list.isNotEmpty()) {
                Timber.d(list.toString())
                courseRecyclerAdapter.setData(list)
            } else {
                recyclerCourseList?.visibility = View.GONE
                textViewNoStudents?.visibility = View.VISIBLE
            }
    }

    /**
     * Use [openStudentProfile] to send [username] to [ProfileActivity]
     *
     * @param username user's username in String
     * ***/
    fun openCourseDetail(course: Course) {
        val courseDetailFragment = CourseDetailFragment()
        val bundle = Bundle()
//        bundle.putInt("position", id)
        bundle.putParcelable("course", course)
        courseDetailFragment.arguments = bundle
        courseDetailFragment.show(parentFragmentManager, "coursefragment")
    }
}
