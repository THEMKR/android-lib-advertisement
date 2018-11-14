package com.lory.library.advertisement.provider

import android.content.Context
import android.view.ViewGroup
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.R
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.utils.Tracer

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

    /**
     * Method to get the Width of the Banner
     */
    protected fun getBannerWidth():Int{
        Tracer.debug(TAG, "getBannerWidth: ")
        return context.resources.getDimensionPixelSize(R.dimen.banner_width)
    }

    /**
     * Method to get the Height of the Banner
     */
    protected fun getBannerHeight():Int{
        Tracer.debug(TAG, "getBannerHeight: ")
        return context.resources.getDimensionPixelSize(R.dimen.banner_height)
    }
}