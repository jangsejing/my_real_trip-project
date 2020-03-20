package com.jess.myrealtrip.presentation.splash.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.jess.myrealtrip.R
import com.jess.myrealtrip.common.base.BaseActivity
import com.jess.myrealtrip.databinding.MainActivityBinding
import com.jess.myrealtrip.databinding.SplashActivityBinding
import com.jess.myrealtrip.presentation.main.view.MainActivity
import com.jess.myrealtrip.presentation.splash.viewmodel.SplashViewModel

class SplashActivity : AppCompatActivity() {

    lateinit var binding: SplashActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.splash_activity)
        Handler().postDelayed({
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }, 1300)
    }
}
