package com.jess.myrealtrip.presentation.main.view

import android.os.Bundle
import com.jess.myrealtrip.R
import com.jess.myrealtrip.common.base.BaseActivity
import com.jess.myrealtrip.common.base.BaseRecyclerViewAdapter
import com.jess.myrealtrip.data.NewsData
import com.jess.myrealtrip.databinding.MainActivityBinding
import com.jess.myrealtrip.databinding.MainItemBinding
import com.jess.myrealtrip.presentation.main.viewmodel.MainItemViewModel
import com.jess.myrealtrip.presentation.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*
import timber.log.Timber

class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {

    override val layoutRes = R.layout.main_activity

    override val viewModelClass = MainViewModel::class.java

    override fun initLayout() {
        rv_main.adapter = object :

            BaseRecyclerViewAdapter<NewsData, MainItemBinding>(R.layout.main_item) {

            override fun onBindData(
                position: Int,
                data: NewsData?,
                dataBinding: MainItemBinding
            ) {
                dataBinding.viewModel = MainItemViewModel(data)
            }
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
