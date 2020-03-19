package com.jess.myrealtrip.presenter.main.view

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.jess.myrealtrip.R
import com.jess.myrealtrip.common.base.BaseActivity
import com.jess.myrealtrip.common.extension.createViewModel
import com.jess.myrealtrip.databinding.MainActivityBinding
import com.jess.myrealtrip.presenter.main.viewmodel.MainViewModel
import com.jess.myrealtrip.presenter.splash.viewmodel.SplashViewModel
import javax.inject.Inject

class MainActivity : BaseActivity<MainActivityBinding, MainViewModel>() {

    override val layoutRes = R.layout.main_activity

    override val viewModelClass = MainViewModel::class.java

    override fun initLayout() {
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}
