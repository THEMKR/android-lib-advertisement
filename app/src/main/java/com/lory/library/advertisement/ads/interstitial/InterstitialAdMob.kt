package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.utils.Tracer

internal class InterstitialAdMob : Interstitial {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".InterstitialAdMob"
    }

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdListener
     */
    constructor(context: Context, adId: String, onAdListener: OnAdListener?) : super(context, adId, onAdListener) {
        Tracer.debug(TAG, "Constructor : ")
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
    }

    override fun showAd() {
        Tracer.debug(TAG, "showAd: ")
    }

    override fun cancelAd() {
        Tracer.debug(TAG, "cancelAd: ")
    }

    override fun isAdLoaded(): Boolean {
        Tracer.debug(TAG, "isAdLoaded: ")
        return true
    }
}