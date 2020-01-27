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
class RecentActivityViewModel(private val recentActivityRepository: RecentActivityRepository, private val url: String) : ViewModel(){

    private val _showMsg: MutableLiveData<ShowMessage?> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage?> get() = _showMsg

    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar

    private val _recentList = MutableLiveData<List<RecentActivity>>()
    val recentList : LiveData<List<RecentActivity>>get() = _recentList

    /**  The implementation of insert() is completely hidden from the UI.
     *  We don't want insert to block the main thread, so we're launching a new
     *  coroutine. ViewModels have a coroutine scope based on their lifecycle called
     *  viewModelScope which we can use here.
     **/
    init {
        _progressbar.postValue(false)

        viewModelScope.launch {
            try {
                _recentList.postValue(recentActivityRepository.getAllRecentActivities())
            }catch (io : IOException){
                _showMsg.postValue(ShowMessage("Unable to connect to server"))
            }
        }

        viewModelScope.launch {
            try {
                recentActivityRepository.insertRecentActivity(url)
            }catch (e : IOException){
                _showMsg.postValue(ShowMessage("Unable to connect to server"))
            }
        }
    }


}