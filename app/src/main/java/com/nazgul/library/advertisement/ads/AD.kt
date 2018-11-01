package com.nazgul.library.advertisement.ads

import android.content.Context
import com.nazgul.library.advertisement.callback.OnAdListener

/**
 * Base class to extend by all AD unit
 */
abstract class AD {
    protected val context: Context
    protected val adId: String
        get() {
            return (field ?: "").trim()
        }
    protected val onAdListener: OnAdListener?

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdListener
     */
    constructor(context: Context, adId: String, onAdListener: OnAdListener?) {
        this.context = context
        this.adId = adId
        this.onAdListener = onAdListener
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
     * Method to check weather the ad is loaded or not
     * @return TRUE if ad is successfully loaded
     */
    abstract fun isAdLoaded(): Boolean

    /**
     * Method to cancel the AD
     */
    abstract fun cancelAd()

}