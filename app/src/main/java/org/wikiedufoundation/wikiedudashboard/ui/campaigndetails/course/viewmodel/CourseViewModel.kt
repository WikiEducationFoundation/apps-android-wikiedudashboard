package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.data.Course
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.course.repsoitory.CourseRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException
/**
 * CourseViewModel takes in parameters courseRepository and url.
 ***/
class CourseViewModel(private val courseRepository: CourseRepository, private val url: String) :
        ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar
    private val _courseList: MutableLiveData<List<Course>> = MutableLiveData()
    val courseList: LiveData<List<Course>> get() = _courseList

    init {
        viewModelScope.launch {
            try {
                _progressbar.postValue(true)
//                TODO("Uncomment when the API is ready to avoid HTTP 404 error crashing")
//                _courseList.postValue(courseRepository.requestCourse(url))
                _progressbar.postValue(false)
            } catch (io: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)
            }
        }
    }
}
