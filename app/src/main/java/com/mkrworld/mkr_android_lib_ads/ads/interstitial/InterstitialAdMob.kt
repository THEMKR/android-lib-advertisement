package com.mkrworld.mkr_android_lib_ads.ads.interstitial

import android.content.Context
import com.mkrworld.mkr_android_lib_ads.BuildConfig
import com.mkrworld.mkr_android_lib_ads.ads.BaseAd
import com.mkrworld.mkr_android_lib_ads.utils.Tracer

class InterstitialAdMob : BaseInterstitialAd {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".InterstitialAdMob"
    }

    /**
     * Constructor
     * @param context
     */
    constructor(context: Context) : super(context) {
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
}