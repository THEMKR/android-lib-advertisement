package com.lory.library.advertisement.ads.banner

import android.app.Activity
import android.view.View
import android.widget.RelativeLayout
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
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
            Tracer.debug(TAG, "onAdClicked: ")
            super.onAdClicked()
            onAdListener.onAdClicked()
        }

        override fun onAdClosed() {
            Tracer.debug(TAG, "onAdClosed: ")
            super.onAdClosed()
            onAdListener.onAdFinished()
        }

        override fun onAdFailedToLoad(index: Int) {
            Tracer.debug(TAG, "onAdFailedToLoad: ")
            super.onAdFailedToLoad(index)
            onAdListener.onAdFailed()
        }

        override fun onAdImpression() {
            Tracer.debug(TAG, "onAdImpression: ")
            super.onAdImpression()
        }

        override fun onAdLeftApplication() {
            Tracer.debug(TAG, "onAdLeftApplication: ")
            super.onAdLeftApplication()
            onAdListener.onAdFinished()
        }

        override fun onAdLoaded() {
            Tracer.debug(TAG, "onAdLoaded: ")
            super.onAdLoaded()
            isReady = true
            onAdListener.onAdReady()
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
     * @param onAdListener
     * @param bannerAdView The AD container
     */
    internal constructor(activity: Activity, adId: String, onAdListener: OnAdListener, bannerAdView: BannerAdView) : super(activity, adId, onAdListener, bannerAdView) {
        Tracer.debug(TAG, "Constructor: ")
        adView = AdView(activity)
        adView.adSize = AdSize.BANNER
        adView.adListener = adMobAdListener
        bannerAdView.visibility = View.VISIBLE
        bannerAdView.removeAllViews()
        bannerAdView.addView(adView, RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT))
        adView.layoutParams.width = bannerAdView.layoutParams.width
        adView.layoutParams.height = bannerAdView.layoutParams.height
        adView.adUnitId = adId
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
        adView.loadAd(AdRequest.Builder().addTestDevice("7D7D0BB53322C0DB49F2F2CCE8550FA0").build())
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