package com.lory.library.advertisement.dto

import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName
import com.lory.library.advertisement.utils.Constants

@IgnoreExtraProperties
class DTOAppConfig : DTOBaseNetworkResponse() {

    /**
     * Current Version of app on firebase
     */
    var version: Int = 0

    /**
     * Method to get the Info of the Ad
     */
    var adInfoList: ArrayList<DTOAdInfo> = ArrayList()

}