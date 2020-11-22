package org.wikiedufoundation.wikiedudashboard.ui.campaign.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.campaign.repository.ActiveCampaignRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

/**
 * Class extends ViewModel and requires activeCampaignRepository and cookies as a parameter.
 */

class ActiveCampaignViewModel(private val activeCampaignRepository: ActiveCampaignRepository, cookies: String) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage?> = MutableLiveData()
    val showMsg: LiveData<ShowMessage?> get() = _showMsg

    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar

    val data = activeCampaignRepository.allCapaignList()

    init {
        _progressbar.postValue(true)

        viewModelScope.launch {
            try {

                activeCampaignRepository.requestCampaignList(cookies)
                _progressbar.postValue(false)
            } catch (e: IOException) {
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)
            }
        }
    }
}
