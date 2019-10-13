package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_training.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.ProfilePresenterImpl
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
import java.util.*

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(), ProfileContract.View, Toolbar.OnMenuItemClickListener {

    private var mParam1: String? = null
    private var mParam2: Boolean? = null

    private var sharedPrefs: SharedPrefs? = null
    private var presenter: ProfileContract.Presenter? = null
    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            mParam1 = it.getString(ARG_PARAM1)
            mParam2 = it.getBoolean(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_training, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setToolbar(mParam2)
        setViewPager()
        sharedPrefs = SharedPrefs(requireContext())
        presenter = ProfilePresenterImpl(this, RetrofitProfileProvider())
        val sharedUserName = sharedPrefs?.userName?.let { it }
        val param1Exists = mParam1?.let { it } ?: ""
        if (param1Exists == sharedUserName) {
            sharedPrefs?.cookies?.let { presenter?.requestProfile(it, sharedUserName) }
            presenter?.requestProfileDetails(sharedUserName)
        } else {
            mParam1?.let { param1 ->
                sharedPrefs?.cookies?.let { presenter?.requestProfile(it, param1) }
                presenter?.requestProfileDetails(param1)
            }
        }
    }

    private fun setViewPager() {
        viewPagerAdapter = ViewPagerAdapter(childFragmentManager)
        viewPager?.adapter = viewPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }

    private fun setToolbar(param2: Boolean?) {
        toolbar?.inflateMenu(R.menu.menu_profile)
        toolbar?.setOnMenuItemClickListener(this)
        toolbar?.setNavigationOnClickListener { activity?.onBackPressed() }
        if (param2 != null && param2) {
            toolbar?.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            toolbar?.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
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
        fragmentList.add(ProfileStatsFragment.newInstance(data, mParam1, mParam2))
        titleList.add("Course Details")
        fragmentList.add(ProfileCourseListFragment.newInstance(data))
        titleList.add("Recent Uploads")
        val courseUploadList = data.uploads?.let { CourseUploadList(it) }
        fragmentList.add(CourseUploadsFragment.newInstance(2, "", courseUploadList))

        viewPagerAdapter?.setTabData(fragmentList, titleList)
        viewPagerAdapter?.notifyDataSetChanged()
    }

    @Suppress("UselessCallOnNotNull")
    override fun setProfileData(data: ProfileDetailsResponse) {
        llProfileParent?.visibility = VISIBLE
        tvUserName.text = mParam1
        setUserProfileImage(data)
        llEmail?.visibility = INVISIBLE
        setUserProfileBio(data)
        setUserProfileLocation(data)
        tvDescription?.text = data.userProfile?.bio
        tvLocation?.text = data.userProfile?.location

        llInstitute?.visibility = INVISIBLE

    }

    private fun setUserProfileLocation(data: ProfileDetailsResponse) {
        if (data.userProfile?.location != null) {
            tvLocation?.text = data.userProfile.location
        } else {
            llLocation?.visibility = INVISIBLE
        }
    }

    private fun setUserProfileBio(data: ProfileDetailsResponse) {
        if (data.userProfile?.bio != null) {
            tvDescription?.text = data.userProfile.bio
        } else {
            tvDescription?.visibility = INVISIBLE
        }
    }

    private fun setUserProfileImage(data: ProfileDetailsResponse) {
        if (data.userProfile?.profileImage == null) {
            ivProfilePic?.setImageDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.ic_account_circle_white_48dp) })
        } else {
            val profilePicUrl = Urls.BASE_URL + data.userProfile.profileImage
            Glide.with(context).load(profilePicUrl).apply(RequestOptions().circleCrop()).into(ivProfilePic)
        }
    }

    override fun setData(data: ProfileResponse) {
        setTabs(data)
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