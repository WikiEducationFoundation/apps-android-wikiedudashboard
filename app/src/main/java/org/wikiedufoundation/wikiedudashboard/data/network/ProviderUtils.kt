package org.wikiedufoundation.wikiedudashboard.data.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.wikiedufoundation.wikiedudashboard.util.Urls
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Network utils for Retrofit2 API service
 ***/
class ProviderUtils {
    companion object {
        /**
         * Use the [get] method for creating Retrofit2 api service
         * for WikiEduDashboardApi
         *
         * @return retrofit.create(WikiEduDashboardApi::class.java) */
        val apiObject: WikiEduDashboardApi
            get() {

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES).build()
                val gson = GsonBuilder()
                        .setLenient()
                        .create()
                val retrofit = Retrofit.Builder()
                        .baseUrl(Urls.BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                return retrofit.create(WikiEduDashboardApi::class.java)
            }

        /**
         * Use the [get] method for creating Retrofit2 api service
         * for WikiEduDashboardApi
         *
         * @return retrofit.create(WikiEduDashboardApi::class.java) */
        val commonsApiObject: WikiEduDashboardApi
            get() {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val client = OkHttpClient.Builder().addInterceptor(interceptor).connectTimeout(5, TimeUnit.MINUTES)
                        .readTimeout(5, TimeUnit.MINUTES).build()
                val gson = GsonBuilder()
                        .setLenient()
                        .create()
                val retrofit = Retrofit.Builder()
                        .baseUrl(Urls.WIKIMEDIA_COMMONS_BASE_URL)
                        .client(client)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .build()
                return retrofit.create(WikiEduDashboardApi::class.java)
            }
    }
}
