package com.pnstest.neo.androidpnsconnection.Interfaces.models


data class Request (
    internal var token :String = "",
    internal var oauthAccessToken: Int = 0,
    internal var type : String = "mobile",
    internal var os : String = "android",
    internal var model : String = "",
    internal var version : String = "",
    internal var applicationId : String = ""
)