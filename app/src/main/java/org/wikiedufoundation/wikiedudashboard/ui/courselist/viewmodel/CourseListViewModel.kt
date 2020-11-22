package org.wikiedufoundation.wikiedudashboard.ui.courselist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.courselist.repository.CourseListRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

/**
 * Class extends ViewModel and requires CourseListRepository and cookies as a parameter.
 */

class CourseListViewModel(private val repository: CourseListRepository, private val cookies: String) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage?> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage?> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar

    val data = repository.allCourseList()

    init {
        viewModelScope.launch {
            try {
                _progressbar.postValue(true)
                repository.requestCourseList(cookies)
                _progressbar.postValue(false)
            } catch (e: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)
            }
        }
    }
}
