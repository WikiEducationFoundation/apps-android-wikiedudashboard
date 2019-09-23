package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.ProfilePresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsFragment
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileActivity
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileContract
import org.wikiedufoundation.wikiedudashboard.ui.profile.RetrofitProfileProvider
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetailsResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.ui.settings.SettingsActivity
import org.wikiedufoundation.wikiedudashboard.util.Urls
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter
import org.wikiedufoundation.wikiedudashboard.util.ViewUtils
import timber.log.Timber
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(), ProfileContract.View, Toolbar.OnMenuItemClickListener {

    private var mParam1: String? = null
    private var mParam2: Boolean? = null
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

    private var llAsStudent: LinearLayout? = null
    private var llByStudent: LinearLayout? = null
//    private var llAsStudent: LinearLayout?=null
    private var llEmail:LinearLayout?=null
    private var llLocation:LinearLayout?=null
    private var llInstitute:LinearLayout?=null
    private var llProfileParent: LinearLayout?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        if (arguments != null) {
            mParam1 = arguments!!.getString(ARG_PARAM1)
            mParam2 = arguments!!.getBoolean(ARG_PARAM2)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_training, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)
        progressBar = view.findViewById(R.id.progressBar)
        toolbar!!.inflateMenu(R.menu.menu_profile)
        toolbar!!.setOnMenuItemClickListener(this)

        ivProfilePic = view.findViewById(R.id.iv_profile_pic)
        tvUsername = view.findViewById(R.id.tv_profile_username)
        tvDescription = view.findViewById(R.id.tv_profile_description)
        tvEmail = view.findViewById(R.id.tv_profile_email)
        tvLocation = view.findViewById(R.id.tv_profile_location)
        tvInstitute = view.findViewById(R.id.tv_profile_institute)
        llEmail = view.findViewById(R.id.ll_profile_email)
        llLocation = view.findViewById(R.id.ll_profile_location)
        llInstitute= view.findViewById(R.id.ll_profile_institute)
        llProfileParent = view.findViewById(R.id.ll_profile_parent)
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPager!!.adapter = viewPagerAdapter
        tabLayout!!.setupWithViewPager(viewPager)
//        context = getContext()
        sharedPrefs = SharedPrefs(getContext())
        toolbar!!.setNavigationOnClickListener { activity!!.onBackPressed() }
        presenter = ProfilePresenterImpl(this, RetrofitProfileProvider())
        if (mParam1!!.equals(sharedPrefs!!.userName)) {
            presenter!!.requestProfile(sharedPrefs?.cookies!!, sharedPrefs?.userName!!)
            presenter!!.requestProfileDetails(sharedPrefs?.userName!!)
        } else {
            presenter!!.requestProfile(sharedPrefs?.cookies!!, mParam1!!)
            presenter!!.requestProfileDetails(mParam1!!)
        }
        if (mParam2!!) {
            toolbar!!.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            toolbar!!.setNavigationOnClickListener {
                activity!!.onBackPressed()
            }
        }

        return view
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        val i = Intent(context, SettingsActivity::class.java)
        startActivity(i)
        return true
    }


    private fun setTabs(data: ProfileResponse) {
        val fragmentList = ArrayList<Fragment>()
        val titleList = ArrayList<String>()
        titleList.add("Contribution Statistics")
        fragmentList.add(ProfileStatsFragment.newInstance(data, mParam1!!, mParam2!!))
        titleList.add("Course Details")
        fragmentList.add(ProfileCourseListFragment.newInstance(data))
        titleList.add("Recent Uploads")
        val courseUploadList = CourseUploadList(data.uploads!!)
        fragmentList.add(CourseUploadsFragment.newInstance(2, "", courseUploadList))

        viewPagerAdapter!!.setTabData(fragmentList, titleList)
        viewPagerAdapter!!.notifyDataSetChanged()
    }

    override fun setProfileData(data: ProfileDetailsResponse) {
        llProfileParent!!.visibility = VISIBLE
        val profilePicUrl = Urls.BASE_URL + data.user_profile.profile_image
        Timber.d(profilePicUrl)
        if (data.user_profile.profile_image == null || data.user_profile.profile_image.equals("")) {
            ivProfilePic!!.setImageDrawable(resources.getDrawable(R.drawable.ic_account_circle_white_48dp))
        } else {
            Glide.with(context).load(profilePicUrl).apply(RequestOptions().circleCrop()).into(ivProfilePic)
        }
        tvUsername!!.text = mParam1!!
//        if (data.user_profile.email.isNotEmpty()) {
//            tvEmail!!.text = data.user_profile.email
//        } else {
            llEmail!!.visibility = INVISIBLE
//        }
        if (data.user_profile.bio!=null) {
            tvDescription!!.text = data.user_profile.bio
        } else {
            tvDescription!!.visibility = INVISIBLE
        }
        if (data.user_profile.location!=null) {
            tvLocation!!.text = data.user_profile.location
        } else {
            llLocation!!.visibility = INVISIBLE
        }
//        if (data.user_profile.institution!=null) {
//            tvInstitute!!.text = data.user_profile.institution
//        } else{
            llInstitute!!.visibility = INVISIBLE
//        }
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

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, other_user: Boolean): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putBoolean(ARG_PARAM2, other_user)
            fragment.arguments = args
            return fragment
        }
    }
}