package com.jess.myrealtrip.common.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

open class BaseViewModel @Inject constructor() : ViewModel() {

    // IO Dispatchers
    val ioDispatchers: CoroutineContext = Dispatchers.IO

    // UI Dispatchers
    val uiDispatchers: CoroutineContext = Dispatchers.Main
}