package com.nazgul.library.advertisement.ads.interstitial

import android.content.Context
import com.nazgul.library.advertisement.BuildConfig
import com.nazgul.library.advertisement.ads.BaseAd
import com.nazgul.library.advertisement.utils.Tracer

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