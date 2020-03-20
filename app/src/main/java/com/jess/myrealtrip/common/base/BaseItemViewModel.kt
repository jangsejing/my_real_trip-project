package com.jess.myrealtrip.common.base

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext
/**
 * @author jess
 * @since 2020.03.20
 */
open class BaseItemViewModel {

    // IO Dispatchers
    val ioDispatchers: CoroutineContext = Dispatchers.IO

    // UI Dispatchers
    val uiDispatchers: CoroutineContext = Dispatchers.Main
}