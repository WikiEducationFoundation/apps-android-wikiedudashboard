package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.campaign_home_fragment.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.data.CampaignDetails
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.view.home.CourseHomeFragment
import java.text.SimpleDateFormat
import java.util.Locale

class CampaignHomeFragment : Fragment() {

    private var campaignDetail: CampaignDetails? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? =
            inflater.inflate(R.layout.campaign_home_fragment, container, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            campaignDetail = it.getSerializable(ARG_PARAM1) as CampaignDetails
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()
    }

    private fun setData() {
        textViewCountCourses.text = campaignDetail?.course_count
        textViewCountArticlesCreated.text = campaignDetail?.created_count
        textViewCountArticlesEdited.text = campaignDetail?.edited_count
        textViewCountWordsAdded.text = campaignDetail?.word_count
        textViewCountReferencesAdded.text = campaignDetail?.reference_count
        textViewCountArticleViews.text = campaignDetail?.views_count
        textViewCountstudents.text = campaignDetail?.student_count
        textViewCountCommonsUploads.text = campaignDetail?.uplaod_count
        textViewCampaignTitle.text = campaignDetail?.title
        textViewCampaignDescription.text = campaignDetail?.description
        textViewCreationDateDetail.text = campaignDetail?.creation_date
    }

    companion object {
        private const val ARG_PARAM1 = "param1"

        /**
         * This method creates a new instance of [CourseHomeFragment]
         */
        fun newInstance(campaignDetail: CampaignDetails?) = CampaignHomeFragment().apply {
            val args = Bundle()
            args.putSerializable(ARG_PARAM1, campaignDetail)
            this.arguments = args
        }
    }

    /**
     * This method converts date to readable format
     * using the [SimpleDateFormat] class
     */
    fun readableDate(realDate: String?): CharSequence? {
        val pattern = "EEE d MMM  yyyy"
        val simpleDateFormat = SimpleDateFormat(pattern, Locale.getDefault())
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.getDefault())
        val date = inputFormat.parse(realDate)
        return simpleDateFormat.format(date)
    }
}
