package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import timber.log.Timber
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CourseUploadsRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.presenter.CourseUploadsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider.RetrofitCourseUploadsProvider
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsActivity
import org.wikiedufoundation.wikiedudashboard.util.ViewUtils

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [CourseUploadsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseUploadsFragment : Fragment(), CourseUploadsView {

    private var courseUrl: String? = null
    private var type: Int = 0
    private var courseUploadList: CourseUploadList? = null

    private var recyclerView: RecyclerView? = null
    private var progressBar: ProgressBar? = null
    private var tvNoStudents: TextView? = null

    private var courseUploadsPresenter: CourseUploadsPresenterImpl? = null
    private var courseUploadsRecyclerAdapter: CourseUploadsRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            type = arguments!!.getInt(ARG_PARAM1)
            courseUrl = arguments!!.getString(ARG_PARAM2)
            courseUploadList = arguments!!.getSerializable(ARG_PARAM3) as CourseUploadList?
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upload_list, container, false)
        val context: Context? = context
        recyclerView = view.findViewById(R.id.rv_upload_list)
        progressBar = view.findViewById(R.id.progressBar)
        tvNoStudents = view.findViewById(R.id.tv_no_uploads)

        courseUploadsPresenter = CourseUploadsPresenterImpl(this, RetrofitCourseUploadsProvider())
        courseUploadsRecyclerAdapter = CourseUploadsRecyclerAdapter(context!!, this)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView!!.layoutManager = linearLayoutManager
        recyclerView!!.setHasFixedSize(true)
        recyclerView!!.adapter = courseUploadsRecyclerAdapter
        if (type == 1) {
            courseUploadsPresenter!!.requestCourseUploads(courseUrl!!)
        } else if (type == 2) {
            setData(courseUploadList!!)
            showProgressBar(false)
        }
        return view
    }

    override fun setData(courseUploadList: CourseUploadList) {
        Timber.d(courseUploadList.toString())
        if (courseUploadList.uploads.isNotEmpty()) {
            recyclerView!!.visibility = View.VISIBLE
            courseUploadsRecyclerAdapter!!.setData(courseUploadList)
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

    fun openCourseDetail(courseUploads: CourseUploadList?, position: Int) {
        val i = Intent(context, MediaDetailsActivity::class.java)
        i.putExtra("uploads", courseUploads)
        i.putExtra("position", position)
        startActivity(i)
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        private val ARG_PARAM3 = "param3"

        fun newInstance(type: Int, courseDetail: String, courseUploads: CourseUploadList?): CourseUploadsFragment {
            val fragment = CourseUploadsFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, type)
            args.putString(ARG_PARAM2, courseDetail)
            args.putSerializable(ARG_PARAM3, courseUploads)
            fragment.arguments = args
            return fragment
        }
    }
}