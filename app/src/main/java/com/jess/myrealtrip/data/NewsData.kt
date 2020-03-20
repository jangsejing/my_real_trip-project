package com.jess.myrealtrip.data

import androidx.databinding.ObservableField

class NewsData(
    val title: String? = null,
    val link: String? = null,
    val description: ObservableField<String> = ObservableField(),
    val image: ObservableField<String> = ObservableField(),
    var isRequest: Boolean = false
)
