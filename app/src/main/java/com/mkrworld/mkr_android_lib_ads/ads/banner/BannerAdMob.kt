package com.mkrworld.mkr_android_lib_ads.ads.banner

import android.content.Context
import android.view.ViewGroup
import com.mkrworld.mkr_android_lib_ads.BuildConfig
import com.mkrworld.mkr_android_lib_ads.utils.Tracer

class BannerAdMob : BaseBannerAd {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".BannerAdMob"
    }

    /**
     * Constructor
     * @param context
     * @param adContainer The Ad container
     */
    constructor(context: Context, adContainer: ViewGroup) : super(context, adContainer) {

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
}