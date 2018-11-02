package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.ads.AD
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.utils.Tracer

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