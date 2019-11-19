package org.wikiedufoundation.wikiedudashboard.ui.mediadetail.view

import android.app.DownloadManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_media_details.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.ImageViewerFragment
import org.wikiedufoundation.wikiedudashboard.ui.adapters.CategoryListRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.adapters.FileUsesRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.home.CourseHomeFragment
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUpload
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsActivity
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.MediaDetailsContract
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.RetrofitMediaDetailsProvider
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.MediaDetailsResponse
import org.wikiedufoundation.wikiedudashboard.util.CustomTabHelper
import org.wikiedufoundation.wikiedudashboard.util.showCustomChromeTabs
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber
import java.io.File
import java.io.IOException

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [MediaDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MediaDetailFragment : Fragment(), Toolbar.OnMenuItemClickListener, MediaDetailsContract.View {

    private val retrofitMediaDetailsProvider: RetrofitMediaDetailsProvider by inject()
    private val mediaDetailsPresenter: MediaDetailsContract.Presenter by inject {
        parametersOf(this, retrofitMediaDetailsProvider)
    }

    private var position: Int? = null
    private var courseUpload: CourseUpload? = null
    private var customTabHelper: CustomTabHelper = CustomTabHelper()
    private val baseURL = "https://commons.wikimedia.org/wiki/File:"

    // Media Details
    private lateinit var fileName: String
    private lateinit var courseUploads: CourseUploadList

    private lateinit var categoryListRecyclerAdapter: CategoryListRecyclerAdapter
    private lateinit var fileUsesRecyclerAdapter: FileUsesRecyclerAdapter

    private var downloadID: Long? = null
    private var onDownloadCompleteReceiver: BroadcastReceiver? = null
    private val WRITE_EXTERNAL_STORAGE_RC = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            courseUploads = it.getSerializable(ARG_PARAM1) as CourseUploadList
            position = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
       return inflater.inflate(R.layout.fragment_media_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context: Context? = context

        toolbar.inflateMenu(R.menu.menu_media_details)
        courseUpload = position?.let { (courseUploads.uploads[it]) }
        context?.let { Glide.with(it).load(courseUpload?.thumbUrl).into(ivMediaDetail) }

        fileName = courseUpload?.fileName.toString()
        mediaDetailTitle.text = fileName
        mediaDetailAuthor.text = courseUpload?.uploader
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
        mediaDetailUploadedDate.text = courseUpload?.uploadedAt
        ivMediaDetail.setOnClickListener {
            (context as MediaDetailsActivity).addFragment(ImageViewerFragment.newInstance(courseUpload?.thumbUrl))
        }
        toolbar.setOnMenuItemClickListener(this)

        categoryListRecyclerAdapter = CategoryListRecyclerAdapter(R.layout.item_rv_media_category)
        recyclerCategoryList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = categoryListRecyclerAdapter
        }

        fileUsesRecyclerAdapter = FileUsesRecyclerAdapter(R.layout.item_rv_files)

        recyclerFileUsesList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = fileUsesRecyclerAdapter
        }

        mediaDetailsPresenter.requestMediaDetails("")
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.item_download) {
            downloadImageWithPermission()
            //downloadImage()
            return true
        } else if (item?.itemId == R.id.item_customtabs) {
            val builder = CustomTabsIntent.Builder()
            context?.let { builder.setToolbarColor(ContextCompat.getColor(it, R.color.colorPrimary)) }
            builder.addDefaultShareMenuItem()
            builder.setShowTitle(true)
            context?.let { builder.setExitAnimations(it, android.R.anim.fade_in, android.R.anim.fade_out) }
            val customTabsIntent = builder.build()

            val packageName = context?.let { customTabHelper.getPackageNameToUse(it, baseURL + fileName) }
            packageName?.let {
                customTabsIntent.intent.setPackage(packageName)
                customTabsIntent.launchUrl(context, Uri.parse(baseURL + fileName))
            } /*?: webView*/
            return true
        }
        return false
    }

    private fun downloadImage() {
//        TODO("not implemented")
        val destinationPath = "/wikiedu/images"
        val url = Uri.parse(courseUpload?.thumbUrl)
        val mDir = File(Environment.getExternalStorageDirectory(), "/wikiedu/images/")
        val downloadMgr: DownloadManager = context?.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        try {
            if (!mDir.exists()) {
                mDir.mkdir()
                Timber.d("Folder created at $mDir")
            }
            val downloadRequest: DownloadManager.Request = DownloadManager.Request(url)

            //downloaded image will be stored in Downloads directory of a device
            //if no destination dir is set
            downloadRequest.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI or
                    DownloadManager.Request.NETWORK_MOBILE).setAllowedOverMetered(true)
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setAllowedOverMetered(true).setAllowedOverRoaming(false)
                    .setTitle("Wikiedu Image Download").setDescription("Downloading Image")
                    .setDestinationInExternalPublicDir(destinationPath, mDir.absolutePath)

            downloadID = downloadMgr.enqueue(downloadRequest)

            onDownloadCompleteReceiver = object : BroadcastReceiver() {
                override fun onReceive(context: Context, intent: Intent) {
                    val id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1)
                    if (downloadID == id) {
                        Toast.makeText(context, "Download complete!\n Image "
                                + courseUpload?.fileName + " saved to " + mDir, Toast.LENGTH_LONG).show()
                        Timber.d("image saved to $mDir")
                    }
                }

            }
            context?.registerReceiver(onDownloadCompleteReceiver,
                    IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE))


        } catch (ie: IOException) {
            ie.stackTrace
        }
    }

    private fun downloadImageWithPermission() {
        val permissionType = android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        val isGranted = context?.applicationContext?.let {
            ContextCompat.checkSelfPermission(it,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }
        if (isGranted == PackageManager.PERMISSION_GRANTED) {
            downloadImage()
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(arrayOf(permissionType), WRITE_EXTERNAL_STORAGE_RC)

            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            WRITE_EXTERNAL_STORAGE_RC -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
                        grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Timber.d("Permission denied by user")

                } else {
                    downloadImage()
                    Timber.d("Permission granted by user")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        try {
            context?.unregisterReceiver(onDownloadCompleteReceiver)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }
    }

    override fun setData(data: MediaDetailsResponse) {
        Timber.d(data.toString())

        val imageInfo = data.query.page[data.query.page.keys.first()]?.let { it.imageInfo[0] }

        // Description
        textViewDescription.text = imageInfo?.extMetaData?.description?.value

        // License
        textViewLicence.text = imageInfo?.extMetaData?.license?.value
        textViewLicence.setOnClickListener { imageInfo?.let { context?.showCustomChromeTabs(it.extMetaData.licenseUrl.value) } }

        // Categories
        val categories = data.query.page[data.query.page.keys.first()]?.categories ?: emptyList()
        if (categories.isNotEmpty()) {
            recyclerCategoryList.visibility = View.VISIBLE
            categoryListRecyclerAdapter.setData(categories)
            categoryListRecyclerAdapter.notifyDataSetChanged()
            textViewNoCategories.visibility = View.GONE
        } else {
            recyclerCategoryList.visibility = View.GONE
            textViewNoCategories.visibility = View.VISIBLE
        }

        // File Uses
        val fileUses = data.query.page[data.query.page.keys.first()]?.globalUsage
        if (categories.isNotEmpty()) {
            recyclerFileUsesList.visibility = View.VISIBLE
            fileUses?.let { fileUsesRecyclerAdapter.setData(it) }
            fileUsesRecyclerAdapter.notifyDataSetChanged()
            textViewNoUses.visibility = View.GONE
        } else {
            recyclerFileUsesList.visibility = View.GONE
            textViewNoUses.visibility = View.VISIBLE
        }
    }

    override fun showProgressBar(show: Boolean) {
//        if (show) {
//            progressBar?.visibility = View.VISIBLE
//        } else {
//            progressBar?.visibility = View.GONE
//        }
    }

    override fun showMessage(message: String) {
        context?.showToast(message)
    }

    companion object {
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Returns a new instance of [MediaDetailFragment]
         *
         * @param courseUploadList course uploads list
         * @param position course upload position
         * @return a [MediaDetailFragment] instance
         * ***/
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
