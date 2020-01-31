package org.wikiedufoundation.wikiedudashboard.ui.profile.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileDetails
import org.wikiedufoundation.wikiedudashboard.ui.profile.data.ProfileResponse
import org.wikiedufoundation.wikiedudashboard.ui.profile.repository.ProfileRepository
import org.wikiedufoundation.wikiedudashboard.util.ShowMessage
import java.io.IOException

/**
 * Class extends ViewModel and requires ProfileRepository.
 */
class ProfileViewModel(private val profileRepository: ProfileRepository) : ViewModel() {

    private val _showMsg: MutableLiveData<ShowMessage> = MutableLiveData()
    val showMsg: MutableLiveData<ShowMessage> get() = _showMsg
    private val _progressbar = MutableLiveData<Boolean>()
    val progressbar: LiveData<Boolean> get() = _progressbar
    private val _profile: MutableLiveData<ProfileResponse> = MutableLiveData()
    val profile : LiveData<ProfileResponse> get() = _profile
    private val _profileDetails : MutableLiveData<ProfileDetails> = MutableLiveData()
    val profileDetails : LiveData<ProfileDetails> get() = _profileDetails


    init {
        _progressbar.postValue(true)
    }


    /**  The implementation of [requestProfile] is completely hidden from the UI.
     *  We don't want insert to block the main thread, so we're launching a new
     *  coroutine. ViewModels have a coroutine scope based on their lifecycle called
     *  viewModelScope which we can use here.
     **/

    fun requestProfile(cookies: String, username: String){
        viewModelScope.launch {
            try {
                _profile.postValue(profileRepository.requestProfile(cookies, username))
                _progressbar.postValue(false)

            }catch (io : IOException){
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)
            }
        }
    }

    /**  The implementation of [requestProfileDetails] is completely hidden from the UI.
     *  We don't want insert to block the main thread, so we're launching a new
     *  coroutine. ViewModels have a coroutine scope based on their lifecycle called
     *  viewModelScope which we can use here.
     **/
    fun requestProfileDetails(username : String){
        viewModelScope.launch {
            try {
                _profileDetails.postValue(profileRepository.requestProfileDetails(username))
                _progressbar.postValue(false)
            }catch (io : IOException){
                _showMsg.postValue(ShowMessage("Unable to connect to server."))
                _progressbar.postValue(false)
            }
        }
    }


}