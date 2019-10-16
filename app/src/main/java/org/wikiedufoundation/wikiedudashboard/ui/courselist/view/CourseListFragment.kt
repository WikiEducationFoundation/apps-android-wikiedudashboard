package org.wikiedufoundation.wikiedudashboard.ui.courselist.view

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
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CourseListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.CourseDetailActivity
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.ui.courselist.presenter.CourseListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.courselist.provider.RetrofitCourseListProvider
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.CourseListData
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [CourseListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseListFragment : Fragment(), CourseListView {

    private var mParam1: String? = null
    private var mParam2: String? = null
    private var tv_no_courses: TextView? = null
    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null
    private var coursesList: List<CourseListData> = ArrayList()
    private var courseListPresenter: CourseListPresenterImpl? = null
    private var courseListRecyclerAdapter: CourseListRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mParam1 = arguments?.getString(ARG_PARAM1)
            mParam2 = arguments?.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore_course_list, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        tv_no_courses = view.findViewById(R.id.tv_no_courses)
        recyclerView = view.findViewById(R.id.rv_course_list)

        val context = context
        val sharedPrefs: SharedPrefs? = context?.let { SharedPrefs(it) }
        tv_no_courses?.text = sharedPrefs?.cookies
        courseListPresenter = CourseListPresenterImpl(this, RetrofitCourseListProvider())
        courseListRecyclerAdapter = CourseListRecyclerAdapter(R.layout.item_rv_explore_courses) { openCourseDetail(it) }
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = courseListRecyclerAdapter

        sharedPrefs?.cookies?.let { courseListPresenter?.requestDashboard(it) }
        return view
    }

    override fun setData(data: ExploreCoursesResponse) {
        Timber.d(data.toString())
        if (data.courses.isNotEmpty()) {
            coursesList = data.courses
            recyclerView?.visibility = View.VISIBLE
            courseListRecyclerAdapter?.setData(data.courses)
            courseListRecyclerAdapter?.notifyDataSetChanged()
            tv_no_courses?.visibility = View.GONE
        } else {
            recyclerView?.visibility = View.GONE
            tv_no_courses?.visibility = View.VISIBLE
        }
    }

    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    override fun showMessage(message: String) {
        context?.showToast(message)
    }

    private fun openCourseDetail(slug: String) {
        val i = Intent(context, CourseDetailActivity::class.java)
        i.putExtra("url", slug)
        i.putExtra("enrolled", false)
        startActivity(i)
    }

    fun updateSearchQuery(query: String) {
        Timber.d(query)
        val filteredCourseList: ArrayList<CourseListData>? = ArrayList()
        for (course in coursesList) {
            if (course.title.toLowerCase().contains(query.toLowerCase())) {
                filteredCourseList?.add(course)
            }
        }
        filteredCourseList?.let { courseListRecyclerAdapter?.setData(it) }
        courseListRecyclerAdapter?.notifyDataSetChanged()
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): CourseListFragment {
            val fragment = CourseListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}
