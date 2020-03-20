package com.jess.myrealtrip.presentation.detail.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jess.myrealtrip.common.base.BaseViewModel
import com.jess.myrealtrip.data.NewsData
import javax.inject.Inject

/**
 * Main ViewModel
 */
class DetailViewModel @Inject constructor(

) : BaseViewModel() {

    private val _url = MutableLiveData<String>()
    val url: LiveData<String> = _url

    fun setNewData(data: NewsData) {
        _url.value = data.link
    }


}