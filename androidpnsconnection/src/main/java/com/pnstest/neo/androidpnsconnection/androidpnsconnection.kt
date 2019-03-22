package com.pnstest.neo.androidpnsconnection

import android.os.Build
import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.pnstest.neo.androidpnsconnection.Interfaces.IPNSConnection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory


class AndroidPNSConnection(val fcmToken: String) {
    val apiserve by lazy {
        IPNSConnection.create()
    }
    var disposable: Disposable? = null


    fun beginSearch(srsearch: String) {
        Log.i("PNSConn", Build.MODEL)
        Log.i("PNSConn", "On Library")
        disposable =
            apiserve.hitCountCheck("query", "json", "search", srsearch)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { result -> Log.i("PNSConn", result.toString()) },
                    { error -> Log.i("PNSConn", error.message) }
                )
    }


}