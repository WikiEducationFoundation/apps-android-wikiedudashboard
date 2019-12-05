package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile_stats.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.AsInstructorDetails
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.AsStudentDetails
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ByStudentsDetails
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val ARG_PARAM3 = "param3"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [ProfileStatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProfileStatsFragment : Fragment() {

    private var profileResponse: ProfileResponse? = null
    private var username: String? = null
    private var otherUser: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            otherUser = it.getBoolean(ARG_PARAM3)
            username = it.getString(ARG_PARAM2)
            profileResponse = it.getSerializable(ARG_PARAM1) as? ProfileResponse
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_profile_stats, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profileResponse?.asInstructor?.let {
            val asInstructorDetails: AsInstructorDetails = it
        }

        profileResponse?.asStudent?.let {
            val asStudentDetails: AsStudentDetails = it
            val text = "Total impact made by $username's students"
            tvTitleImpactByStudent.text = text
            tvCountArticlesCreated.text = asStudentDetails.individualArticlesCreated
            tvCountArticlesEdited.text = asStudentDetails.individualArticleViews
            tvCountTotalEdits.text = asStudentDetails.individualArticleCount
            tvCountStudentEditors.text = asStudentDetails.individualUploadCount
            tvCountWordsAdded.text = asStudentDetails.individualWordCount
            tvCountArticleViews.text = asStudentDetails.individualArticleViews
            tvCountCommonsUploads.text = asStudentDetails.individualUploadCount
        } ?: run {
            llAsStudent.visibility = GONE
            llNotEnrolled.visibility = VISIBLE
        }

        profileResponse?.byStudents?.let {
            val byStudentDetails: ByStudentsDetails = it
            val text : String = "Total impact made by " + username +"as a student"
            tvTitleImpactByStudent.text = text
            tvinstructorCountWordsAdded.text = byStudentDetails.wordCount
            tvInstructorCountReferencesAdded.text = byStudentDetails.referencesCount
            tvInstructorCountArticleViews.text = byStudentDetails.viewSum
            tvInstructorCountArticlesCreated.text = byStudentDetails.newArticleCount
            tvInstructorCountArticlesEdited.text = byStudentDetails.articleCount
            tvInstructorCountCommonsUpload.text = byStudentDetails.uploadCount
        } ?: run {
            llByStudent.visibility = GONE
            llNotEnrolled.visibility = VISIBLE
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment ProfileStatsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: ProfileResponse?, param2: String?, param3: Boolean?) =
                ProfileStatsFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)
                        param2?.let { putString(ARG_PARAM2, it) }
                        param3?.let { putBoolean(ARG_PARAM3, it) }
                    }
                }
    }
}
