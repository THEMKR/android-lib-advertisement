package com.mkrworld.mkr_android_lib_ads.ads

import android.content.Context
import com.mkrworld.mkr_android_lib_ads.callback.OnAdListener

/**
 * Base class to extend by all Ad unit
 */
abstract class BaseAd {
    val context: Context

    var adId: String? = null
        get() {
            return (field ?: "").trim()
        }

    var onAdListener: OnAdListener? = null

    /**
     * Constructor
     * @param context
     */
    constructor(context: Context) {
        this.context = context
    }

    /**
     * Method to fetch the Add
     */
    abstract fun fetchAd()

    /**
     * Method to show the Add
     */
    abstract fun showAd()

    /**
     * Method to cancel the Ad
     */
    abstract fun cancelAd()

}