package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_upload_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CourseUploadsRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.viewmodel.CourseUploadsViewModel
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsActivity
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [CourseUploadsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CourseUploadsFragment : Fragment() {

    private var type: Int = 0
    private var courseUrl: String? = null
    private var courseUploadList: CourseUploadList? = null
    private lateinit var courseUploadsRecyclerAdapter: CourseUploadsRecyclerAdapter
    private val courseUploadsViewModel by viewModel<CourseUploadsViewModel> { parametersOf(courseUrl) }

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
    ): View? = inflater.inflate(R.layout.fragment_upload_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        courseUploadsRecyclerAdapter = CourseUploadsRecyclerAdapter(R.layout.item_rv_course_upload) { uploadList, position ->
            openCourseDetail(uploadList, position)
        }
        initializeRecyclerView()
        setData()
        initializeProgressBar()
        initializeToaster()
    }

    private fun initializeRecyclerView() {
        recyclerUploadList?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = courseUploadsRecyclerAdapter
        }
    }
    private fun setData() {
        courseUploadsViewModel.uploadList.observe(this, Observer {
            Timber.d(it.toString())
            if (it.isNotEmpty()) {
                recyclerUploadList?.visibility = View.VISIBLE
                courseUploadsRecyclerAdapter.setData(it)
                textViewNoUploads?.visibility = View.GONE
            } else {
                recyclerUploadList?.visibility = View.GONE
                textViewNoUploads?.visibility = View.VISIBLE
            }
        })
    }

    private fun initializeProgressBar() {
        courseUploadsViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun initializeToaster() {
        courseUploadsViewModel.showMsg.observe(this, androidx.lifecycle.Observer {
            val message = it.showMsg
            context?.showToast(message)
        })
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
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        private const val ARG_PARAM3 = "param3"

        /**
         * [CourseUploadsFragment.newInstance] factory that creates an instance of this fragment
         * and put [type], [courseDetail],and [courseUploads] variables into Bundle
         *
         * @param type course type
         * @param courseDetail course detail data
         * @param courseUploads course uploads
         * ***/
        fun newInstance(type: Int, courseDetail: String, courseUploads: CourseUploadList?) = CourseUploadsFragment().apply {
            val args = Bundle()
            args.putInt(ARG_PARAM1, type)
            args.putString(ARG_PARAM2, courseDetail)
            args.putSerializable(ARG_PARAM3, courseUploads)
            this.arguments = args
        }
    }
}
