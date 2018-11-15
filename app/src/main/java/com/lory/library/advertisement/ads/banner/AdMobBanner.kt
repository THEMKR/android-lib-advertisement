package com.lory.library.advertisement.ads.banner

import android.app.Activity
import android.view.View
import android.widget.FrameLayout
import android.widget.RelativeLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Tracer

internal class AdMobBanner : Banner {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdMobBanner"
    }

    private val adView: AdView
    private var isReady: Boolean = false
    private val adMobAdListener = object : AdListener() {
        override fun onAdClicked() {
            super.onAdClicked()
            onAdListener.onAdClicked()
        }

        override fun onAdClosed() {
            super.onAdClosed()
            onAdListener.onAdFinished()
        }

        override fun onAdFailedToLoad(index: Int) {
            super.onAdFailedToLoad(index)
            onAdListener.onAdFailed()
        }

        override fun onAdImpression() {
            super.onAdImpression()
        }

        override fun onAdLeftApplication() {
            super.onAdLeftApplication()
            onAdListener.onAdFinished()
        }

        override fun onAdLoaded() {
            super.onAdLoaded()
            isReady = true
            onAdListener.onAdReady()
        }

        override fun onAdOpened() {
            super.onAdOpened()
        }
    }

    /**
     * Constructor
     * @param activity
     * @param adId
     * @param onAdListener
     * @param bannerAdView The AD container
     */
    constructor(activity: Activity, adId: String, onAdListener: OnAdListener, bannerAdView: BannerAdView) : super(activity, adId, onAdListener, bannerAdView) {
        adView = AdView(activity)
        adView.adUnitId = adId
        adView.adListener = adMobAdListener
        bannerAdView.visibility = View.VISIBLE
        layoutAdView()
    }

    /**
     * Method to layout the Ad View
     */
    private fun layoutAdView() {
        Tracer.debug(TAG, "layoutAdView: ")
        bannerAdView.removeAllViews()
        val frameLayout = FrameLayout(activity)
        frameLayout.addView(adView, FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT))
        bannerAdView.addView(frameLayout, RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT))
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
        adView.loadAd(AdRequest.Builder().build())
    }

    override fun shownAd() {
        Tracer.debug(TAG, "shownAd: ")
        if (isReady) {
            bannerAdView.visibility = View.VISIBLE
        }
    }

    override fun finishAd() {
        Tracer.debug(TAG, "finishAd: ")
    }

    override fun isAdReady(): Boolean {
        Tracer.debug(TAG, "isAdReady: ")
        return isReady
    }
}