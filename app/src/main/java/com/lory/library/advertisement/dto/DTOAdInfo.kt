package com.lory.library.advertisement.dto

import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName
import com.lory.library.advertisement.utils.Constants

@IgnoreExtraProperties
class DTOAdInfo {

    /**
     * Method to get the Application ID
     */
    var appId: String = ""

    /**
     * Method to get the AdProvider Index
     */
    var adProvider: Int = 0

    /**
     * Method to get the Ad Id
     */
    var adId: String = ""

    /**
     * Method to get the Ad Id
     */
    var adType: Int = 0

}