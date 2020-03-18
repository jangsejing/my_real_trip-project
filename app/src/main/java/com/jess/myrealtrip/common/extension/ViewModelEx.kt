package com.jess.myrealtrip.common.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

fun <T : ViewModel> AppCompatActivity.createViewModel(
    factory: ViewModelProvider.Factory,
    classType: Class<T>
): T = ViewModelProvider(this, factory)[classType]