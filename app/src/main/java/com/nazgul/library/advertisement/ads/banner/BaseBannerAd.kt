package com.nazgul.library.advertisement.ads.banner

import android.content.Context
import android.view.ViewGroup
import com.nazgul.library.advertisement.BuildConfig
import com.nazgul.library.advertisement.ads.BaseAd
import com.nazgul.library.advertisement.utils.Tracer

abstract class BaseBannerAd : BaseAd {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".BaseBannerAd"
    }

    protected val adContainer: ViewGroup

    /**
     * Constructor
     * @param context
     * @param adContainer The Ad container
     */
    constructor(context: Context, adContainer: ViewGroup) :super(context){
        this.adContainer = adContainer
        Tracer.debug(TAG, "Constructor : ")
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
        adContainer.removeAllViews()
    }
}