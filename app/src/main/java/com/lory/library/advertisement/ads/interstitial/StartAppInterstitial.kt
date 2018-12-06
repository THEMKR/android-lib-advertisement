package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.OnAdvertisementListener
import com.startapp.android.publish.adsCommon.Ad
import com.startapp.android.publish.adsCommon.StartAppAd
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener

internal class StartAppInterstitial : Interstitial {
    private var isAdReady: Boolean = false
    private val ad: StartAppAd
    private var listener = object : AdEventListener {
        override fun onFailedToReceiveAd(p0: Ad?) {
        }

        override fun onReceiveAd(p0: Ad?) {
            isAdReady = true
        }
    }

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdvertisementListener
     */
    internal constructor(context: Context, adId: String, onAdvertisementListener: OnAdvertisementListener) : super(context, adId, onAdvertisementListener) {
        ad = StartAppAd(context)
    }

    override fun fetchAd() {
        ad.loadAd(StartAppAd.AdMode.FULLPAGE, listener)
    }

    override fun shownAd() {
        if (isAdReady()) {
            ad.showAd()
        }
    }

    override fun isAdReady(): Boolean {
        return isAdReady
    }

    override fun finishAd() {
    }
}