package com.lory.library.advertisement.ads.banner

import android.app.Activity
import android.view.View
import android.widget.RelativeLayout
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.Tracer
import com.startapp.android.publish.ads.banner.BannerListener


internal class StartAppBanner : Banner {
    companion object {
        private const val TAG: String = Constants.TAG + ".StartAppBanner"
    }

    private val adView: com.startapp.android.publish.ads.banner.Banner
    private var isReady: Boolean = false
    private val startApListener = object : BannerListener {
        override fun onClick(view: View?) {
            Tracer.debug(TAG, "onClick: $view")
            onAdvertisementListener.onAdvertisementClicked()
        }

        override fun onFailedToReceiveAd(view: View?) {
            Tracer.debug(TAG, "onFailedToReceiveAd: $view")
            onAdvertisementListener.onAdvertisementFailed()
        }

        override fun onReceiveAd(view: View?) {
            Tracer.debug(TAG, "onReceiveAd: $view")
            isReady = true
            bannerAdView.visibility = View.VISIBLE
            onAdvertisementListener.onAdvertisementReady()
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
        Tracer.debug(TAG, "Constructor: $adId")
        adView = com.startapp.android.publish.ads.banner.Banner(activity, startApListener)
        bannerAdView.visibility = View.GONE
        bannerAdView.removeAllViews()
        bannerAdView.addView(adView, RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT))
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
        onAdvertisementListener.onAdvertisementFetching()
    }

    override fun shownAd() {
        Tracer.debug(TAG, "shownAd: ${isAdReady()}")
        if (isAdReady()) {
            adView.showBanner()
            onAdvertisementListener.onAdvertisementShown()
        }
    }

    override fun isAdReady(): Boolean {
        Tracer.debug(TAG, "isAdReady: $isReady")
        return isReady
    }

    override fun finishAd() {
        Tracer.debug(TAG, "finishAd: ")
    }
}