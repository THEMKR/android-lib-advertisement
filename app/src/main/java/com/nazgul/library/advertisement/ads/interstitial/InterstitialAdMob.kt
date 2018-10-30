package com.nazgul.library.advertisement.ads.interstitial

import android.content.Context
import com.nazgul.library.advertisement.BuildConfig
import com.nazgul.library.advertisement.utils.Tracer

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