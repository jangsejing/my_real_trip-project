package com.jess.myrealtrip.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jess.myrealtrip.common.base.BaseViewModel
import com.jess.myrealtrip.common.extension.safeScope
import com.jess.myrealtrip.data.ChannelData
import com.jess.myrealtrip.repository.GoogleRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Main ViewModel
 *
 * @property repository
 */
class MainViewModel @Inject constructor(
    private val repository: GoogleRepository
) : BaseViewModel() {

    private val _item = MutableLiveData<List<ChannelData.ItemData>>()
    val item: LiveData<List<ChannelData.ItemData>> = _item

    /**
     * News 정보 가져오기
     */
    fun getNews() {
        viewModelScope.safeScope().launch {
            repository.getList()?.let { channel ->
                _item.value = channel.item
            }
        }
    }
}