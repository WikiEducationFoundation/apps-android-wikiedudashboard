package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_explore_course_list.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.ProfileCourseListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.CourseDetailActivity
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.CourseData
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [ProfileCourseListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileCourseListFragment : Fragment() {

    private var coursesList: List<CourseData> = ArrayList()

    private lateinit var courseListRecyclerAdapter: ProfileCourseListRecyclerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            coursesList = (it.getSerializable(ARG_PARAM1) as? ProfileResponse)?.courses ?: emptyList()
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

        recyclerCourseList?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = courseListRecyclerAdapter
        }

        setData(coursesList)
        showProgressBar(false)
    }

    /**
     * Use [setData] to set list of courses data
     * @param courses list of courses data
     * ***/
    fun setData(courses: List<CourseData>) {
        Timber.d(courses.toString())
        if (courses.isNotEmpty()) {
            recyclerCourseList?.visibility = View.VISIBLE
            courseListRecyclerAdapter.setData(courses)
            courseListRecyclerAdapter.notifyDataSetChanged()
            textViewNoCourses?.visibility = View.GONE
        } else {
            recyclerCourseList?.visibility = View.GONE
            textViewNoCourses?.visibility = View.VISIBLE
        }
    }

    /**
     * User [showProgressBar] to show loading progress
     *
     * @param show boolean value to determine the visibility of the progress bar
     * ***/
    fun showProgressBar(show: Boolean) {
        progressBar?.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    /**
     * Use [showMessage] to show a toast
     *
     * @param message text message in String
     * ***/
    fun showMessage(message: String) {
        context?.showToast(message)
    }

    private fun openCourseDetail(slug: String) {
        val i = Intent(context, CourseDetailActivity::class.java)
        i.putExtra("url", slug)
        i.putExtra("enrolled", false)
        startActivity(i)
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: ProfileResponse?): ProfileCourseListFragment {
            val fragment = ProfileCourseListFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM1, param1)
            fragment.arguments = args
            return fragment
        }
    }
}
