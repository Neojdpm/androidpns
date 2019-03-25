package com.pnstest.neo.pnstest

import android.bluetooth.BluetoothClass
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pnstest.neo.androidpnsconnection.AndroidPNSConnection


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AndroidPNSConnection("https://dev.panatech.io/","https://pns.dev.panatech.io:3003/","asd", "asd", 2)
    }
}
