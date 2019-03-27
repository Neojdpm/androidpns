package com.pnstest.neo.androidpnsconnection.Interfaces


import com.pnstest.neo.androidpnsconnection.Interfaces.models.AccessToken

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.Observable
import retrofit2.http.*

interface IDevConnection {

    @GET("api/cmc/auth/access-token/")
    fun getAccessToken(@Header("Authorization") token : String) : Observable<AccessToken>

    companion object {
        fun create(baseURL: String): IDevConnection {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build()

            return retrofit.create(IDevConnection::class.java)
        }
    }

}
