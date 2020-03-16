package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_explore_course_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.ProfileCourseListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.CourseDetailActivity
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.CourseData
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.viewmodel.ProfileViewModel
import org.wikiedufoundation.wikiedudashboard.util.showSnackbar
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [ProfileCourseListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileCourseListFragment : Fragment() {
    private val profileViewModel by viewModel<ProfileViewModel>()
    private var coursesList: List<CourseData> = ArrayList()
    private lateinit var courseListRecyclerAdapter: ProfileCourseListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            coursesList = (it.getSerializable(ARG_PARAM1) as? ProfileResponse)?.courses
                    ?: emptyList()
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_explore_course_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        courseListRecyclerAdapter = ProfileCourseListRecyclerAdapter(R.layout.item_rv_explore_courses_users) {
            openCourseDetail(it)
        }
        initializeRecyclerView()
        setData()
        initializeProgressBar()
        initializeToaster()
    }

    private fun initializeRecyclerView() {
        recyclerCourseList?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = courseListRecyclerAdapter
        }
    }

    /**
     * Use [setData] to set list of courses data
     * @param courses list of courses data
     * ***/
    fun setData() {
        profileViewModel.profile.observe(this, Observer {
            val courses = it.courses
            Timber.d(it.courses.toString())
            if (courses.isNotEmpty()) {
                recyclerCourseList?.visibility = View.VISIBLE
                courseListRecyclerAdapter.setData(courses)
                textViewNoCourses?.visibility = View.GONE
            } else {
                recyclerCourseList?.visibility = View.GONE
                textViewNoCourses?.visibility = View.VISIBLE
            }
        })
    }

    private fun initializeProgressBar() {
        profileViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun initializeToaster() {
        profileViewModel.showMsg.observe(this, androidx.lifecycle.Observer {
            val message = it.showMsg
            view?.showSnackbar(message)
        })
    }

    private fun openCourseDetail(slug: String) {
        val i = Intent(context, CourseDetailActivity::class.java)
        i.putExtra("url", slug)
        i.putExtra("enrolled", false)
        startActivity(i)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: ProfileResponse?) = ProfileCourseListFragment().apply {
            val args = Bundle()
            args.putSerializable(ARG_PARAM1, param1)
            this.arguments = args
        }
    }
}
