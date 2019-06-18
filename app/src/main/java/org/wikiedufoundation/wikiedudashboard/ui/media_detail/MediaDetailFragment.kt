package org.wikiedufoundation.wikiedudashboard.ui.media_detail

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_media_details.*

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.ImageViewerFragment
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CourseUploadsRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.data.CourseUpload
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.course_detail.uploads.presenter.CourseUploadsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.util.CustomTabHelper

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [MediaDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MediaDetailFragment : Fragment(), Toolbar.OnMenuItemClickListener {


    private var courseUploads: CourseUploadList? = null
    private var position: Int? = null
    private var customTabHelper: CustomTabHelper = CustomTabHelper()

    private var mediaDetailImage: ImageView? = null
    private var tvTitle: TextView? = null
    private var tvDescription: TextView? = null
    private var tvUploadDate: TextView? = null
    private var tvAuthor: TextView? = null
    private var tvLicense: TextView? = null
    private var courseUpload: CourseUpload? = null
    private var toolbar: Toolbar? = null
    private val baseURL: String? = "https://commons.wikimedia.org/wiki/File:"
    private var fileName: String? = null

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
        val context: Context? = getContext()
        mediaDetailImage = view.findViewById(R.id.iv_media_detail)
        tvAuthor = view.findViewById(R.id.mediaDetailAuthor)
        tvTitle = view.findViewById(R.id.mediaDetailTitle)
        tvUploadDate = view.findViewById(R.id.mediaDetailuploadeddate)
        tvLicense = view.findViewById(R.id.mediaDetailLicense)
        tvDescription = view.findViewById(R.id.mediaDetailDesc)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar!!.inflateMenu(R.menu.menu_media_details)
        courseUpload = (courseUploads!!.uploads[position!!])
        Glide.with(context!!).load(courseUpload!!.thumbUrl).into(mediaDetailImage!!)
        fileName = courseUpload!!.file_name
        tvTitle!!.text = fileName
        tvAuthor!!.text = courseUpload!!.uploader
//        tvDescription!!.text = courseUpload!!.
//        tvLicense!!.text = courseUpload!!.
        toolbar!!.setNavigationOnClickListener { activity!!.onBackPressed() }
        tvUploadDate!!.text = courseUpload!!.uploaded_at
        mediaDetailImage!!.setOnClickListener {
            (context as MediaDetailsActivity).addFragment(ImageViewerFragment.newInstance(courseUpload!!.thumbUrl))
        }
        toolbar!!.setOnMenuItemClickListener(this)
        return view
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item!!.itemId == R.id.item_download) {
            downloadImage()
            return true
        } else if (item.itemId == R.id.item_customtabs) {
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(ContextCompat.getColor(context!!, R.color.colorPrimary))
            builder.addDefaultShareMenuItem()
            builder.setShowTitle(true)
            builder.setExitAnimations(context!!, android.R.anim.fade_in, android.R.anim.fade_out)
            val customTabsIntent = builder.build()

            val packageName = customTabHelper.getPackageNameToUse(context!!, baseURL + fileName)
            if (packageName == null) {
                // webview.
            } else {
                customTabsIntent.intent.setPackage(packageName)
                customTabsIntent.launchUrl(context, Uri.parse(baseURL + fileName))
            }
            return true
        }
        return false
    }

    private fun downloadImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
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