package org.wikiedufoundation.wikiedudashboard.ui.campaign.repository

import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.ui.campaign.dao.ActiveCampaignDao
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.ShowMessge
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.lang.Error

/**Declares the DAO as a private property in the constructor. Pass in the DAO
 *instead of the whole database, because you only need access to the DAO*
 * */
class ActiveCampaignRepository(private val wikiEduDashboardApi: WikiEduDashboardApi,
                               private val activeCampaignDao: ActiveCampaignDao) {

    /** Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     * */
    val allCampaignList: LiveData<List<CampaignListData>> = activeCampaignDao.getAllCampaign()


    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/
    suspend fun getCampaignList(cookies: String){
        withContext(Dispatchers.Main) {
            try {
                val request = wikiEduDashboardApi.getExploreCampaigns(cookies).await()
                val campaignList = request.campaigns
                activeCampaignDao.insertCampaign(campaignList)
            } catch (e: HttpException) {
                Timber.d("Unable to connect to server")
                ShowMessge("Unable to connect to server")
            } catch (e: Error) {
                Timber.d("Something went wrong")
                ShowMessge("Something went wrong")
            }
        }
    }

}