package org.wikiedufoundation.wikiedudashboard.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardApi
import org.wikiedufoundation.wikiedudashboard.data.network.WikiEduDashboardMediaApi
import org.wikiedufoundation.wikiedudashboard.util.Urls
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Use the [apiModule] to creating Retrofit2 api service
 * for WikiEduDashboardApi
 *
 * @return retrofit.create(WikiEduDashboardApi::class.java) */
val apiModule = module {

    single { provideBaseRetrofit().create(WikiEduDashboardApi::class.java) }
    single { provideCommonsRetrofit().create(WikiEduDashboardMediaApi::class.java) }
}

/**
 * Use the [providerGson] to provide Gson
 * @return  GsonBuilder*/
fun providerGson(): Gson =
        GsonBuilder()
                .setLenient()
                .create()

/**
 * Use the [provideInterceptor] to provide a HttpLoggingInterceptor
 * @return  HttpLoggingInterceptor*/
fun provideInterceptor(): HttpLoggingInterceptor {
    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    return interceptor
}

/**
 * Use the [provideClient] to provide a OkHttpClient
 * @return  OkHttpClient*/
fun provideClient(): OkHttpClient =
        OkHttpClient.Builder()
                .addInterceptor(provideInterceptor())
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .build()

/**
 * Use the [provideBaseRetrofit] to provide a Retrofit with WITH_BASE_URL instance
 * @return  Retrofit*/
fun provideBaseRetrofit(): Retrofit =
        Retrofit.Builder()
                .baseUrl(Urls.BASE_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create(providerGson()))
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

/**
 * Use the [provideCommonsRetrofit] to provide a Retrofit with WIKI_MEDIA_COMMONS instance
 * @return  Retrofit*/
fun provideCommonsRetrofit(): Retrofit =
        Retrofit.Builder()
                .baseUrl(Urls.WIKIMEDIA_COMMONS_BASE_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create(providerGson()))
                .build()
