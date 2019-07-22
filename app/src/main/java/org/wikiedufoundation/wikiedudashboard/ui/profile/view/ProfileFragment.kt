package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.RoundedBitmapDrawable
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.BitmapImageViewTarget
import com.google.android.material.tabs.TabLayout
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.ProfilePresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.profile.RetrofitProfileProvider
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsFragment
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileContract
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetailsResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.util.Urls
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter
import org.wikiedufoundation.wikiedudashboard.util.ViewUtils
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(), ProfileContract.View {

    private var toolbar: Toolbar? = null
    private var tabLayout: TabLayout? = null
    private var viewPager: ViewPager? = null
    private var progressBar: ProgressBar? = null

    private var sharedPrefs: SharedPrefs? = null
    private var presenter: ProfileContract.Presenter? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null

    private var ivProfilePic: ImageView? = null
    private var tvUsername: TextView? = null
    private var tvDescription: TextView? = null
    private var tvEmail: TextView? = null
    private var tvLocation: TextView? = null
    private var tvInstitute: TextView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_training, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)
        progressBar = view.findViewById(R.id.progressBar)

        ivProfilePic = view.findViewById(R.id.iv_profile_pic)
        tvUsername = view.findViewById(R.id.tv_profile_username)
        tvDescription = view.findViewById(R.id.tv_profile_description)
        tvEmail = view.findViewById(R.id.tv_profile_email)
        tvLocation = view.findViewById(R.id.tv_profile_location)
        tvInstitute = view.findViewById(R.id.tv_profile_institute)

        viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPager!!.adapter = viewPagerAdapter
        tabLayout!!.setupWithViewPager(viewPager)
//        context = getContext()
        sharedPrefs = SharedPrefs(getContext())
        toolbar!!.setNavigationOnClickListener { activity!!.onBackPressed() }
        presenter = ProfilePresenterImpl(this, RetrofitProfileProvider())
        presenter!!.requestProfile(sharedPrefs?.cookies!!, sharedPrefs?.userName!!)
        presenter!!.requestProfileDetails(sharedPrefs?.userName!!)
        return view
    }

    private fun setTabs(data: ProfileResponse) {
        val fragmentList = ArrayList<Fragment>()
        val titleList = ArrayList<String>()
        titleList.add("Contribution Statistics")
        fragmentList.add(ProfileStatsFragment.newInstance(data))
        titleList.add("Course Details")
        fragmentList.add(ProfileCourseListFragment.newInstance(data))
        titleList.add("Recent Uploads")
        val courseUploadList = CourseUploadList(data.uploads!!)
        fragmentList.add(CourseUploadsFragment.newInstance(2, "", courseUploadList))

        viewPagerAdapter!!.setTabData(fragmentList, titleList)
        viewPagerAdapter!!.notifyDataSetChanged()
    }

    override fun setProfileData(data: ProfileDetailsResponse) {
        val profilePicUrl = Urls.BASE_URL + data.user_profile.profile_image
        Log.d("ProfileFragment", profilePicUrl)
        if (data.user_profile.profile_image==null || data.user_profile.profile_image.equals("")){
            ivProfilePic!!.setImageDrawable(resources.getDrawable(R.drawable.ic_account_circle_white_48dp))
        }else{
            Glide.with(context).load(profilePicUrl).apply(RequestOptions().circleCrop()).into(ivProfilePic)
        }
        tvUsername!!.text = sharedPrefs!!.userName
        tvEmail!!.text = "demo@wikiedu.org"
        tvDescription!!.text = "Demo Description"
        tvLocation!!.text = "Demo Location"
        tvInstitute!!.text = "Demo Institute"
    }

    override fun setData(data: ProfileResponse) {
        setTabs(data)
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

}