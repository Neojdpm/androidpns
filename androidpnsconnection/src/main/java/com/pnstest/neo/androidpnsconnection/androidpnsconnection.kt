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


class AndroidPNSConnection(val baseURL: String, val pnsURL: String, val fcmToken: String, val appId : Int) {
    val attt = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjcxYTlhNjcxMzBiYWJiMjQ2M2Q2OGZlODk0MDEwNTQ2YWE2NWZjNjNhN2ZjYWUwODQ2YmY2YjA0MDI4ZGFlMWVkYTgyYzhhMDU4ODJmZmUxIn0.eyJhdWQiOiIyIiwianRpIjoiNzFhOWE2NzEzMGJhYmIyNDYzZDY4ZmU4OTQwMTA1NDZhYTY1ZmM2M2E3ZmNhZTA4NDZiZjZiMDQwMjhkYWUxZWRhODJjOGEwNTg4MmZmZTEiLCJpYXQiOjE1NTMyODgyOTUsIm5iZiI6MTU1MzI4ODI5NSwiZXhwIjoxNTg0OTEwNjk1LCJzdWIiOiIyNzk4Iiwic2NvcGVzIjpbXX0.HtioQlS8bCVm5tQWgz9fB37iLO7TCAuY47lE9VhSHYSqafUEqDilNZJqtAMd_l4H_OblSRpOj5EPuh0l1STIxP4l6ZOLrFwSDUM-j-PhBD-XPPRfEoOWHiWnWdRJR4z_itl4QFlg-E6ko8twldWiKCVxdRtChVqtx_UtlkqInpzyYUZeRi4lK2W7goLDcKrwACWRrZyx_MlK5DTORdHMNUvT8Dkw1ZCIzfE8BGsSVAddUQhsd_uGorJGTIDGTxXRmDjps5_AWJEzLRqG8k-qxQLituweXhmRNv8ij3-NK26S2-lOlDyTy7BoJrRt9lolm30AGLYHmDEVU0pfnb9XJOm5NModmL8I4k7Aw-86zzNoGOuxhC2hLz52evEjTXDPaoBVecgkqbuul1JC1fOiaYz-VOrIUZUHchOA2X5K78-xG70meswy3veZsuRv1-Wkr5vCA9BL7OQChFl4lDqA-ZxW0O2gDkNZl1KcyAn17kH0Q_V1vHLAyXmYfk4naQLFuwADceWOKdmgo70zE0hOb05c4q9DHIYMdoRDZrAzOySpcTCjqtbXGTAh_T8C0i19wVkkjb_8SyRrCjjXiPzCqEGrjpLbAs8UsgXOfYpAbWG0Woumd2lBDr35iblkyYcbXpwELiVEBLY-v-GM8groZZ_2KFWG7EZx6RDMBJmxRMU\",\"refresh_token\":\"def50200a77cab459703b60b5dc86c28ced3eba67a28eefa677f224003ba3dc1862dcece5e392cadb4c5ca0ba5a69325517bda89ef1bac2fc7bd387a0739c65d36e7c4dc737272d9fd49ea19b5a9b2f1aa705b3ce8fdb0be0311428c7ec52635f2ec284fbb561c369348dd1fd6ee7d76cd19c3333ccdf35a552705a365c97d97ff5bfa2705e3ea48b1a7d14bce97fd31dcece59081a1d2335cf5ff763adeda0cbce3b68a2af90600a9367968c59a2820849e53754b3844bb96e86536582a62a675a10b0b5cb875f5c4747142d27b4c63a7d4c274fa52aa24598d2e6778b609e711e4be0857090944513ada61450a0512167dd7d8d64b2222b982b1241d872754a692afc2e184590f6bc8c70b232313d895450c0efb6db15256dca3d0692850ffb337e29a26291f12ecb08844b926fe204f791287644c1e60542b59634308e12f013b116be41b50f52b25cc17e6abe85fd93517156c518dedc78dbda3cc309b7f9b1cd575"
    val pnsapi by lazy {
        IPNSConnection.create(pnsURL, "")
    }
    val devapi by lazy {
        IDevConnection.create(baseURL, "")
    }
    var disposable: Disposable? = null

    fun start() {
        Log.i("PNSConn", Build.MODEL)
        disposable = devapi.getAccessToken(this.attt).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result ->
                    val pnsrequest = PNSRequest(
                        token = fcmToken,
                        oauthAccessToken = result.accessToken,
                        model = Build.MODEL,
                        version = Build.VERSION.SDK,
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