package com.lory.library.advertisement.dto

import com.google.gson.annotations.SerializedName

class DTOAppConfig : DTOBaseNetworkResponse() {

    /**
     * Current Version of app on firebase
     */
    @SerializedName("version")
    var version: Int = 0

    /**
     * Method to get the Info of the Ad
     */
    @SerializedName("adInfoList")
    var adInfoList: ArrayList<DTOAdInfo> = ArrayList()
}