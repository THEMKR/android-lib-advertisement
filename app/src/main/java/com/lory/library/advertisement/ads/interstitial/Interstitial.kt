package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ads.Advertisement
import com.lory.library.advertisement.utils.Tracer

internal abstract class Interstitial : Advertisement {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".Interstitial"
    }

    protected val context: Context
    protected val adId: String
        get() {
            return (field ?: "").trim()
        }
    protected val onAdvertisementListener: OnAdvertisementListener

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdvertisementListener
     */
    internal constructor(context: Context, adId: String, onAdvertisementListener: OnAdvertisementListener) {
        Tracer.debug(TAG, "Constructor : ")
        this.context = context
        this.adId = adId
        this.onAdvertisementListener = onAdvertisementListener
    }

}