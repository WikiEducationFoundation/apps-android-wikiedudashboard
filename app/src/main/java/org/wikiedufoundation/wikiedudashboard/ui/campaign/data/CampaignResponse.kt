package org.wikiedufoundation.wikiedudashboard.ui.campaign.data

class CampaignResponse(val show: Boolean, val msg: String){

    data class ShowProgressbar (val show: Boolean)

    data class ToasterMsg (val msg : String)

}




