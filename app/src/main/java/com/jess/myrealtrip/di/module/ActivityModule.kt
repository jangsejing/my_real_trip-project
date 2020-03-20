package com.jess.myrealtrip.di.module

import com.jess.myrealtrip.presentation.detail.view.DetailActivity
import com.jess.myrealtrip.presentation.main.view.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindDetailActivity(): DetailActivity

}