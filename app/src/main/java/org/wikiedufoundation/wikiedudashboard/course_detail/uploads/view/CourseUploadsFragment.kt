package org.wikiedufoundation.wikiedudashboard.course_detail.uploads.view

import android.content.Context
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
import org.wikiedufoundation.wikiedudashboard.adapters.CourseUploadsRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.presenter.CourseUploadsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.course_detail.uploads.provider.RetrofitCourseUploadsProvider
import org.wikiedufoundation.wikiedudashboard.helper.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.helper.ViewUtils

import butterknife.BindView
import butterknife.ButterKnife

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [CourseUploadsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseUploadsFragment : Fragment(), CourseUploadsView {

    private var courseUrl: String? = null

    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var tvNoStudents: TextView? = null

    private var courseUploadsPresenter: CourseUploadsPresenterImpl? = null
    private var courseUploadsRecyclerAdapter: CourseUploadsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            courseUrl = arguments!!.getString(ARG_PARAM1)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_explore_course_list, container, false)
        val context : Context? = getContext()
        val sharedPrefs: SharedPrefs? = SharedPrefs(context)
        recyclerView = view.findViewById(R.id.rv_course_list)
        progressBar = view.findViewById(R.id.progressBar)
        tvNoStudents = view.findViewById(R.id.tv_no_courses)


        tvNoStudents!!.text = sharedPrefs!!.cookies
        courseUploadsPresenter = CourseUploadsPresenterImpl(this, RetrofitCourseUploadsProvider())
        courseUploadsRecyclerAdapter = CourseUploadsRecyclerAdapter(context!!, this)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.adapter = courseUploadsRecyclerAdapter
        courseUploadsPresenter!!.requestCourseUploads(courseUrl!!)
        return view
    }

    override fun setData(courseUploadList: CourseUploadList) {
        Log.d("DashboardFragment: ", courseUploadList.toString())
        if (courseUploadList.uploads.isNotEmpty()) {
            recyclerView!!.visibility = View.VISIBLE
            courseUploadsRecyclerAdapter!!.setData(courseUploadList.uploads)
            courseUploadsRecyclerAdapter!!.notifyDataSetChanged()
            tvNoStudents!!.visibility = View.GONE
        } else {
            recyclerView!!.visibility = View.GONE
            tvNoStudents!!.visibility = View.VISIBLE
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

    companion object {
        private val ARG_PARAM1 = "param1"

        fun newInstance(courseDetail: String): CourseUploadsFragment {
            val fragment = CourseUploadsFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, courseDetail)
            fragment.arguments = args
            return fragment
        }
    }
}