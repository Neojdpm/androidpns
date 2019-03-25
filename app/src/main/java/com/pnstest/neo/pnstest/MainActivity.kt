package com.pnstest.neo.pnstest

import android.bluetooth.BluetoothClass
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pnstest.neo.androidpnsconnection.AndroidPNSConnection
import com.google.firebase.internal.FirebaseAppHelper.getToken
import com.google.firebase.iid.InstanceIdResult
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.iid.FirebaseInstanceId



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
