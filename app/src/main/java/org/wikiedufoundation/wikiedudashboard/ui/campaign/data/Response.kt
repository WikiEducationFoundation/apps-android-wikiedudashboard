package org.wikiedufoundation.wikiedudashboard.ui.campaign.data

import okhttp3.ResponseBody
import retrofit2.Response

  data class  ShowMessge( val showMsg : String?)
  data class  ShowProgress( val showProgress : Boolean)

   fun responseMessage(response: Response<ResponseBody>): ShowMessge {
    val result: String?
    return if (response.isSuccessful) {
      result = response.body()?.string()
      ShowMessge(result)
    } else {
      result = response.errorBody()?.string()
      ShowMessge(result)
    }
  }
