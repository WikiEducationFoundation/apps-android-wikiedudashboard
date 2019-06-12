package org.wikiedufoundation.wikiedudashboard.ui.media_detail

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_media_details.*

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CourseUploadsRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.data.CourseUpload
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.presenter.CourseUploadsPresenterImpl

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [MediaDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MediaDetailFragment : Fragment() {

    private var courseUploads: CourseUploadList? =null
    private var position: Int ?=null

    private var mediaDetailImage: ImageView?=null
    private var tvTitle: TextView?=null
    private var tvDescription: TextView?=null
    private var tvUploadDate: TextView?=null
    private var tvAuthor: TextView?=null
    private var tvLicense: TextView?=null
    private var courseUpload: CourseUpload? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            courseUploads = arguments!!.getSerializable(ARG_PARAM1) as CourseUploadList
            position = arguments!!.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_media_details, container, false)
        val context : Context? = getContext()
        mediaDetailImage = view.findViewById(R.id.iv_media_detail)
        tvAuthor = view.findViewById(R.id.mediaDetailAuthor)
        tvTitle = view.findViewById(R.id.mediaDetailTitle)
        tvUploadDate = view.findViewById(R.id.mediaDetailuploadeddate)
        tvLicense = view.findViewById(R.id.mediaDetailLicense)
        tvDescription = view.findViewById(R.id.mediaDetailDesc)
        courseUpload = (courseUploads!!.uploads[position!!])
        Glide.with(context!!).load(courseUpload!!.thumbUrl).into(mediaDetailImage!!)
        tvTitle!!.text = courseUpload!!.file_name
        tvAuthor!!.text = courseUpload!!.uploader
//        tvDescription!!.text = courseUpload!!.
//        tvLicense!!.text = courseUpload!!.
        tvUploadDate!!.text = courseUpload!!.uploaded_at
        return view
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        fun newInstance(courseUploadList: CourseUploadList?, position: Int): MediaDetailFragment {
            val fragment = MediaDetailFragment()
            val args = Bundle()
            args.putSerializable(ARG_PARAM1, courseUploadList)
            args.putInt(ARG_PARAM2, position)
            fragment.arguments = args
            return fragment
        }
    }
}