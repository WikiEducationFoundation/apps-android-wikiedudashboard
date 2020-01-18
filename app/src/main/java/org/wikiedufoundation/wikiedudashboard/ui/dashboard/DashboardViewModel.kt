package org.wikiedufoundation.wikiedudashboard.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.courselist.data.CourseListData
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.MyDashboardResponse
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.data.UserData
import org.wikiedufoundation.wikiedudashboard.ui.dashboard.repository.DashboardRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

class DashboardViewModel(private val dashboardRepository: DashboardRepository, private val cookies: String) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage?> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage?> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar
    val _user: MutableLiveData<List<CourseListData>> = MutableLiveData()
    val user: LiveData<List<CourseListData>> get() = _user

    val data = dashboardRepository.allCourseList


    init {

        _progressbar.postValue(false)

        /**  The implementation of insert() is completely hidden from the UI.
         *  We don't want insert to block the main thread, so we're launching a new
         *  coroutine. ViewModels have a coroutine scope based on their lifecycle called
         *  viewModelScope which we can use here.
         **/
        viewModelScope.launch {
            try {

                dashboardRepository.getDashboardDetail(cookies)
            } catch (e: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
            }
        }
    }



}