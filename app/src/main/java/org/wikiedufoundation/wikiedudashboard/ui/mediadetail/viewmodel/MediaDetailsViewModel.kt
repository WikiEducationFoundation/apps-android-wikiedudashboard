package org.wikiedufoundation.wikiedudashboard.ui.mediadetail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.data.Query
import org.wikiedufoundation.wikiedudashboard.ui.mediadetail.repository.MediaDetailsRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

/**
 * Class extends ViewModel and requires courseUploadsRepository and url as a parameter.
 */
class MediaDetailsViewModel(private val mediaDetailsRepository: MediaDetailsRepository,
                            private val cookies : String) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar
    private val _mediaDetails: MutableLiveData<Query> = MutableLiveData()
    val mediaDetails : LiveData<Query> get() = _mediaDetails


    init {

        viewModelScope.launch {
            try {
                _progressbar.postValue(true)
               _mediaDetails.postValue(mediaDetailsRepository.requestMediaDetails(cookies))
                _progressbar.postValue(false)

            }catch (io : IOException){
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)

            }
        }
    }
}