package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.data.Students
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.student.repsoitory.StudentRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

/**
 * Class extends ViewModel and requires StudentRepository as a parameter.
 */
class StudentViewModel(private val studentRepository: StudentRepository, private val url: String)
    : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar

    private val _studentList: MutableLiveData<List<Students>> = MutableLiveData()
    val studentList: LiveData<List<Students>> get() = _studentList

    private val _studentDetail: MutableLiveData<Students> = MutableLiveData()
    val studentDetail: LiveData<Students> get() = _studentDetail

    init {
        _progressbar.postValue(true)

        viewModelScope.launch {
            try {
                _studentList.postValue(studentRepository.requestStudent(url))
                _progressbar.postValue(false)
            } catch (io: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)
            }
        }
    }
}
