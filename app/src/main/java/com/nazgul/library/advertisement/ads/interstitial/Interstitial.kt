package com.nazgul.library.advertisement.ads.interstitial

import android.content.Context
import com.nazgul.library.advertisement.BuildConfig
import com.nazgul.library.advertisement.ads.AD
import com.nazgul.library.advertisement.callback.OnAdListener
import com.nazgul.library.advertisement.utils.Tracer

abstract class Interstitial : AD {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".Banner"
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

}