package org.wikiedufoundation.wikiedudashboard.ui.profile.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_profile_stats.*
import androidx.fragment.app.Fragment
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
                              savedInstanceState: Bundle?): View?  = inflater.inflate(R.layout.fragment_profile_stats, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (profileResponse?.asInstructor != null) {
            val asInstructorDetails: AsInstructorDetails? = profileResponse?.asInstructor
        } else {

        }

        if (profileResponse?.asStudent != null) {
            val asStudentDetails: AsStudentDetails = profileResponse!!.asStudent!!
            val text : String = "Total impact made by " + username +"'s students"
            tv_title_impact_as_student.text = text
            tv_count_articles_created.text = asStudentDetails.individualArticlesCreated
            tv_count_articles_edited.text = asStudentDetails.individualArticleViews
            tv_count_total_edits.text = asStudentDetails.individualArticleCount
            tv_count_student_editors.text = asStudentDetails.individualUploadCount
            tv_count_words_added.text = asStudentDetails.individualWordCount
            tv_count_article_views.text = asStudentDetails.individualArticleViews
            tv_count_commons_uploads.text = asStudentDetails.individualUploadCount
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

        if (profileResponse?.byStudents != null) {
            val byStudentDetails: ByStudentsDetails = profileResponse!!.byStudents!!
            val text : String = "Total impact made by " + username +"as a student"
            tv_title_impact_by_student.text = text
            tvInstructorCountWordsAdded.text = byStudentDetails.wordCount
            tv_instructor_count_references_added.text = byStudentDetails.referencesCount
            tv_instructor_count_article_views.text = byStudentDetails.viewSum
            tv_instructor_count_articles_created.text = byStudentDetails.newArticleCount
            tv_instructor_count_articles_edited.text = byStudentDetails.articleCount
            tv_instructor_count_commons_upload.text = byStudentDetails.uploadCount
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
