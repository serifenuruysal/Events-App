package com.androidapp.eventsapp.data.network.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.Rfc3339DateJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.*

/**
 * Created by S.Nur Uysal on 2020-02-10.
 */

private const val BOOMSET_API_BASE_URL = "https://api.boomset.com/"

class ApiService : BoomsetApi {

    private val api: ApiInterface

    init {

        api = Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder()
                        .add(Date::class.java, Rfc3339DateJsonAdapter().nullSafe())
                        .add(KotlinJsonAdapterFactory())
                        .build()
                )
            )
            .client(providesOkHttpClient())
            .baseUrl(BOOMSET_API_BASE_URL)
            .build()
            .create(ApiInterface::class.java)
    }

    private fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(OAuthInterceptor())
            .build()
    }

    override fun getEvents(page: Int) =
        api.getEvents(page)

    override fun getGuests(eventId: Int, page: Int) =
        api.getGuests(eventId, page)


}

class OAuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request =
            request.newBuilder().header("Authorization", "Token e08b31ca0b7adff3041bee800fc8d306")
                .build()

        return chain.proceed(request)
    }
}