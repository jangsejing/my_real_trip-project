package com.jess.myrealtrip.presentation.main.view

import android.content.Intent
import android.os.Bundle
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jess.myrealtrip.R
import com.jess.myrealtrip.common.base.BaseActivity
import com.jess.myrealtrip.common.base.BaseRecyclerViewAdapter
import com.jess.myrealtrip.common.extension.addItems
import com.jess.myrealtrip.data.NewsData
import com.jess.myrealtrip.databinding.MainActivityBinding
import com.jess.myrealtrip.databinding.MainItemBinding
import com.jess.myrealtrip.presentation.detail.view.DetailActivity
import com.jess.myrealtrip.presentation.main.viewmodel.MainItemViewModel
import com.jess.myrealtrip.presentation.main.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.main_activity.*
import timber.log.Timber

/**
 * MainActivity
 *
 * @author jess
 * @since 2020.03.20
 */
class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>(),
    SwipeRefreshLayout.OnRefreshListener {

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
                val intent = Intent(this@MainActivity, DetailActivity::class.java).apply {
                    putExtra(NewsData::class.java.simpleName, itemData)
                }
                startActivity(intent)
            }
        }

        // refresh
        refresh.setOnRefreshListener(this)
    }

    override fun initDataBinding() {
        super.initDataBinding()
        viewModel.isProgress.observe(this, Observer {
            refresh.isRefreshing = it
        })
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        onRefresh()
    }

    override fun onRefresh() {
        (rv_main.adapter as BaseRecyclerViewAdapter<*, *>).run {
            clear()
        }
        viewModel.getNews()
    }
}
