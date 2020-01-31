package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.data.RecentActivity
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.recentactivity.repository.RecentActivityRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

/**
 * Class extends ViewModel and requires RecentActivityRepository as a parameter.
 */
class RecentActivityViewModel(private val recentActivityRepository: RecentActivityRepository, private val url: String) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage> get() = _showMsg

    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar

    private val _recentList: MutableLiveData<List<RecentActivity>> = MutableLiveData()
    val recentList: LiveData<List<RecentActivity>> get() = _recentList

    init {

        viewModelScope.launch {
            _progressbar.postValue(true)

            try {
                _recentList.postValue(recentActivityRepository.requestRecentActivity(url))
                _progressbar.postValue(false)
            } catch (e: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server"))
                _progressbar.postValue(false)
            }
        }
    }
}