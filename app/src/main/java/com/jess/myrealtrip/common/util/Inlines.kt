package com.jess.myrealtrip.common.util

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import timber.log.Timber

/**
 * 예외처리
 *
 * @param action
 */
inline fun tryCatch(action: () -> Unit) {
    try {
        action()
    } catch (e: Exception) {
        e.printStackTrace()
        Timber.e(e)
    }
}