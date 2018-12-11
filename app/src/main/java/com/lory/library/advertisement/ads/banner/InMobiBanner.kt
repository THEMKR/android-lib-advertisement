package com.lory.library.advertisement.ads.banner

import android.app.Activity
import android.view.View
import android.widget.RelativeLayout
import com.inmobi.ads.InMobiAdRequestStatus
import com.inmobi.ads.InMobiBanner
import com.inmobi.ads.listeners.BannerAdEventListener
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.Tracer

internal class InMobiBanner : Banner {
    companion object {
        private const val TAG: String = Constants.TAG + ".InMobiBanner"
    }

    private val adView: InMobiBanner
    private var isReady: Boolean = false
    private val inMobiListener = object : BannerAdEventListener() {
        override fun onAdClicked(p0: InMobiBanner?, p1: MutableMap<Any, Any>?) {
            Tracer.debug(TAG, "onAdClicked: $p0  $p1")
            onAdvertisementListener.onAdvertisementClicked()
        }

        override fun onAdLoadFailed(p0: InMobiBanner?, p1: InMobiAdRequestStatus?) {
            Tracer.debug(TAG, "onAdLoadFailed: $p0  $p1  ${p1?.message}  ${p1?.statusCode}")
            onAdvertisementListener.onAdvertisementFailed()
        }

        override fun onAdLoadSucceeded(p0: InMobiBanner?) {
            Tracer.debug(TAG, "onAdLoadSucceeded: $p0")
            isReady = true
            onAdvertisementListener.onAdvertisementReady()
        }

        override fun onAdDisplayed(p0: InMobiBanner?) {
            Tracer.debug(TAG, "onAdDisplayed: $p0")
            onAdvertisementListener.onAdvertisementShown()
        }

        override fun onAdDismissed(p0: InMobiBanner?) {
            Tracer.debug(TAG, "onAdDismissed: $p0")
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
        Tracer.debug(TAG, "Constructor : $adId")
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
        onAdvertisementListener.onAdvertisementFetching()
        adView.load(activity)
    }

    override fun shownAd() {
        Tracer.debug(TAG, "shownAd: ${isAdReady()}")
        if (isAdReady()) {
            bannerAdView.visibility = View.VISIBLE
            onAdvertisementListener.onAdvertisementShown()
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