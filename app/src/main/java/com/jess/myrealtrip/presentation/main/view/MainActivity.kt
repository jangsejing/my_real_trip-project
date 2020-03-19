package com.jess.myrealtrip.presentation.main.view

import android.os.Bundle
import com.jess.myrealtrip.R
import com.jess.myrealtrip.common.base.BaseActivity
import com.jess.myrealtrip.common.base.BaseRecyclerViewAdapter
import com.jess.myrealtrip.data.RssData
import com.jess.myrealtrip.databinding.MainActivityBinding
import com.jess.myrealtrip.databinding.MainItemBinding
import com.jess.myrealtrip.presentation.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*
import timber.log.Timber

class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {

    override val layoutRes = R.layout.main_activity

    override val viewModelClass = MainViewModel::class.java

    override fun initLayout() {
        rv_main.adapter = object :
            BaseRecyclerViewAdapter<RssData.ItemData, MainItemBinding>(R.layout.main_item) {
        }.apply {
            setOnItemClickListener { view, itemData ->
                Timber.d(itemData.toString())
            }
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        viewModel.getNews()
    }

}
