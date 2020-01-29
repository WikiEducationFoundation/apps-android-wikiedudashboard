package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.data.Article
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.articlesedited.repository.ArticlesEditedRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

/**
 * Class extends ViewModel and requires ArticlesEditedRepository as a parameter.
 */
class ArticlesEditedViewModel(
    private val articlesEditedRepository: ArticlesEditedRepository,
    private val url: String
) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar

    private val _articleList: MutableLiveData<List<Article>> = MutableLiveData()
    val articleList: LiveData<List<Article>> get() = _articleList

    init {
        _progressbar.postValue(false)

        viewModelScope.launch {
            try {
                _articleList.postValue(articlesEditedRepository.requestArticlesEdited(url))
            } catch (io: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to the server."))
            }
        }
    }
}