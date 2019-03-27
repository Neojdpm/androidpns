package com.pnstest.neo.androidpnsconnection.Interfaces

import com.pnstest.neo.androidpnsconnection.Interfaces.models.PNSRequest

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.Observable
import retrofit2.http.*

interface IPNSConnection {

    @POST("devices")
    fun registerDevice(@Body client : PNSRequest ) : Observable<kotlin.Any>

    companion object {
        fun create(baseURL: String): IPNSConnection {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl(baseURL)
                .build()

            return retrofit.create(IPNSConnection::class.java)
        }
    }

}
