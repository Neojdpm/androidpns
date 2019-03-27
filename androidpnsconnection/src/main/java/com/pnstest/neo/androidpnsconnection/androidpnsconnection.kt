package com.pnstest.neo.androidpnsconnection

import android.os.Build
import android.util.Log
import com.pnstest.neo.androidpnsconnection.Interfaces.IDevConnection
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import com.pnstest.neo.androidpnsconnection.Interfaces.IPNSConnection
import com.pnstest.neo.androidpnsconnection.Interfaces.models.PNSRequest
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

/**
 * Android PNS Connection
 *
 * Funcionalidad para conectar la aplicacion Android al Pana Notification Server.
 *
 * @author neojdpm
 */
class AndroidPNSConnection(val baseURL: String, val pnsURL: String, val fcmToken: String, val accessToken: String, val appId : Int) {
    val attt = "Bearer $accessToken"
    val pnsapi by lazy {
        IPNSConnection.create(pnsURL)
    }
    val devapi by lazy {
        IDevConnection.create(baseURL)
    }
    var disposable: Disposable? = null

    fun start() {
        Log.i("PNSConn", this.attt)
        disposable = devapi.getAccessToken(this.attt).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    val pnsrequest = PNSRequest(
                        token = fcmToken,
                        oauthAccessToken = result.accessToken,
                        model = Build.MODEL,
                        version = Build.VERSION.RELEASE,
                        applicationId = appId
                    )
                    Log.i("PNSConn", pnsrequest.toString())

                    pnsapi.registerDevice(pnsrequest).subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            { result ->
                                Log.i("PNSConn", result.toString())
                            },
                            { error -> Log.e("PNSConn", error.toString())  }
                        )
                },
                { error -> Log.e("PNSConn", error.toString())  }
            )


    }


}