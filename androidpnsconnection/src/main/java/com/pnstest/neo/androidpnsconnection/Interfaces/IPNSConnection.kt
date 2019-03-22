package com.pnstest.neo.androidpnsconnection.Interfaces


import com.pnstest.neo.androidpnsconnection.Interfaces.models.Request
import org.json.JSONObject

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.Body
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.Observable

interface IPNSConnection {

    @GET("cmc/api/auth/access-token/")
    fun loadPetitions(@Query("token") fcmToken: String) : Observable<kotlin.Any>

    @POST("devices")
    fun getAccessToken(@Body client : Request) : Observable<kotlin.Any>


    @GET("api.php")
    fun hitCountCheck(@Query("action") action: String,
                      @Query("format") format: String,
                      @Query("list") list: String,
                      @Query("srsearch") srsearch: String):  Observable<kotlin.Any>

    companion object {
        fun create(): IPNSConnection {

            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .addConverterFactory(
                    GsonConverterFactory.create())
                .baseUrl("https://en.wikipedia.org/w/")
                .build()

            return retrofit.create(IPNSConnection::class.java)
        }
    }

}
