package com.lory.library.advertisement.ads.banner

import android.app.Activity
import android.view.View
import android.widget.RelativeLayout
import com.inmobi.ads.InMobiAdRequest
import com.inmobi.ads.InMobiAdRequestStatus
import com.inmobi.ads.InMobiBanner
import com.inmobi.ads.listeners.BannerAdEventListener
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Tracer

internal class InMobiBanner : Banner {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".InMobiBanner"
    }

    private val adView: InMobiBanner
    private var isReady: Boolean = false
    private val inMobiListener = object : BannerAdEventListener() {
        override fun onAdClicked(p0: InMobiBanner?, p1: MutableMap<Any, Any>?) {
            Tracer.debug(TAG, "onAdClicked: ")
            onAdvertisementListener.onAdvertisementClicked()
        }

        override fun onAdLoadFailed(p0: InMobiBanner?, p1: InMobiAdRequestStatus?) {
            Tracer.debug(TAG, "onAdLoadFailed: ${p0}      ${p1?.message}")
            onAdvertisementListener.onAdvertisementFailed()
        }

        override fun onAdLoadSucceeded(p0: InMobiBanner?) {
            Tracer.debug(TAG, "onAdLoadSucceeded: ")
            isReady = true
            onAdvertisementListener.onAdvertisementReady()
        }

        override fun onAdDisplayed(p0: InMobiBanner?) {
            Tracer.debug(TAG, "onAdDisplayed: ")
            onAdvertisementListener.onAdvertisementShown()
        }

        override fun onAdDismissed(p0: InMobiBanner?) {
            Tracer.debug(TAG, "onAdDismissed: ")
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
        Tracer.debug(TAG, "fetchAd: ")
        adView.load(activity)
    }

    override fun shownAd() {
        Tracer.debug(TAG, "shownAd: ")
        if (isAdReady()) {
            bannerAdView.visibility = View.VISIBLE
        }
    }

    override fun isAdReady(): Boolean {
        Tracer.debug(TAG, "isAdReady: ")
        return isReady
    }

    override fun finishAd() {
        Tracer.debug(TAG, "finishAd: ")
    }
}