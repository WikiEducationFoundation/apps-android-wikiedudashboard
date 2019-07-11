package org.wikiedufoundation.wikiedudashboard.ui.mediadetail.view

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.ImageViewerFragment
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CategoryListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.adapters.FileUsesRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUpload
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsContract
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsActivity
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.RetrofitMediaDetailsProvider
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.FileUsage
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.ImageDetails
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.MediaCategory
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.MediaDetailsResponse
import org.wikiedufoundation.wikiedudashboard.util.CustomTabHelper
import org.wikiedufoundation.wikiedudashboard.util.ViewUtils

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [MediaDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MediaDetailFragment : Fragment(), Toolbar.OnMenuItemClickListener, MediaDetailsContract.View {

    private var courseUploads: CourseUploadList? = null
    private var position: Int? = null
    private var customTabHelper: CustomTabHelper = CustomTabHelper()

    // Media Details
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

    // Other Utils
    private var progressBar: ProgressBar? = null
    private var tvNoCategories: TextView? = null
    private var categoriesRecyclerView: RecyclerView? = null
    private var tvNoFileUses: TextView? = null
    private var fileUsesRecyclerView: RecyclerView? = null

    private var sharedPrefs: SharedPrefs? = null
    private var mediaDetailsPresenter: MediaDetailsContract.Presenter? = null
    private var categoryListRecyclerAdapter: CategoryListRecyclerAdapter? = null
    private var fileusesRecyclerAdapter: FileUsesRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            courseUploads = arguments!!.getSerializable(ARG_PARAM1) as CourseUploadList
            position = arguments!!.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_media_details, container, false)
        val context: Context? = context
        mediaDetailImage = view.findViewById(R.id.iv_media_detail)
        tvAuthor = view.findViewById(R.id.mediaDetailAuthor)
        tvTitle = view.findViewById(R.id.mediaDetailTitle)
        tvUploadDate = view.findViewById(R.id.mediaDetailuploadeddate)
        tvLicense = view.findViewById(R.id.mediaDetailLicense)
        tvDescription = view.findViewById(R.id.mediaDetailDesc)
        toolbar = view.findViewById(R.id.toolbar)

        categoriesRecyclerView = view.findViewById(R.id.rv_category_list)
//        progressBar = view.findViewById(R.id.progressBar)
        tvNoCategories = view.findViewById(R.id.tv_no_categories)
        fileUsesRecyclerView = view.findViewById(R.id.rv_file_uses_list)
        tvNoFileUses = view.findViewById(R.id.tv_no_uses)

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

        mediaDetailsPresenter = MediaDetailsPresenterImpl(this, RetrofitMediaDetailsProvider())
        categoryListRecyclerAdapter = CategoryListRecyclerAdapter(context, this)
        val linearLayoutManager = LinearLayoutManager(context)
        categoriesRecyclerView!!.layoutManager = linearLayoutManager
        categoriesRecyclerView!!.setHasFixedSize(true)
        categoriesRecyclerView!!.adapter = categoryListRecyclerAdapter

        fileusesRecyclerAdapter = FileUsesRecyclerAdapter(context, this)
        val linearLayoutManager2 = LinearLayoutManager(context)
        fileUsesRecyclerView!!.layoutManager = linearLayoutManager2
        fileUsesRecyclerView!!.setHasFixedSize(true)
        fileUsesRecyclerView!!.adapter = fileusesRecyclerAdapter

        mediaDetailsPresenter!!.requestMediaDetails("")

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
//        TODO("not implemented")
    }

    override fun setData(data: MediaDetailsResponse) {
        Log.d("MediaDetailFragment: ", data.toString())

        // Description
        val imageinfo: ImageDetails = data.query.page.get(data.query.page.keys.first())!!.imageinfo.get(0)
//        tvDescription!!.text = imageinfo.extMetaData

        // License

        // Categories
        val categories: List<MediaCategory> = data.query.page.get(data.query.page.keys.first())!!.categories
        if (categories.isNotEmpty()) {
            categoriesRecyclerView!!.visibility = View.VISIBLE
            categoryListRecyclerAdapter!!.setData(categories)
            categoryListRecyclerAdapter!!.notifyDataSetChanged()
            tvNoCategories!!.visibility = View.GONE
        } else {
            categoriesRecyclerView!!.visibility = View.GONE
            tvNoCategories!!.visibility = View.VISIBLE
        }

        // File Uses
        val fileuses: List<FileUsage> = data.query.page.get(data.query.page.keys.first())!!.globalusage
        if (categories.isNotEmpty()) {
            fileUsesRecyclerView!!.visibility = View.VISIBLE
            fileusesRecyclerAdapter!!.setData(fileuses)
            fileusesRecyclerAdapter!!.notifyDataSetChanged()
            tvNoFileUses!!.visibility = View.GONE
        } else {
            fileUsesRecyclerView!!.visibility = View.GONE
            tvNoFileUses!!.visibility = View.VISIBLE
        }
    }

    override fun showProgressBar(show: Boolean) {
//        if (show) {
//            progressBar!!.visibility = View.VISIBLE
//        } else {
//            progressBar!!.visibility = View.GONE
//        }
    }

    override fun showMessage(message: String) {
        ViewUtils.showToast(context!!, message)
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