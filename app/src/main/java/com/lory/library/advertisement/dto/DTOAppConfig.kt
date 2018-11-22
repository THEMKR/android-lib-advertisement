package com.lory.library.advertisement.dto

import com.google.firebase.database.IgnoreExtraProperties
import com.google.firebase.database.PropertyName

@IgnoreExtraProperties
class DTOAppConfig : DTOBaseNetworkResponse() {

    /**
     * Current Version of app on firebase
     */
    @PropertyName("version")
    var version: Int = 0

    /**
     * Method to get the Info of the Ad
     */
    @PropertyName("adInfoList")
    var adInfoList: ArrayList<DTOAdInfo> = ArrayList()

    /**
     * Key denote the app App Key
     */
    var key: String = ""
        get() {
            return field.trim()
        }
}