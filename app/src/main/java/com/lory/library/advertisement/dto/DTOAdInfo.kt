package com.lory.library.advertisement.dto

import com.google.gson.annotations.SerializedName

class DTOAdInfo {

    /**
     * Method to get the Application ID
     */
    @SerializedName("appId")
    var appId: String = ""

    /**
     * Method to get the AdProvider Index
     */
    @SerializedName("adProvider")
    var adProvider: Int = -1

    /**
     * Method to get the Ad Id
     */
    @SerializedName("adId")
    var adId: String = ""

    /**
     * Method to get the Ad Id
     */
    @SerializedName("adType")
    var adType: Int = -1

    override fun toString(): String {
        return super.toString() + "  AdProvider : ${adProvider}     AppId : ${appId}        AdId : ${adId}      AdType : ${adType}"
    }
}