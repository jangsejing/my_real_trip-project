package com.jess.myrealtrip.di.compnent

import com.jess.myrealtrip.MainApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * AppComponent
 *
 * @author jess
 * @since 2020.03.18
 */
@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class
    ]
)

interface AppComponent : AndroidInjector<MainApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: MainApplication): AppComponent
    }
}