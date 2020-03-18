package com.jess.myrealtrip.presenter.main.view

import android.os.Bundle
import com.jess.myrealtrip.R
import com.jess.myrealtrip.common.base.BaseActivity
import com.jess.myrealtrip.databinding.MainActivityBinding
import com.jess.myrealtrip.presenter.splash.viewmodel.SplashViewModel

class MainActivity : BaseActivity<MainActivityBinding, SplashViewModel>() {

    override val layoutRes = R.layout.main_activity

    override val viewModelClass = SplashViewModel::class.java

    override fun initLayout() {
    }

    override fun onCreated(savedInstanceState: Bundle?) {

    }

}
