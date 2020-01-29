package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_articles_edited.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.ArticlesEditedRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.viewmodel.ArticlesEditedViewModel
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A [Fragment] subclass for edited course articles
 * ***/
class CourseArticlesEditedFragment : Fragment() {

    private lateinit var articlesEditedRecyclerAdapter: ArticlesEditedRecyclerAdapter

    private var url: String? = null

    private val articlesEditedViewModel by viewModel<ArticlesEditedViewModel> { parametersOf(url) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_articles_edited, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        url = arguments?.getString("url", null)

        articlesEditedRecyclerAdapter = ArticlesEditedRecyclerAdapter(R.layout.item_rv_articles_edited)

        initializeRecyclerView()
        setData()
        initializeProgressBar()
        initializeToaster()
    }

    private fun initializeRecyclerView() {
        recyclerEditedArticlesList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = articlesEditedRecyclerAdapter
        }
    }

    private fun setData() {
        articlesEditedViewModel.articleList.observe(this, Observer {
            Timber.d(it.toString())
            if (it.isNotEmpty()) {
                recyclerEditedArticlesList?.visibility = View.VISIBLE
                articlesEditedRecyclerAdapter.setData(it)
                textViewNoEditedArticles?.visibility = View.GONE
            } else {
                recyclerEditedArticlesList?.visibility = View.GONE
                textViewNoEditedArticles?.visibility = View.VISIBLE
            }
        })
    }

    private fun initializeProgressBar() {
        articlesEditedViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun initializeToaster() {
        articlesEditedViewModel.showMsg.observe(this, androidx.lifecycle.Observer {
            val message = it.showMsg
            context?.showToast(message)
        })
    }
}
