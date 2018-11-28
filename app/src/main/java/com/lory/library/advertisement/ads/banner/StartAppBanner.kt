package com.lory.library.advertisement.ads.banner

import android.app.Activity
import android.view.View
import android.widget.RelativeLayout
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Tracer
import com.startapp.android.publish.ads.banner.BannerListener


internal class StartAppBanner : Banner {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".StartAppBanner"
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
            bannerAdView.visibility = View.VISIBLE
            onAdvertisementListener.onAdvertisementReady()
            isReady = true
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
        adView = com.startapp.android.publish.ads.banner.Banner(activity, startApListener)
        bannerAdView.visibility = View.GONE
        bannerAdView.removeAllViews()
        bannerAdView.addView(adView, RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT))
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
    }

    override fun shownAd() {
        Tracer.debug(TAG, "shownAd: ")
        if (isAdReady()) {
            adView.showBanner()
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