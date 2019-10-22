package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.ArticlesEditedRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.presenter.ArticlesEditedPresenter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider.RetrofitArticlesEditedProvider
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A [Fragment] subclass for edited course articles
 * ***/
class CourseArticlesEditedFragment : Fragment(), ArticlesEditedView {

    private val retrofitArticlesEditedProvider: RetrofitArticlesEditedProvider by inject()
    private val articlesEditedPresenter: ArticlesEditedPresenter by inject {
        parametersOf(this, retrofitArticlesEditedProvider)
    }

    private lateinit var tvNoEditedArticles: TextView
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView

    private lateinit var articlesEditedRecyclerAdapter: ArticlesEditedRecyclerAdapter

    private var url: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_articles_edited, container, false)

        url = arguments?.getString("url", null)

        recyclerView = view.findViewById(R.id.rv_edited_articles_list)
        progressBar = view.findViewById(R.id.progress_bar)
        tvNoEditedArticles = view.findViewById(R.id.tv_no_edited_articles)

        articlesEditedRecyclerAdapter = ArticlesEditedRecyclerAdapter(R.layout.item_rv_articles_edited)

        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = articlesEditedRecyclerAdapter
        }

        url?.let { articlesEditedPresenter.requestArticlesEdited(it) }
        return view
    }

    override fun setData(data: ArticlesEdited) {
        Timber.d(data.toString())
        if (data.course.articles.isNotEmpty()) {
            recyclerView.visibility = View.VISIBLE
            articlesEditedRecyclerAdapter.setData(data.course.articles)
            articlesEditedRecyclerAdapter.notifyDataSetChanged()
            tvNoEditedArticles.visibility = View.GONE
        } else {
            recyclerView.visibility = View.GONE
            tvNoEditedArticles.visibility = View.VISIBLE
        }
    }

    override fun showProgressBar(show: Boolean) {
        progressBar.visibility = if (show) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    override fun showMessage(message: String) {
        context?.showToast(message)
    }
}
