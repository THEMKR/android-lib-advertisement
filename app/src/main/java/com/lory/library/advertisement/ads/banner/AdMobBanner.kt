package com.lory.library.advertisement.ads.banner

import android.app.Activity
import android.view.View
import android.widget.RelativeLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.Tracer
import com.lory.library.advertisement.utils.Utils

internal class AdMobBanner : Banner {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdMobBanner"
    }

    private val adView: AdView
    private var isReady: Boolean = false
    private val adMobAdListener = object : AdListener() {
        override fun onAdClicked() {
            Tracer.debug(TAG, "onAdClicked: ")
            super.onAdClicked()
            onAdvertisementListener.onAdvertisementClicked()
        }

        override fun onAdClosed() {
            Tracer.debug(TAG, "onAdClosed: ")
            super.onAdClosed()
            onAdvertisementListener.onAdvertisementFinished()
        }

        override fun onAdFailedToLoad(index: Int) {
            Tracer.debug(TAG, "onAdFailedToLoad: ")
            super.onAdFailedToLoad(index)
            onAdvertisementListener.onAdvertisementFailed()
        }

        override fun onAdImpression() {
            Tracer.debug(TAG, "onAdImpression: ")
            super.onAdImpression()
        }

        override fun onAdLeftApplication() {
            Tracer.debug(TAG, "onAdLeftApplication: ")
            super.onAdLeftApplication()
            onAdvertisementListener.onAdvertisementFinished()
        }

        override fun onAdLoaded() {
            Tracer.debug(TAG, "onAdLoaded: ")
            super.onAdLoaded()
            bannerAdView.visibility = View.VISIBLE
            isReady = true
            onAdvertisementListener.onAdvertisementReady()
        }

        override fun onAdOpened() {
            Tracer.debug(TAG, "onAdOpened: ")
            super.onAdOpened()
        }
    }

    /**
     * Constructor
     * @param activity
     * @param adId
     * @param onAdvertisementListener
     * @param bannerAdView The AD container
     */
    internal constructor(activity: Activity, adId: String, onAdvertisementListener: OnAdvertisementListener, bannerAdView: BannerAdView) : super(activity, adId, onAdvertisementListener, bannerAdView) {
        Tracer.debug(TAG, "Constructor: ")
        adView = AdView(activity)
        adView.adSize = AdSize.BANNER
        adView.adListener = adMobAdListener
        bannerAdView.visibility = View.GONE
        bannerAdView.removeAllViews()
        bannerAdView.addView(adView, RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT))
        adView.layoutParams.width = bannerAdView.getBannerWidth()
        adView.layoutParams.height = bannerAdView.getBannerHeight()
        adView.adUnitId = adId
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
        val testId = Utils.getMetaDataString(activity, Constants.MetaDataKeys.ADMOB_TEST_ID).trim()
        if (testId.isNotEmpty()) {
            adView.loadAd(AdRequest.Builder().addTestDevice(testId).build())
        } else {
            adView.loadAd(AdRequest.Builder().build())
        }
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