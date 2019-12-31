package org.wikiedufoundation.wikiedudashboard.data.localDatabse.Repository

import androidx.lifecycle.LiveData
import org.wikiedufoundation.wikiedudashboard.data.localDatabse.dao.ActiveCampaignDao
import org.wikiedufoundation.wikiedudashboard.ui.campaign.data.CampaignListData

/**Declares the DAO as a private property in the constructor. Pass in the DAO
*instead of the whole database, because you only need access to the DAO*
 * */
class ActiveCampaignRepository(val activeCampaignDao: ActiveCampaignDao){


    /** Room executes all queries on a separate thread.
     * Observed LiveData will notify the observer when the data has changed.
     * */
    val allCampaignList : LiveData<List<CampaignListData>> = activeCampaignDao.getAllCampaign()


    /** The suspend modifier tells the compiler that this must be called from a
     *  coroutine or another suspend function.
     **/

    suspend fun insert(campaignListData: CampaignListData) {

        activeCampaignDao.insertCampaign(campaignListData)
    }

    suspend fun delete(){
        activeCampaignDao.deleteAllCampaign()
    }


//    override fun requestCampaignList(cookies: String, presenterCallback: PresenterCallback<*>): LiveData<List<ExploreCampaignsResponse>> {
//
//        val campaignListResponseCall = wikiEduDashboardApi.getExploreCampaigns(cookies)
//        campaignListResponseCall.enqueue(object : Callback<ExploreCampaignsResponse> {
//            override fun onResponse(call: Call<ExploreCampaignsResponse>, response: Response<ExploreCampaignsResponse>) {
//                presenterCallback.onSuccess(response.body())
//                response.body()?.let { activeCampaignDao.insertCampaign(it) }
//            }
//
//            override fun onFailure(call: Call<ExploreCampaignsResponse>, t: Throwable) {
//                presenterCallback.onFailure()
//            }
//        })
//
//        return activeCampaignDao.getAllCampaign()
//    }
}