package org.wikiedufoundation.wikiedudashboard.ui.dashboard.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.repository.DashboardRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

/**
* Class extends AndroidViewModel and requires application as a parameter.
*/
class DashboardViewModel(dashboardRepository: DashboardRepository, cookies:String): ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage?> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage?> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar
    var _courselist: MutableLiveData<List<CourseListData>> = MutableLiveData()
    val courselist: LiveData<List<CourseListData>> get() = _courselist

    val _courseList: MutableLiveData<List<CourseListData>> = MutableLiveData()
    val courseList: MutableLiveData<List<CourseListData>> get() = _courseList


    /**  The implementation of insert() is completely hidden from the UI.
     *  We don't want insert to block the main thread, so we're launching a new
     *  coroutine. ViewModels have a coroutine scope based on their lifecycle called
     *  viewModelScope which we can use here.
     **/

    init {
        viewModelScope.launch {
            try {
                _progressbar.postValue(true)
                _courseList.postValue(dashboardRepository.getDashboardDetail(cookies))
                _progressbar.postValue(false)

            } catch (e: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)
            }
        }
    }
}