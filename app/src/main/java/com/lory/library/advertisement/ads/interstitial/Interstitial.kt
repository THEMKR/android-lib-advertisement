package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.callback.OnAdProvider
import com.lory.library.advertisement.utils.Tracer

internal abstract class Interstitial : OnAdProvider {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".Interstitial"
    }

    protected val context: Context
    protected val adId: String
        get() {
            return (field ?: "").trim()
        }
    protected val onAdListener: OnAdListener

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdListener
     */
    internal constructor(context: Context, adId: String, onAdListener: OnAdListener) {
        Tracer.debug(TAG, "Constructor : ")
        this.context = context
        this.adId = adId
        this.onAdListener = onAdListener
    }

}