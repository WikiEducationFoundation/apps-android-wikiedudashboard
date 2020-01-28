package org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.data.User
import org.wikiedufoundation.wikiedudashboard.ui.coursedetail.students.repository.StudentsRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

class StudentsViewModel(private val studentsRepository : StudentsRepository, url : String) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar

    private val _studentList : MutableLiveData<List<User>> = MutableLiveData()
    val studentList : LiveData<List<User>> get() = _studentList

    init {
        _progressbar.postValue(false)

        viewModelScope.launch {
            try {
                _studentList.postValue(studentsRepository.requestStudentList(url))
            }catch (io : IOException){
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
            }
        }
    }


}