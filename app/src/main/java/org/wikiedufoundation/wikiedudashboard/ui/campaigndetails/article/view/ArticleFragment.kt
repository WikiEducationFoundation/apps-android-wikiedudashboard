package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.article_fragment.*
import kotlinx.android.synthetic.main.fragment_explore_students.*
import kotlinx.android.synthetic.main.fragment_explore_students.progressBar
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.adapters.ArticlesRecyclerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.data.ArticleDataSource
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.data.Articles
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.viewmodel.ArticleViewModel
import org.wikiedufoundation.wikiedudashboard.util.showToast
import timber.log.Timber

class ArticleFragment : Fragment() {
    private lateinit var url: String
    private lateinit var ariclesRecyclerAdapter: ArticlesRecyclerAdapter
    private val articlesViewModel by viewModel<ArticleViewModel> { parametersOf(url) }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.article_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        url = arguments?.getString("url", null).toString()

        ariclesRecyclerAdapter = ArticlesRecyclerAdapter(R.layout.item_rv_articles) { openArticleDetail(it) }
        initializeRecyclerView()
        setData()
        initializeProgressBar()
        initializeToaster()
    }

    private fun initializeRecyclerView() {
        recyclerArticleList.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = ariclesRecyclerAdapter
        }
    }

    private fun initializeProgressBar() {
        articlesViewModel.progressbar.observe(this, androidx.lifecycle.Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun initializeToaster() {
        articlesViewModel.showMsg.observe(this, androidx.lifecycle.Observer {
            val message = it.showMsg
            context?.showToast(message)
        })
    }

    /* TODO This method should be uncommented when the api is ready*/
//    private fun setData() {
//        articlesViewModel.articleList.observe(this, Observer {
//            if (it.isNotEmpty()) {
//                Timber.d(it.toString())
//                ariclesRecyclerAdapter.setData(it)
//            } else {
//                recyclerArticleList?.visibility = View.GONE
//                textViewNoStudents?.visibility = View.VISIBLE
//            }
//        })
//    }

    /*TODO this method will be deleted when the api is ready. This will get the dummy data*/
    private fun setData() {
        val it = ArticleDataSource.getArticles()
            if (it.isNotEmpty()) {
                Timber.d(it.toString())
                ariclesRecyclerAdapter.setData(it)
            } else {
                recyclerArticleList?.visibility = View.GONE
                textViewNoStudents?.visibility = View.VISIBLE
            }
    }

    private fun openArticleDetail(article: Articles) {
        val articlesDetailedFragment = ArticlesDetailedFragment()
        val bundle = Bundle()
        bundle.putParcelable("article", article)
        articlesDetailedFragment.arguments = bundle
        articlesDetailedFragment.show(parentFragmentManager, "articlefragment")

        context?.showToast(article.title)
    }
}
