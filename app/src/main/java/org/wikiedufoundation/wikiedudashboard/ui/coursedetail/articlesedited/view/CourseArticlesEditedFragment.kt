package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_articles_edited.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.ArticlesEditedRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.presenter.ArticlesEditedPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider.RetrofitArticlesEditedProvider
import org.wikiedufoundation.wikiedudashboard.util.showSnackbar
import timber.log.Timber

/**
 * A [Fragment] subclass for edited course articles
 * ***/
class CourseArticlesEditedFragment : Fragment(), ArticlesEditedView {

    private val retrofitArticlesEditedProvider: RetrofitArticlesEditedProvider by inject()
    private val articlesEditedPresenter: ArticlesEditedPresenter by inject {
        parametersOf(this, retrofitArticlesEditedProvider)
    }

    private lateinit var articlesEditedRecyclerAdapter: ArticlesEditedRecyclerAdapter

    private var url: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_articles_edited, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        url = arguments?.getString("url", null)

        articlesEditedRecyclerAdapter = ArticlesEditedRecyclerAdapter(R.layout.item_rv_articles_edited)

        recyclerEditedArticlesList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = articlesEditedRecyclerAdapter
        }

        url?.let { articlesEditedPresenter.requestArticlesEdited(it) }
    }

    override fun setData(data: ArticlesEdited) {
        Timber.d(data.toString())
        if (data.course.articles.isNotEmpty()) {
            recyclerEditedArticlesList?.visibility = View.VISIBLE
            articlesEditedRecyclerAdapter.setData(data.course.articles)
            articlesEditedRecyclerAdapter.notifyDataSetChanged()
            textViewNoEditedArticles?.visibility = View.GONE
        } else {
            recyclerEditedArticlesList?.visibility = View.GONE
            textViewNoEditedArticles?.visibility = View.VISIBLE
        }
    }

    override fun showProgressBar(show: Boolean) {
        progressBar?.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showMessage(message: String) {
        view?.showSnackbar(message)
    }
}
