package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import org.wikiedufoundation.wikiedudashboard.R

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_profile_stats.*
import kotlinx.android.synthetic.main.fragment_profile_stats.view.*
import kotlinx.android.synthetic.main.item_rv_my_dashboard.view.*
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.AsInstructorDetails
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.AsStudentDetails
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ByStudentsDetails
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [ProfileStatsFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProfileStatsFragment : Fragment() {

    private var profileResponse: ProfileResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            profileResponse = it.getSerializable(ARG_PARAM1) as ProfileResponse
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view : View =inflater.inflate(R.layout.fragment_profile_stats, container, false)

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

        val asInstructorDetails: AsInstructorDetails = profileResponse!!.as_instructor!!
        val asStudentDetails : AsStudentDetails = profileResponse!!.as_student!!
        val byStudentDetails : ByStudentsDetails = profileResponse!!.by_students!!

        tvInstructorCountWordsAdded.text = byStudentDetails.word_count
        tvInstructorCountReferencesAdded.text = byStudentDetails.references_count
        tvInstructorCountArticleViews.text = byStudentDetails.view_sum
        tvInstructorCountArticlesCreated.text = byStudentDetails.new_article_count
        tvInstructorCountArticlesEdited.text = byStudentDetails.article_count
        tvInstructorCountCommonsUpload.text = byStudentDetails.upload_count

        tvCountArticlesCreated.text = asStudentDetails.individual_articles_created
        tvCountArticlesEdited.text = asStudentDetails.individual_article_views
        tvCountTotalEdits.text = asStudentDetails.individual_article_count
        tvCountStudentEditors.text = asStudentDetails.individual_upload_count
        tvCountWordsAdded.text = asStudentDetails.individual_word_count
        tvCountArticleViews.text = asStudentDetails.individual_article_views
        tvCountCommonsUploads.text = asStudentDetails.individual_upload_count
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
        fun newInstance(param1: ProfileResponse) =
                ProfileStatsFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)
                    }
                }
    }
}
