package org.wikiedufoundation.wikiedudashboard.data.localDatabse.Viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.data.localDatabse.Repository.ActiveCampaignRepository
import org.wikiedufoundation.wikiedudashboard.data.localDatabse.WikiDatabase
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData

/**
 * Class extends AndroidViewModel and requires application as a parameter.
 */

class ActiveCampaignViewModel(application: Application) : AndroidViewModel(application) {

    /**
     *   The ViewModel maintains a reference to the repository to get data.
     */
    private val repository: ActiveCampaignRepository

    /**
     *   LiveData gives us updated campaign when they change.
     */

    val allCampaignList: LiveData<List<CampaignListData>>

    init {
        /** Gets reference to activeCampaignDao from WordRoomDatabase to construct
         *  the correct WordRepository.
         * */
        val activeCampaignDao = WikiDatabase.database(application, viewModelScope).activeCampaignDao()
        repository = ActiveCampaignRepository(activeCampaignDao)
        allCampaignList = repository.allCampaignList
    }


   /**  The implementation of insert() is completely hidden from the UI.
     *  We don't want insert to block the main thread, so we're launching a new
     *  coroutine. ViewModels have a coroutine scope based on their lifecycle called
     *  viewModelScope which we can use here.
    **/

    fun insert(campaignList: CampaignListData) = viewModelScope.launch {
        repository.insert(campaignList)
    }

    fun delete() = viewModelScope.launch{
        repository.delete()
    }


}