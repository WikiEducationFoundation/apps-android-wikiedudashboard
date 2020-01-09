package org.wikiedufoundation.wikiedudashboard.ui.campaign.viewmodel

import androidx.lifecycle.*
import com.bumptech.glide.Glide.init
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import org.wikiedufoundation.wikiedudashboard.ui.campaign.repository.ActiveCampaignRepository
import org.wikiedufoundation.wikiedudashboard.ui.campaign.repository.CourseListRepository
import timber.log.Timber
import java.io.IOException

/**
 * Class extends AndroidViewModel and requires application as a parameter.
 */

class ActiveCampaignViewModel(private val activeCampaignRepository: ActiveCampaignRepository, cookies: String) : ViewModel() {

    private val _showMsg: MutableLiveData<String> = MutableLiveData()
    val showMsg: MutableLiveData<String> get() = _showMsg

    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar

    val data = activeCampaignRepository.allCampaignList

    init {
        _showMsg.value = "Unable to connect to server"
        _progressbar.postValue(false)

    }

    /**  The implementation of insert() is completely hidden from the UI.
     *  We don't want insert to block the main thread, so we're launching a new
     *  coroutine. ViewModels have a coroutine scope based on their lifecycle called
     *  viewModelScope which we can use here.
     **/


     fun fetchCampaignList(cookies: String){
        viewModelScope.launch {
            try {
                _progressbar.postValue(false)
                activeCampaignRepository.getCampaignList(cookies)
            } catch (e: Throwable) {
                Timber.e(e.message.toString())
            }
        }

    }
}