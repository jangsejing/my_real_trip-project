package com.jess.myrealtrip.di.module

import androidx.lifecycle.ViewModelProvider
import com.jess.myrealtrip.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule::class, ViewModelFactoryModule::class])
class AppModule {

//    companion object {
//        const val NETWORK_TIME_OUT: Long = 15
//    }
//
//    @Provides
//    @Singleton
//    fun provideInterceptor(): Interceptor {
//        return Interceptor { chain ->
//            val request = chain.request()
//            chain.proceed(request.newBuilder().build())
//        }
//    }
//
//    @Provides
//    @Singleton
//    fun createClient(interceptor: Interceptor): OkHttpClient {
//        return OkHttpClient.Builder().apply {
//            if (BuildConfig.DEBUG) addInterceptor(
//                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
//            )
//            connectTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS)
//            readTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS)
//            addInterceptor(interceptor)
//        }.build()
//    }
//
//
//    @Provides
//    @Singleton
//    fun provideGson(): Gson {
//        return GsonBuilder()
//            .setLenient()
//            .create()
//    }
//
//    @Singleton
//    @Provides
//    fun provideApiService(
//        context: Context,
//        okHttpClient: OkHttpClient,
//        gson: Gson
//    ): MarketService {
//        return Retrofit.Builder()
//            .baseUrl(context.getString(R.string.api_base_url))
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create(gson))
//            .build()
//            .create(MarketService::class.java)
//    }
}

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(
        factory: ViewModelFactory
    ): ViewModelProvider.Factory
}
