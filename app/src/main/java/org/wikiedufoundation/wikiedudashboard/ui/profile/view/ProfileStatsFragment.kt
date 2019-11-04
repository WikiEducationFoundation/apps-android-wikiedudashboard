package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_profile_stats.view.*
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
            profileResponse = it.getSerializable(ARG_PARAM1) as ProfileResponse
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_profile_stats, container, false)

        val tvInstructorCountWordsAdded: TextView = view.tv_instructor_count_words_added
        val tvInstructorCountReferencesAdded: TextView = view.tv_instructor_count_references_added
        val tvInstructorCountArticleViews: TextView = view.tv_instructor_count_article_views
        val tvInstructorCountArticlesCreated: TextView = view.tv_instructor_count_articles_created
        val tvInstructorCountArticlesEdited: TextView = view.tv_instructor_count_articles_edited
        val tvInstructorCountCommonsUpload: TextView = view.tv_instructor_count_commons_upload

        val tvCountArticlesCreated: TextView = view.tv_count_articles_created
        val tvCountArticlesEdited: TextView = view.tv_count_articles_edited
        val tvCountTotalEdits: TextView = view.tv_count_total_edits
        val tvCountStudentEditors: TextView = view.tv_count_student_editors
        val tvCountWordsAdded: TextView = view.tv_count_words_added
        val tvCountArticleViews: TextView = view.tv_count_article_views
        val tvCountCommonsUploads: TextView = view.tv_count_commons_uploads

        val clAsStudent: ConstraintLayout = view.cl_as_student
        val clByStudent: ConstraintLayout = view.cl_by_student
        val tvNotEnrolled: TextView = view.tv_not_enrolled
        val tvTitleImpactByStudents: TextView = view.tv_title_impact_by_student
        val tvTitleImpactAsStudents: TextView = view.tv_title_impact_as_student

        profileResponse?.asInstructor?.let {
            val asInstructorDetails: AsInstructorDetails = it
        }

        profileResponse?.asStudent?.let {
            val asStudentDetails: AsStudentDetails = it
            val text: String = "Total impact made by $username's students"
            tvTitleImpactByStudents.text = text
            tvCountArticlesCreated.text = asStudentDetails.individualArticlesCreated
            tvCountArticlesEdited.text = asStudentDetails.individualArticleViews
            tvCountTotalEdits.text = asStudentDetails.individualArticleCount
            tvCountStudentEditors.text = asStudentDetails.individualUploadCount
            tvCountWordsAdded.text = asStudentDetails.individualWordCount
            tvCountArticleViews.text = asStudentDetails.individualArticleViews
            tvCountCommonsUploads.text = asStudentDetails.individualUploadCount
        } ?: run {
            clAsStudent.visibility = GONE
            tvNotEnrolled.visibility = VISIBLE
        }

        profileResponse?.byStudents?.let {
            val byStudentDetails: ByStudentsDetails = it
            val text : String = "Total impact made by " + username +"as a student"
            tvTitleImpactByStudents.text = text
            tvInstructorCountWordsAdded.text = byStudentDetails.wordCount
            tvInstructorCountReferencesAdded.text = byStudentDetails.referencesCount
            tvInstructorCountArticleViews.text = byStudentDetails.viewSum
            tvInstructorCountArticlesCreated.text = byStudentDetails.newArticleCount
            tvInstructorCountArticlesEdited.text = byStudentDetails.articleCount
            tvInstructorCountCommonsUpload.text = byStudentDetails.uploadCount
        } ?: run {
            clByStudent.visibility = GONE
            tvNotEnrolled.visibility = VISIBLE
        }

        return view
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
        fun newInstance(param1: ProfileResponse, param2: String?, param3: Boolean?) =
                ProfileStatsFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)
                        param2?.let { putString(ARG_PARAM2, it) }
                        param3?.let { putBoolean(ARG_PARAM3, it) }
                    }
                }
    }
}
