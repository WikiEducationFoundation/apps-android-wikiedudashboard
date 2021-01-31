package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.data.CourseUpload
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.uploads.repository.CourseUploadsRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import retrofit2.HttpException
import java.io.IOException

/**
 * Class extends ViewModel and requires courseUploadsRepository and url as a parameter.
 */
class CourseUploadsViewModel(
    private val courseUploadsRepository: CourseUploadsRepository,
    private val url: String
) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar
    private val _uploadList: MutableLiveData<List<CourseUpload>> = MutableLiveData()
    val uploadList: LiveData<List<CourseUpload>> get() = _uploadList

    init {
        viewModelScope.launch {
            _progressbar.postValue(true)
            try {
                _uploadList.postValue(courseUploadsRepository.requestCourseUploads(url))
                _progressbar.postValue(false)
            } catch (e: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)
            } catch (e: HttpException) {
                _showMsg.postValue(ShowMessage(e.message()))
                _progressbar.postValue(false)
            }
        }
    }
}
