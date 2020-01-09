package org.wikiedufoundation.wikiedudashboard.ui.campaign.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.campaign.dao.ActiveCampaignDao
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.Response
import timber.log.Timber

/**Declares the DAO as a private property in the constructor. Pass in the DAO
*instead of the whole database, because you only need access to the DAO*
 * */
class ActiveCampaignRepository(private val wikiEduDashboardApi: WikiEduDashboardApi,
                               private val activeCampaignDao: ActiveCampaignDao){

    private lateinit var campaignList : List<CampaignListData>


    /** Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     * */
    val allCampaignList : LiveData<List<CampaignListData>> = activeCampaignDao.getAllCampaign()


    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    suspend fun getCampaignList(cookies: String){
            withContext(Dispatchers.Main) {
                try {
                    val request = wikiEduDashboardApi.getExploreCampaigns(cookies)
                        Response.showProgress(false)
                        val mExploreCampaign = request.await()
                        campaignList = mExploreCampaign.campaigns
                        activeCampaignDao.insertCampaign(campaignList)
                } catch (e: Exception) {
                    Timber.e(e.message.toString())
                } catch (e: Throwable) {
                    Timber.e(e.message.toString())
                }
            }
    }
}