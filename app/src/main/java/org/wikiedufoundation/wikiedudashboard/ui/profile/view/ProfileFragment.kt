package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.fragment_training.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUploadList
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.view.CourseUploadsFragment
import org.wikiedufoundation.wikiedudashboard.ui.profile.viewmodel.ProfileViewModel
import org.wikiedufoundation.wikiedudashboard.ui.settings.SettingsActivity
import org.wikiedufoundation.wikiedudashboard.util.Urls
import org.wikiedufoundation.wikiedudashboard.util.ViewPagerAdapter
import org.wikiedufoundation.wikiedudashboard.util.showSnackbar
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 */
class ProfileFragment : Fragment(), Toolbar.OnMenuItemClickListener {
    private val profileViewModel by viewModel<ProfileViewModel>()
    private val sharedPrefs: SharedPrefs by inject()

    private var mParam1: String? = null
    private var mParam2: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments?.let {
            mParam1 = it.getString(ARG_PARAM1)
            mParam2 = it.getBoolean(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View = inflater.inflate(R.layout.fragment_training, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar.inflateMenu(R.menu.menu_profile)
        toolbar.setOnMenuItemClickListener(this)
        tabLayout.setupWithViewPager(viewPager)

        val sharedUserName = sharedPrefs.userName?.let { it }
        val param1Exists = mParam1?.let { it } ?: ""

        if (param1Exists == sharedUserName) {
            sharedPrefs.cookies?.let { profileViewModel.requestProfile(it, sharedUserName) }
            profileViewModel.requestProfileDetails(sharedUserName)
        } else {
            mParam1?.let { param1 ->
                sharedPrefs.cookies?.let { profileViewModel.requestProfile(it, param1) }
                profileViewModel.requestProfileDetails(param1)
            }
        }
        if (mParam2) {
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            toolbar.setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
        setTabData()
        setProfileData()
        initializeProgressBar()
        initializeToaster()
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        val i = Intent(context, SettingsActivity::class.java)
        startActivity(i)
        return true
    }

    private fun setTabData() {
        profileViewModel.profile.observe(
            this,
            Observer {
                val courseUploadList = it?.uploads?.let { CourseUploadList(it) }

                val fragmentList = listOf(
                    ProfileStatsFragment.newInstance(it, mParam1, mParam2),
                    ProfileCourseListFragment.newInstance(it),
                    CourseUploadsFragment.newInstance(2, "", courseUploadList)
                )

                val titleList = listOf("Contribution Statistics", "Course Details", "Recent Uploads")

                viewPager.apply {
                    adapter = ViewPagerAdapter(childFragmentManager, fragmentList, titleList)
                }
            }
        )
    }

    private fun setProfileData() {
        profileViewModel.profileDetails.observe(
            this,
            Observer {
                llProfileParent.visibility = View.VISIBLE
                val profilePicUrl = Urls.BASE_URL + it?.profileImage
                Timber.d(profilePicUrl)
                Glide.with(requireContext()).load(profilePicUrl)
                    .apply(
                        RequestOptions().placeholder(R.drawable.ic_account_circle_white_48dp)
                            .circleCrop()
                    ).into(ivProfilePic)

                tvProfileUsername.text = mParam1
                tvProfileDescription.text = it?.bio
                tvProfileLocation.text = it?.location
                tvProfileInstitute.text = it?.institution
                llProfileEmail.visibility = View.GONE
            }
        )
    }

    private fun initializeProgressBar() {
        profileViewModel.progressbar.observe(
            this,
            androidx.lifecycle.Observer {
                progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }
        )
    }

    private fun initializeToaster() {
        profileViewModel.showMsg.observe(
            this,
            androidx.lifecycle.Observer {
                val message = it.showMsg
                view?.showSnackbar(message)
            }
        )
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String?, other_user: Boolean) = ProfileFragment().apply {
            val args = Bundle()
            param1?.let { args.putString(ARG_PARAM1, it) }
            args.putBoolean(ARG_PARAM2, other_user)
            this.arguments = args
        }
    }
}
