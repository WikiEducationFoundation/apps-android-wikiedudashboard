package org.wikiedufoundation.wikiedudashboard.ui.courselist.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_explore_course_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CourseListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.CourseDetailActivity
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.ui.courselist.presenter.CourseListPresenter
import org.wikiedufoundation.wikiedudashboard.ui.courselist.provider.RetrofitCourseListProvider
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.CourseListData
import org.wikiedufoundation.wikiedudashboard.util.filterOrEmptyList
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [CourseListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseListFragment : Fragment(), CourseListView {

    private val retrofitCourseListProvider: RetrofitCourseListProvider by inject()
    private val courseListPresenter: CourseListPresenter by inject {
        parametersOf(this, retrofitCourseListProvider)
    }
    private val sharedPrefs: SharedPrefs by inject()

    private var mParam1: String? = null
    private var mParam2: String? = null
    private var coursesList: List<CourseListData> = ArrayList()

    private lateinit var courseListRecyclerAdapter: CourseListRecyclerAdapter

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
    ): View? = inflater.inflate(R.layout.fragment_explore_course_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        textViewNoCourses?.text = sharedPrefs.cookies

        courseListRecyclerAdapter = CourseListRecyclerAdapter(R.layout.item_rv_explore_courses) {
            openCourseDetail(it)
        }

        recyclerCourseList?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = courseListRecyclerAdapter
        }

        sharedPrefs.cookies?.let { courseListPresenter.requestDashboard(it) }
    }

    override fun setData(data: ExploreCoursesResponse) {
        Timber.d(data.toString())
        if (data.courses.isNotEmpty()) {
            coursesList = data.courses
            recyclerCourseList?.visibility = View.VISIBLE
            courseListRecyclerAdapter.setData(data.courses)
            courseListRecyclerAdapter.notifyDataSetChanged()
            textViewNoCourses?.visibility = View.GONE
        } else {
            recyclerCourseList?.visibility = View.GONE
            textViewNoCourses?.visibility = View.VISIBLE
        }
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

    private fun openCourseDetail(slug: String) {
        val i = Intent(context, CourseDetailActivity::class.java)
        i.putExtra("url", slug)
        i.putExtra("enrolled", false)
        startActivity(i)
    }

    fun updateSearchQuery(query: String) {
        Timber.d(query)

        val filterCourseQuery = coursesList.filterOrEmptyList {
            it.title.toLowerCase()
                    .contains(query.toLowerCase())
        }

        courseListRecyclerAdapter.setData(filterCourseQuery)
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
