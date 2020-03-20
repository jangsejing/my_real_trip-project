package com.jess.myrealtrip.common.extension

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

/**
 * @author jess
 * @since 2020.03.20
 */

/**
 * Toast
 *
 * @param stringRes
 */
fun Context.showToast(@StringRes stringRes: Int) {
    Toast.makeText(this, stringRes, Toast.LENGTH_SHORT).show()
}

/**
 * Toast
 *
 * @param text
 */
fun Context.showToast(text: String?) {
    text?.let {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}