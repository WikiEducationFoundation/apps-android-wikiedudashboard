package org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.data.CampaignDetails
import org.wikiedufoundation.wikiedudashboard.ui.campaigndetails.home.repsoitory.CampaignDetailRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

class CampaignHomeViewModel(private val campaignDetailRepository: CampaignDetailRepository) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar
    private val _campaignDetail: MutableLiveData<CampaignDetails> = MutableLiveData()
    val campaignDetail: LiveData<CampaignDetails> get() = _campaignDetail

    init {
        _progressbar.postValue(true)
    }

    /**
     * ViewModels have a coroutine scope based on their lifecycle called
     *  viewModelScope which we can use here.
     **/
    fun requestCampaignDetail(url: String) {
        viewModelScope.launch {
            try {
                _campaignDetail.postValue(campaignDetailRepository.requestCampaignDetails(url))
                _progressbar.postValue(false)
            } catch (io: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)
            }
        }
    }
}
