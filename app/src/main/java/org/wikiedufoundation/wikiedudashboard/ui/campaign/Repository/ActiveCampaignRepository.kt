package org.wikiedufoundation.wikiedudashboard.ui.campaign.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.wikiedufoundation.wikiedudashboard.ui.campaign.dao.ActiveCampaignDao
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import timber.log.Timber

/**Declares the DAO as a private property in the constructor. Pass in the DAO
*instead of the whole database, because you only need access to the DAO*
 * */
class ActiveCampaignRepository(private val wikiEduDashboardApi: WikiEduDashboardApi,
                               private val activeCampaignDao: ActiveCampaignDao){

    private var campaignList = mutableListOf<CampaignListData>()
    private var campaignListLiveData = MutableLiveData<List<CampaignListData>>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)


    /** Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     * */
    val allCampaignList : LiveData<List<CampaignListData>> = activeCampaignDao.getAllCampaign()



    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/

    suspend fun getCampaignListLiveData(cookies: String){
        coroutineScope.launch {
            val request = wikiEduDashboardApi.getExploreCampaigns(cookies)
            withContext(Dispatchers.Main) {
                try {
                    val mExploreCampaign = request.await()
                    campaignList = mExploreCampaign.campaigns
                    campaignListLiveData.value=campaignList;
                    activeCampaignDao.insertCampaign(campaignList)
                } catch (e: Exception) {
                    Timber.e(e.message.toString())
                } catch (e: Throwable) {
                    Timber.e(e.message.toString())
                }
            }
        }
    }
}




