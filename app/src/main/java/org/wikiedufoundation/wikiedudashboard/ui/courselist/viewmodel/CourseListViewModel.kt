package org.wikiedufoundation.wikiedudashboard.ui.courselist.viewmodel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ShowMessge
import org.wikiedufoundation.wikiedudashboard.ui.courselist.repository.CourseListRepository

/**
 * Class extends AndroidViewModel and requires application as a parameter.
 */

class CourseListViewModel(private val repository: CourseListRepository) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessge?> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessge?> get() = _showMsg

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
                repository.getCourseList(cookies)
        }

    }
}