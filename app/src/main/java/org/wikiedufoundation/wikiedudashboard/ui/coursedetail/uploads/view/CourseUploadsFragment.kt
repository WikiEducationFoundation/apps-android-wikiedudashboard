package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view

import android.content.Context
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
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CourseUploadsRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.presenter.CourseUploadsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.provider.RetrofitCourseUploadsProvider
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsActivity
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

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
        arguments?.let {
            type = it.getInt(ARG_PARAM1)
            courseUrl = it.getString(ARG_PARAM2)
            courseUploadList = it.getSerializable(ARG_PARAM3) as CourseUploadList?
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
        courseUploadsRecyclerAdapter = CourseUploadsRecyclerAdapter(R.layout.item_rv_course_upload) { uploadList, position ->
            openCourseDetail(uploadList, position)
        }
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView?.layoutManager = linearLayoutManager
        recyclerView?.setHasFixedSize(true)
        recyclerView?.adapter = courseUploadsRecyclerAdapter
        if (type == 1) {
            courseUrl?.let { courseUploadsPresenter?.requestCourseUploads(it) }
        } else if (type == 2) {
            courseUploadList?.let { setData(it) }
            showProgressBar(false)
        }
        return view
    }

    override fun setData(courseUploadList: CourseUploadList) {
        Timber.d(courseUploadList.toString())
        if (courseUploadList.uploads.isNotEmpty()) {
            recyclerView?.visibility = View.VISIBLE
            courseUploadsRecyclerAdapter?.setData(courseUploadList.uploads)
            courseUploadsRecyclerAdapter?.notifyDataSetChanged()
            tvNoStudents?.visibility = View.GONE
        } else {
            recyclerView?.visibility = View.GONE
            tvNoStudents?.visibility = View.VISIBLE
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

    /**
     * Use [openCourseDetail] to send course uploads data and each uploads' position
     * and then start MediaDetailsActivity
     *
     * @param courseUploads list of course uploads data to be sent through Bundle extras
     * @param position the position of each course uploads to be sent through Bundle extras
     * ***/
    private fun openCourseDetail(courseUploads: CourseUploadList?, position: Int) {
        val i = Intent(context, MediaDetailsActivity::class.java)
        i.putExtra("uploads", courseUploads)
        i.putExtra("position", position)
        startActivity(i)
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"
        private val ARG_PARAM3 = "param3"

        /**
         * [CourseUploadsFragment.newInstance] factory that creates an instance of this fragment
         * and put [type],[courseDetail],and [courseUploads] variables into Bundle
         *
         * @param type course type
         * @param courseDetail course detail data
         * @param courseUploads course uploads
         * ***/
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
