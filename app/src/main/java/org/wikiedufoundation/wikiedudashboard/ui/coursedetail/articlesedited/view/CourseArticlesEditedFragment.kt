package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.ArticlesEditedRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.ArticlesEdited
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.presenter.ArticlesEditedPresenterImpl
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.provider.RetrofitArticlesEditedProvider
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

/**
 * A [Fragment] subclass for edited course articles
 * ***/
class CourseArticlesEditedFragment : Fragment(), ArticlesEditedView {

    private var tvNoEditedArticles: TextView? = null
    private var progressBar: ProgressBar? = null
    private var recyclerView: RecyclerView? = null

    private var articlesEditedPresenter: ArticlesEditedPresenterImpl? = null
    private var articlesEditedRecyclerAdapter: ArticlesEditedRecyclerAdapter? = null
    private var url: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_articles_edited, container, false)

        url = arguments?.getString("url", null)
        val context: Context? = context
        recyclerView = view.findViewById(R.id.rv_edited_articles_list)
        progressBar = view.findViewById(R.id.progress_bar)
        tvNoEditedArticles = view.findViewById(R.id.tv_no_edited_articles)

        articlesEditedPresenter = ArticlesEditedPresenterImpl(RetrofitArticlesEditedProvider(), this)

        articlesEditedRecyclerAdapter = ArticlesEditedRecyclerAdapter(R.layout.item_rv_articles_edited)

        recyclerView?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = articlesEditedRecyclerAdapter
        }

        url?.let { articlesEditedPresenter?.requestArticlesEdited(it) }
        return view
    }

    override fun setData(data: ArticlesEdited) {
        Timber.d(data.toString())
        if (data.course.articles.size > 0) {
            recyclerView?.visibility = View.VISIBLE
            articlesEditedRecyclerAdapter?.setData(data.course.articles)
            articlesEditedRecyclerAdapter?.notifyDataSetChanged()
            tvNoEditedArticles?.visibility = View.GONE
        } else {
            recyclerView?.visibility = View.GONE
            tvNoEditedArticles?.visibility = View.VISIBLE
        }
    }

    override fun showProgressBar(show: Boolean) {
        if (show) {
            progressBar?.visibility = View.VISIBLE
        } else {
            progressBar?.visibility = View.GONE
        }
    }

    override fun showMessage(message: String) {
        context?.showToast(message)
    }
}
