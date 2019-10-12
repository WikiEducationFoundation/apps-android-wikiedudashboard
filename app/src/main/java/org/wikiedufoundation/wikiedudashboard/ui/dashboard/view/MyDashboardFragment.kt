package org.wikiedufoundation.wikiedudashboard.ui.dashboard.view

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
import org.wikiedufoundation.wikiedudashboard.ui.adapters.MyDashboardRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.CourseDetailActivity
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.MyDashboardContract
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.MyDashboardPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.RetrofitMyDashboardProvider
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.CourseListData
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [RecentActivityFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MyDashboardFragment : Fragment(), MyDashboardContract.View {

    // TODO: Rename and change types of parameters
    private var mParam1: String? = null
    private var mParam2: String? = null

    private var tvNoCourses: TextView? = null
    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null
    private var coursesList: List<CourseListData>? = ArrayList()

    private var sharedPrefs: SharedPrefs? = null
    private var myDashboardPresenter: MyDashboardContract.Presenter? = null
    private var myDashboardRecyclerAdapter: MyDashboardRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            mParam1 = it.getString(ARG_PARAM1)
            mParam2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_dashboard, container, false)
        recyclerView = view.findViewById(R.id.rv_course_list)
        progressBar = view.findViewById(R.id.progressBar)
        tvNoCourses = view.findViewById(R.id.tv_no_courses)

        sharedPrefs = context?.let { SharedPrefs(it) }
        myDashboardPresenter = MyDashboardPresenterImpl(this, RetrofitMyDashboardProvider())
        myDashboardRecyclerAdapter = MyDashboardRecyclerAdapter(this)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = myDashboardRecyclerAdapter

        sharedPrefs?.cookies?.let { myDashboardPresenter?.requestDashboard(it) }
        return view
    }

    override fun setData(data: MyDashboardResponse) {
        sharedPrefs?.userName = data.user.userName
        Timber.d(data.toString())
        if (data.currentCourses.isNotEmpty()) {
            coursesList = data.currentCourses
            recyclerView?.visibility = View.VISIBLE
            myDashboardRecyclerAdapter?.setData(data.currentCourses)
            myDashboardRecyclerAdapter?.notifyDataSetChanged()
            tvNoCourses?.visibility = View.GONE
        } else {
            recyclerView?.visibility = View.GONE
            tvNoCourses?.visibility = View.VISIBLE
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

    fun openCourseDetail(slug: String) {
        val i = Intent(context, CourseDetailActivity::class.java)
        i.putExtra("url", slug)
        i.putExtra("enrolled", true)
        startActivity(i)
    }

    fun updateSearchQuery(query: String) {
        Timber.d(query)
        val filteredCourseList: ArrayList<CourseListData>? = ArrayList()
        coursesList?.let {
            for (course in it) {
                if (course.title.toLowerCase().contains(query.toLowerCase())) {
                    filteredCourseList?.add(course)
                }
            }
        }
        filteredCourseList?.let { myDashboardRecyclerAdapter?.setData(it) }
        myDashboardRecyclerAdapter?.notifyDataSetChanged()
    }


    companion object {
        // TODO: Rename parameter arguments, choose names that match
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
        fun newInstance(param1: String, param2: String): MyDashboardFragment {
            val fragment = MyDashboardFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}