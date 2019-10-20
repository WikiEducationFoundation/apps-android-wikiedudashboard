package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsFragment
import org.wikiedufoundation.wikiedudashboard.ui.profile.ProfileContract
import org.wikiedufoundation.wikiedudashboard.ui.profile.RetrofitProfileProvider
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetailsResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.ui.settings.SettingsActivity
import org.wikiedufoundation.wikiedudashboard.util.Urls
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(), ProfileContract.View, Toolbar.OnMenuItemClickListener {

    private val retrofitProfileProvider: RetrofitProfileProvider by inject()
    private val profilePresenter: ProfileContract.Presenter by inject {
        parametersOf(this, retrofitProfileProvider)
    }
    private val sharedPrefs: SharedPrefs by inject()

    private var mParam1: String? = null
    private var mParam2: Boolean? = null

    private lateinit var toolbar: Toolbar
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager
    private lateinit var progressBar: ProgressBar

    private lateinit var ivProfilePic: ImageView
    private lateinit var tvUsername: TextView
    private lateinit var tvDescription: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvLocation: TextView
    private lateinit var tvInstitute: TextView

    private lateinit var llEmail: LinearLayout
    private lateinit var llLocation: LinearLayout
    private lateinit var llInstitute: LinearLayout
    private lateinit var llProfileParent: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            mParam1 = it.getString(ARG_PARAM1)
            mParam2 = it.getBoolean(ARG_PARAM2)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_training, container, false)
        toolbar = view.findViewById(R.id.toolbar)
        tabLayout = view.findViewById(R.id.tabLayout)
        viewPager = view.findViewById(R.id.viewPager)
        progressBar = view.findViewById(R.id.progressBar)
        toolbar.inflateMenu(R.menu.menu_profile)
        toolbar.setOnMenuItemClickListener(this)

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
        tabLayout.setupWithViewPager(viewPager)

        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        val sharedUserName = sharedPrefs.userName?.let { it }
        val param1Exists = mParam1?.let { it } ?: ""

        if (param1Exists == sharedUserName) {
            sharedPrefs.cookies?.let { profilePresenter.requestProfile(it, sharedUserName) }
            profilePresenter.requestProfileDetails(sharedUserName)
        } else {
            mParam1?.let { param1 ->
                sharedPrefs.cookies?.let { profilePresenter.requestProfile(it, param1) }
                profilePresenter.requestProfileDetails(param1)
            }
        }
        mParam2.let {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
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
        val courseUploadList = data.uploads?.let { CourseUploadList(it) }

        val fragmentList = listOf(ProfileStatsFragment.newInstance(data, mParam1, mParam2),
                ProfileCourseListFragment.newInstance(data),
                CourseUploadsFragment.newInstance(2, "", courseUploadList))

        val titleList = listOf("Contribution Statistics", "Course Details", "Recent Uploads")

        viewPager.apply {
            adapter = ViewPagerAdapter(childFragmentManager, fragmentList, titleList)
        }

    }

    @Suppress("UselessCallOnNotNull")
    override fun setProfileData(data: ProfileDetailsResponse) {
        llProfileParent.visibility = View.VISIBLE
        val profilePicUrl = Urls.BASE_URL + data.userProfile?.profileImage
        Timber.d(profilePicUrl)
        if (data.userProfile?.profileImage.isNullOrEmpty()) {
            ivProfilePic.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.ic_account_circle_white_48dp) })
        } else {
            Glide.with(context).load(profilePicUrl).apply(RequestOptions().circleCrop()).into(ivProfilePic)
        }
        tvUsername.text = mParam1

        llEmail.visibility = View.INVISIBLE

        data.userProfile?.bio.let { tvDescription.text = it }
        tvDescription.text = data.userProfile?.bio
        tvLocation.text = data.userProfile?.location

        llInstitute.visibility = View.INVISIBLE
    }

    override fun setData(data: ProfileResponse) {
        setTabs(data)
    }

    override fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) { View.VISIBLE } else { View.GONE }
    }

    override fun showMessage(message: String) {
        context?.showToast(message)
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
        fun newInstance(param1: String?, other_user: Boolean): ProfileFragment {
            val fragment = ProfileFragment()
            val args = Bundle()
            param1?.let { args.putString(ARG_PARAM1, it) }
            args.putBoolean(ARG_PARAM2, other_user)
            fragment.arguments = args
            return fragment
        }
    }
}