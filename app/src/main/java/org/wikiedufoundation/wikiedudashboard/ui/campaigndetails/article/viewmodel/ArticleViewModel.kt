package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.data.Articles
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.article.repsoitory.ArticleRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

class ArticleViewModel(private val articleRepository: ArticleRepository, private val url: String)
    : ViewModel() {
    private val _showMsg: MutableLiveData<ShowMessage> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar

    private val _articleList: MutableLiveData<List<Articles>> = MutableLiveData()
    val articleList: LiveData<List<Articles>> get() = _articleList

    init {
        _progressbar.postValue(true)

        viewModelScope.launch {
            try {
                /*TODO the below line will be uncommented when the API is ready*/
//                _articleList.postValue(articleRepository.requestArticles(url))
                _progressbar.postValue(false)
            } catch (io: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)
            }
        }
    } }
