package com.app.nytimes.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.nytimes.api.constants.Params
import com.app.nytimes.data.Feed
import com.app.nytimes.data.Resource
import com.app.nytimes.network.NetworkControl
import com.app.nytimes.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val mainRepository: MainRepository,
    private val networkControl: NetworkControl
) : ViewModel() {
    //loading
    var isLoading = MutableLiveData(true)

    companion object {
        private val TAG = MainVM::class.java.simpleName
    }

    //List
    private val _feedList = MutableLiveData<Resource<List<Feed>>>()
    val feedList: LiveData<Resource<List<Feed>>>
        get() = _feedList

    fun callToGetPopularFeeds(period: Int) = viewModelScope.launch {
        if (!networkControl.isConnected()) {
            _feedList.postValue(Resource.error("Connection Error!", null))
            return@launch
        }
        isLoading.postValue(true)
        mainRepository.getMostPopularFeeds(7).let {
            if (it.isSuccessful) {
                it.body().run {
                    if (it.body()?.status == Params.STATUS_OK)
                        _feedList.postValue(Resource.success(it.body()?.results!!))
                }
            } else {
                _feedList.postValue(Resource.error(it.errorBody().toString(), null))
            }
        }
    }

}