package com.lory.library.advertisement.ads.banner

import android.content.Context
import android.view.ViewGroup
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.utils.Tracer

internal class BannerAdMob : Banner {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".BannerAdMob"
    }

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdListener
     * @param adContainer The AD container
     */
    constructor(context: Context, adId: String, onAdListener: OnAdListener?, adContainer: ViewGroup) : super(context, adId, onAdListener, adContainer) {

    }

    override fun showAd() {
        Tracer.debug(TAG, "showAd: ")
    }

    override fun cancelAd() {
        Tracer.debug(TAG, "cancelAd: ")
    }

    override fun fetchAd() {
        super.fetchAd()
        Tracer.debug(TAG, "fetchAd: ")
    }

    override fun isAdLoaded(): Boolean {
        Tracer.debug(TAG, "isAdLoaded: ")
        return true
    }
}