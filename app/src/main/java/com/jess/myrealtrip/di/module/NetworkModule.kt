package com.jess.myrealtrip.di.module

import com.jess.myrealtrip.BuildConfig
import com.jess.myrealtrip.repository.service.GoogleService
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * @author jess
 * @since 2020.03.20
 */
@Module
class NetworkModule {

    companion object {
        const val NETWORK_TIME_OUT: Long = 5
    }

    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            chain.proceed(request.newBuilder().build())
        }
    }

    @Provides
    @Singleton
    fun createClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder().apply {
            if (BuildConfig.DEBUG) addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            connectTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS)
            readTimeout(NETWORK_TIME_OUT, TimeUnit.SECONDS)
            addInterceptor(interceptor)
        }.build()
    }

    @Singleton
    @Provides
    fun provideGoogleService(
        okHttpClient: OkHttpClient
    ): GoogleService {
        return Retrofit.Builder()
//            .baseUrl("https://api.rss2json.com/")
            .baseUrl("https://news.google.com")
            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(GoogleService::class.java)
    }
}