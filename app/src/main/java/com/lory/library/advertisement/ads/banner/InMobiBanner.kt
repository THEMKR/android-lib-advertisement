package com.lory.library.advertisement.ads.banner

import android.app.Activity
import android.view.View
import android.widget.RelativeLayout
import com.inmobi.ads.InMobiAdRequestStatus
import com.inmobi.ads.InMobiBanner
import com.inmobi.ads.listeners.BannerAdEventListener
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ui.BannerAdView

internal class InMobiBanner : Banner {
    private val adView: InMobiBanner
    private var isReady: Boolean = false
    private val inMobiListener = object : BannerAdEventListener() {
        override fun onAdClicked(p0: InMobiBanner?, p1: MutableMap<Any, Any>?) {
            onAdvertisementListener.onAdvertisementClicked()
        }

        override fun onAdLoadFailed(p0: InMobiBanner?, p1: InMobiAdRequestStatus?) {
            onAdvertisementListener.onAdvertisementFailed()
        }

        override fun onAdLoadSucceeded(p0: InMobiBanner?) {
            isReady = true
            onAdvertisementListener.onAdvertisementReady()
        }

        override fun onAdDisplayed(p0: InMobiBanner?) {
            onAdvertisementListener.onAdvertisementShown()
        }

        override fun onAdDismissed(p0: InMobiBanner?) {
            onAdvertisementListener.onAdvertisementFinished()
        }
    }

    /**
     * Constructor
     * @param activity
     * @param adId
     * @param onAdvertisementListener
     * @param bannerAdView
     */
    internal constructor(activity: Activity, adId: String, onAdvertisementListener: OnAdvertisementListener, bannerAdView: BannerAdView) : super(activity, adId, onAdvertisementListener, bannerAdView) {
        adView = InMobiBanner(activity, adId.toLong())
        adView.setEnableAutoRefresh(true)
        adView.setRefreshInterval(60)
        bannerAdView.visibility = View.VISIBLE
        bannerAdView.removeAllViews()
        bannerAdView.addView(adView, RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT))
        adView.setBannerSize(bannerAdView.getBannerWidth(), bannerAdView.getBannerHeight())
        adView.setListener(inMobiListener)
    }

    override fun fetchAd() {
        adView.load(activity)
    }

    override fun shownAd() {
        if (isAdReady()) {
            bannerAdView.visibility = View.VISIBLE
        }
    }

    override fun isAdReady(): Boolean {
        return isReady
    }

    override fun finishAd() {
    }
}