package com.jess.myrealtrip.di.module

import androidx.lifecycle.ViewModelProvider
import com.jess.myrealtrip.di.ViewModelFactory
import dagger.Binds
import dagger.Module

/**
 * @author jess
 * @since 2020.03.20
 */
@Module(includes = [ViewModelModule::class, ViewModelFactoryModule::class])
class AppModule {

}

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}
