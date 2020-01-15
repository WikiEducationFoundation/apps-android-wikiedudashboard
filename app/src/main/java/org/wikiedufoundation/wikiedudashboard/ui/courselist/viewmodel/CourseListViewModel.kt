package org.wikiedufoundation.wikiedudashboard.ui.courselist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import org.wikiedufoundation.wikiedudashboard.ui.courselist.repository.CourseListRepository
import java.io.IOException

/**
 * Class extends AndroidViewModel and requires application as a parameter.
 */

class CourseListViewModel(private val repository: CourseListRepository) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage?> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage?> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar
    val data = repository.allCourseList
    init {
        _progressbar.postValue(false)
    }

    /**  The implementation of insert() is completely hidden from the UI.
     *  We don't want insert to block the main thread, so we're launching a new
     *  coroutine. ViewModels have a coroutine scope based on their lifecycle called
     *  viewModelScope which we can use here.
     **/

    fun fetchCourseList(cookies: String) {
        viewModelScope.launch {
            try {

                repository.getCourseList(cookies)
            } catch (e: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
            }
        }
    }
}