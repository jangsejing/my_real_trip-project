package com.jess.myrealtrip.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jess.myrealtrip.common.base.BaseViewModel
import com.jess.myrealtrip.common.extension.safeScope
import com.jess.myrealtrip.data.RssData
import com.jess.myrealtrip.repository.GoogleRepository
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

/**
 * Main ViewModel
 *
 * @property repository
 */
class MainViewModel @Inject constructor(
    private val repository: GoogleRepository
) : BaseViewModel() {

    private val _item = MutableLiveData<List<RssData.ItemData>>()
    val item: LiveData<List<RssData.ItemData>> = _item

    /**
     * News 정보 가져오기
     */
    fun getNews() {
        viewModelScope.safeScope().launch {
            _item.postValue(repository.getList().body()?.items)
            Timber.d(repository.getList().body()?.items.toString())
        }
    }

}