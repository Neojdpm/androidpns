package com.pnstest.neo.androidpnsconnection.Interfaces.models


data class PNSRequest (
    internal var token :String = "",
    internal var oauthAccessToken: String = "",
    internal var type : String = "mobile",
    internal var os : String = "android",
    internal var model : String = "",
    internal var version : String = "",
    internal var applicationId : Int = 1
)