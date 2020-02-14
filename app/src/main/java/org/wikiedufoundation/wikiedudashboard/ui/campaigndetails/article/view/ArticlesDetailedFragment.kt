package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.databinding.FragmentArticlesDetailedBinding
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.data.Articles

class ArticlesDetailedFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentArticlesDetailedBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_articles_detailed, container, false)
        arguments?.let { bundle ->
            bundle.getParcelable<Articles>("article")
                    ?.let {
                        binding.textViewTitle.text = it.title
                        binding.textViewCountCharactersAdded.text = it.characters_added
                        binding.textViewCountReferencesAdded.text = it.references_added
                        binding.textViewViewsCount.text = it.views
                        binding.textViewCourseList.text = it.courses
                    }
        }

        return binding.root
    }
}
