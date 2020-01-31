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
 * Class extends ViewModel and requires dashboardRepository and cookies as a parameter.
 */
class DashboardViewModel(dashboardRepository: DashboardRepository, cookies: String) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage?> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage?> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar
    private val _courseList: MutableLiveData<List<CourseListData>> = MutableLiveData()
    val courseList: LiveData<List<CourseListData>> get() = _courseList

    init {
        viewModelScope.launch {
            try {
                _progressbar.postValue(true)
                _courseList.postValue(dashboardRepository.requestDashboardDetail(cookies))
                _progressbar.postValue(false)
            } catch (e: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)
            }
        }
    }
}