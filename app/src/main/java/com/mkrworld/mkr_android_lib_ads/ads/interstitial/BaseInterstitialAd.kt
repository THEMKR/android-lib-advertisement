package com.mkrworld.mkr_android_lib_ads.ads.interstitial

import android.content.Context
import com.mkrworld.mkr_android_lib_ads.BuildConfig
import com.mkrworld.mkr_android_lib_ads.ads.BaseAd
import com.mkrworld.mkr_android_lib_ads.utils.Tracer

abstract class BaseInterstitialAd : BaseAd {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".BaseInterstitialAd"
    }

    /**
     * Constructor
     * @param context
     */
    constructor(context: Context) : super(context) {
        Tracer.debug(TAG, "Constructor : ")
    }

}