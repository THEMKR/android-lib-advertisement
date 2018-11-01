package com.nazgul.library.advertisement.ads.banner

import android.content.Context
import android.view.ViewGroup
import com.nazgul.library.advertisement.BuildConfig
import com.nazgul.library.advertisement.ads.AD
import com.nazgul.library.advertisement.callback.OnAdListener
import com.nazgul.library.advertisement.utils.Tracer

abstract class Banner : AD {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".Banner"
    }

    protected val adContainer: ViewGroup

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdListener
     * @param adContainer The AD container
     */
    constructor(context: Context, adId: String, onAdListener: OnAdListener?, adContainer: ViewGroup) : super(context, adId, onAdListener) {
        this.adContainer = adContainer
        Tracer.debug(TAG, "Constructor : ")
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
        adContainer.removeAllViews()
    }
}