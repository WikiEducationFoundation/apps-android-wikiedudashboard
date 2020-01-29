package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.data.CourseDetail
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.common.repository.CourseDetailRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

/**
 * Class extends ViewModel and requires CourseDetailRepository as a parameter.
 */
class CourseDetailViewModel(private val courseDetailRepository: CourseDetailRepository) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar
    private val _coursedetail: MutableLiveData<CourseDetail> = MutableLiveData()
    val coursedetail: LiveData<CourseDetail> get() = _coursedetail

    init {
        _progressbar.postValue(true)
    }

    fun requestCourseDetail(url : String) {
        viewModelScope.launch {
            try {
                _coursedetail.postValue(courseDetailRepository.requestCourseDetails(url))
                _progressbar.postValue(false)

            } catch (io: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)

            }
        }
    }
}