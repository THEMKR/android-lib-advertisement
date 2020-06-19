package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ads.Advertisement

/**
 * @author THEMKR
 */
internal abstract class Interstitial : Advertisement {
    protected val context: Context
    protected val adId: String
        get() {
            return field.trim()
        }
    protected val onAdvertisementListener: OnAdvertisementListener

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdvertisementListener
     */
    internal constructor(context: Context, adId: String, onAdvertisementListener: OnAdvertisementListener) {
        this.context = context
        this.adId = adId
        this.onAdvertisementListener = onAdvertisementListener
    }

}