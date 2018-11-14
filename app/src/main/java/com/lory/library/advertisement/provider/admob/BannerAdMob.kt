package com.lory.library.advertisement.provider.admob

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.provider.Banner
import com.lory.library.advertisement.utils.Tracer

internal class BannerAdMob : Banner {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".BannerAdMob"
    }

    private val adView: AdView
    private var isLoaded: Boolean = false
    private val listener = object : AdListener() {
        override fun onAdClicked() {
            super.onAdClicked()
            onAdListener?.onAdClicked()
        }

        override fun onAdClosed() {
            super.onAdClosed()
            onAdListener?.onAdFinished()
        }

        override fun onAdFailedToLoad(index: Int) {
            super.onAdFailedToLoad(index)
            onAdListener?.onAdFailed()
        }

        override fun onAdImpression() {
            super.onAdImpression()
        }

        override fun onAdLeftApplication() {
            super.onAdLeftApplication()
            onAdListener?.onAdFinished()
        }

        override fun onAdLoaded() {
            super.onAdLoaded()
            isLoaded = true
            onAdListener?.onAdReady()
            adContainer.visibility = View.VISIBLE
        }

        override fun onAdOpened() {
            super.onAdOpened()
        }
    }

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdListener
     * @param adContainer The AD container
     */
    constructor(context: Context, adId: String, onAdListener: OnAdListener?, adContainer: ViewGroup) : super(context, adId, onAdListener, adContainer) {
        adView = AdView(context)
        adView.adUnitId = adId
        adView.adListener = listener
        adContainer.visibility = View.GONE
        layoutAdView()
    }

    /**
     * Method to layout the Ad View
     */
    private fun layoutAdView() {
        Tracer.debug(TAG, "layoutAdView: ")
        adContainer.removeAllViews()
        val frameLayout = FrameLayout(context)
        frameLayout.addView(adView, FrameLayout.LayoutParams(getBannerWidth(), getBannerHeight()))
        adContainer.addView(frameLayout, ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT))
    }

    override fun showAd() {
        Tracer.debug(TAG, "showAd: ")
        if (isLoaded) {
            adContainer.visibility = View.VISIBLE
        }
    }

    override fun cancelAd() {
        Tracer.debug(TAG, "cancelAd: ")
    }

    override fun fetchAd() {
        super.fetchAd()
        Tracer.debug(TAG, "fetchAd: ")
        adView.loadAd(AdRequest.Builder().build())
    }

    override fun isAdLoaded(): Boolean {
        Tracer.debug(TAG, "isAdLoaded: ")
        return isLoaded
    }
}