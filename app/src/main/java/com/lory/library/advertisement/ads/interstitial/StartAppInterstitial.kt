package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.utils.Tracer
import com.startapp.android.publish.adsCommon.StartAppAd

internal class StartAppInterstitial : Interstitial {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdMobInterstitial"
    }

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdListener
     */
    constructor(context: Context, adId: String, onAdListener: OnAdListener) : super(context, adId, onAdListener) {
        Tracer.debug(TAG, "Constructor : ")
        StartAppAd.disableAutoInterstitial()
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
        StartAppAd.showAd(context)
    }

    override fun shownAd() {
        Tracer.debug(TAG, "shownAd: ")
    }

    override fun isAdReady(): Boolean {
        Tracer.debug(TAG, "isAdReady: ")
        return true
    }

    override fun finishAd() {
        Tracer.debug(TAG, "finishAd: ")
    }
}