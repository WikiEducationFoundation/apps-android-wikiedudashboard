package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_upload_list.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CourseUploadsRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.presenter.CourseUploadsPresenter
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

    private val retrofitCourseUploadsProvider: RetrofitCourseUploadsProvider by inject()
    private val courseUploadsPresenter: CourseUploadsPresenter by inject {
        parametersOf(this, retrofitCourseUploadsProvider)
    }

    private var type: Int = 0
    private var courseUrl: String? = null
    private var courseUploadList: CourseUploadList? = null

    private lateinit var courseUploadsRecyclerAdapter: CourseUploadsRecyclerAdapter

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

        courseUploadsRecyclerAdapter = CourseUploadsRecyclerAdapter(R.layout.item_rv_course_upload) {
            uploadList, position ->
            openCourseDetail(uploadList, position)
        }

        recyclerUploadList?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = courseUploadsRecyclerAdapter
        }

        if (type == 1) {
            courseUrl?.let { courseUploadsPresenter.requestCourseUploads(it) }
        } else if (type == 2) {
            courseUploadList?.let { setData(it) }
            showProgressBar(false)
        }
        return view
    }

    override fun setData(courseUploadList: CourseUploadList) {
        Timber.d(courseUploadList.toString())
        if (courseUploadList.uploads.isNotEmpty()) {
            recyclerUploadList?.visibility = View.VISIBLE
            courseUploadsRecyclerAdapter.setData(courseUploadList.uploads)
            courseUploadsRecyclerAdapter.notifyDataSetChanged()
            textViewNoUploads?.visibility = View.GONE
        } else {
            recyclerUploadList?.visibility = View.GONE
            textViewNoUploads?.visibility = View.VISIBLE
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
