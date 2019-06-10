package org.wikiedufoundation.wikiedudashboard.course_list.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.adapters.CourseListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.course_detail.common.view.CourseDetailActivity
import org.wikiedufoundation.wikiedudashboard.course_list.data.ExploreCoursesResponse
import org.wikiedufoundation.wikiedudashboard.course_list.presenter.CourseListPresenterImpl
import org.wikiedufoundation.wikiedudashboard.course_list.provider.RetrofitCourseListProvider
import org.wikiedufoundation.wikiedudashboard.helper.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.helper.ViewUtils

import butterknife.BindView
import butterknife.ButterKnife


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

    private var courseListPresenter: CourseListPresenterImpl? = null
    private var courseListRecyclerAdapter: CourseListRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_explore_course_list, container, false)
        progressBar = view.findViewById(R.id.progressBar)
        tv_no_courses = view.findViewById(R.id.tv_no_courses)
        recyclerView = view.findViewById(R.id.rv_course_list)

        val context: Context? = getContext()
        val sharedPrefs : SharedPrefs? = SharedPrefs(context)
        tv_no_courses!!.text = sharedPrefs!!.cookies
        courseListPresenter = CourseListPresenterImpl(this, RetrofitCourseListProvider())
        courseListRecyclerAdapter = CourseListRecyclerAdapter(context!!, this)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.adapter = courseListRecyclerAdapter

        courseListPresenter!!.requestDashboard(sharedPrefs.cookies!!)
        return view
    }

    override fun setData(data: ExploreCoursesResponse) {
        Log.d("DashboardFragment: ", data.toString())
        if (data.courses.size > 0) {
            recyclerView!!.visibility = View.VISIBLE
            courseListRecyclerAdapter!!.setData(data.courses)
            courseListRecyclerAdapter!!.notifyDataSetChanged()
            tv_no_courses!!.visibility = View.GONE
        } else {
            recyclerView!!.visibility = View.GONE
            tv_no_courses!!.visibility = View.VISIBLE
        }

    }

    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar!!.visibility = View.VISIBLE
        } else {
            progressBar!!.visibility = View.GONE
        }
    }

    override fun showMessage(message: String) {
        ViewUtils.showToast(context!!, message)
    }

    fun openCourseDetail(slug: String) {
        val i = Intent(context, CourseDetailActivity::class.java)
        i.putExtra("url", slug)
        startActivity(i)
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
        fun newInstance(param1: String, param2: String): CourseListFragment {
            val fragment = CourseListFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
