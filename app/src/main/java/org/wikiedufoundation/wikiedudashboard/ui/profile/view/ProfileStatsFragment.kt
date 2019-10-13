package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.annotation.SuppressLint
import org.wikiedufoundation.wikiedudashboard.R

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_profile_stats.*
import kotlinx.android.synthetic.main.fragment_profile_stats.view.*
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
                              savedInstanceState: Bundle?): View?  = inflater.inflate(R.layout.fragment_profile_stats, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (profileResponse?.as_instructor != null) {
            val asInstructorDetails: AsInstructorDetails? = profileResponse?.as_instructor
        } else {

        }

        if (profileResponse?.as_student != null) {
            val asStudentDetails: AsStudentDetails = profileResponse!!.as_student!!
            val text : String = "Total impact made by " + username +"'s students"
            tv_title_impact_as_student.text = text
            tv_count_articles_created.text = asStudentDetails.individual_articles_created
            tv_count_articles_edited.text = asStudentDetails.individual_article_views
            tv_count_total_edits.text = asStudentDetails.individual_article_count
            tv_count_student_editors.text = asStudentDetails.individual_upload_count
            tv_count_words_added.text = asStudentDetails.individual_word_count
            tv_count_article_views.text = asStudentDetails.individual_article_views
            tv_count_commons_uploads.text = asStudentDetails.individual_upload_count
        } else {
            setViewsToShow(tv_title_impact_as_student,
                    tv_count_articles_created,
                    tv_count_articles_created_text,
                    tv_count_articles_edited,
                    tv_count_articles_edited_text,
                    tv_count_total_edits,
                    tv_count_total_edits_text,
                    tv_count_student_editors,
                    tv_count_student_editors_text,
                    tv_count_words_added,
                    tv_count_words_added_text,
                    tv_count_article_views,
                    tv_count_article_views_text,
                    tv_count_commons_uploads,
                    tv_count_commons_uploads_text)

            setViewsToHide(
                    tv_title_impact_by_student,
                    tvInstructorCountWordsAdded,
                    tvInstructorCountWordsAddedTitle,
                    tv_instructor_count_references_added,
                    tv_instructor_count_references_added_title,
                    tv_instructor_count_article_views,
                    tv_instructor_count_article_views_title,
                    tv_instructor_count_articles_edited,
                    tv_instructor_count_articles_edited_text,
                    tv_instructor_count_articles_created,
                    tv_instructor_count_articles_created_title,
                    tv_instructor_count_commons_upload,
                    tv_instructor_count_commons_upload_text)
        }

        if (profileResponse?.by_students != null) {
            val byStudentDetails: ByStudentsDetails = profileResponse!!.by_students!!
            val text : String = "Total impact made by " + username +"as a student"
            tv_title_impact_by_student.text = text
            tvInstructorCountWordsAdded.text = byStudentDetails.word_count
            tv_instructor_count_references_added.text = byStudentDetails.references_count
            tv_instructor_count_article_views.text = byStudentDetails.view_sum
            tv_instructor_count_articles_created.text = byStudentDetails.new_article_count
            tv_instructor_count_articles_edited.text = byStudentDetails.article_count
            tv_instructor_count_commons_upload.text = byStudentDetails.upload_count
        } else {
            setViewsToShow(
                    tv_title_impact_by_student,
                    tvInstructorCountWordsAdded,
                    tvInstructorCountWordsAddedTitle,
                    tv_instructor_count_references_added,
                    tv_instructor_count_references_added_title,
                    tv_instructor_count_article_views,
                    tv_instructor_count_article_views_title,
                    tv_instructor_count_articles_edited,
                    tv_instructor_count_articles_edited_text,
                    tv_instructor_count_articles_created,
                    tv_instructor_count_articles_created_title,
                    tv_instructor_count_commons_upload,
                    tv_instructor_count_commons_upload_text)

            setViewsToHide(tv_title_impact_as_student,
                    tv_count_articles_created,
                    tv_count_articles_created_text,
                    tv_count_articles_edited,
                    tv_count_articles_edited_text,
                    tv_count_total_edits,
                    tv_count_total_edits_text,
                    tv_count_student_editors,
                    tv_count_student_editors_text,
                    tv_count_words_added,
                    tv_count_words_added_text,
                    tv_count_article_views,
                    tv_count_article_views_text,
                    tv_count_commons_uploads,
                    tv_count_commons_uploads_text)
        }
    }


    fun setViewsToHide(vararg views: View) {
        views.forEach {
            it.visibility = View.GONE
        }
    }

    fun setViewsToShow(vararg views: View) {
        views.forEach {
            it.visibility = View.VISIBLE
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
        fun newInstance(param1: ProfileResponse, param2: String, param3: Boolean) =
                ProfileStatsFragment().apply {
                    arguments = Bundle().apply {
                        putSerializable(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                        putBoolean(ARG_PARAM3, param3)
                    }
                }
    }
}
