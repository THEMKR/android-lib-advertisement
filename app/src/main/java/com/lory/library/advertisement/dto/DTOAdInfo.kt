package com.lory.library.advertisement.dto

import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName

@IgnoreExtraProperties
class DTOAdInfo {

    /**
     * Method to get the Application ID
     */
    @PropertyName("appId")
    var appId: String = ""

    /**
     * Method to get the AdProvider Index
     */
    @PropertyName("adProvider")
    var adProvider: Int = 0

    /**
     * Method to get the Ad Id
     */
    @PropertyName("adId")
    var adId: String = ""

    /**
     * Method to get the Ad Id
     */
    @PropertyName("adType")
    var adType: Int = 0

}