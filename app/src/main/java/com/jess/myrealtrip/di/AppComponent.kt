package com.jess.myrealtrip.di

import com.jess.myrealtrip.JessApplication
import com.jess.myrealtrip.di.module.ActivityModule
import com.jess.myrealtrip.di.module.AppModule
import com.jess.myrealtrip.di.module.NetworkModule
import com.jess.myrealtrip.di.module.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * @author jess
 * @since 2020.03.20
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        NetworkModule::class,
        RepositoryModule::class
    ]
)

interface AppComponent : AndroidInjector<JessApplication> {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: JessApplication): AppComponent
    }

}