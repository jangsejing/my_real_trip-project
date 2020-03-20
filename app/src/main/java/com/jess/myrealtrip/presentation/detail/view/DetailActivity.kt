package com.jess.myrealtrip.presentation.detail.view

import android.os.Bundle
import android.view.MenuItem
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jess.myrealtrip.R
import com.jess.myrealtrip.common.base.BaseActivity
import com.jess.myrealtrip.common.util.tryCatch
import com.jess.myrealtrip.data.NewsData
import com.jess.myrealtrip.databinding.DetailActivityBinding
import com.jess.myrealtrip.presentation.detail.viewmodel.DetailViewModel
import kotlinx.android.synthetic.main.detail_activity.*
import kotlinx.android.synthetic.main.detail_activity.view.*

class DetailActivity : BaseActivity<DetailActivityBinding, DetailViewModel>() {

    override val layoutRes = R.layout.detail_activity

    override val viewModelClass = DetailViewModel::class.java

    override fun initLayout() {
        // back 버튼 노출
        setSupportActionBar(tb_detail)
        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowHomeEnabled(true)
        }
    }

    override fun onCreated(savedInstanceState: Bundle?) {
        tryCatch {
            intent?.let {
                val data = intent.getSerializableExtra(NewsData::class.java.simpleName) as NewsData
                viewModel.setNewData(data)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
