package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.Tracer
import com.startapp.android.publish.adsCommon.Ad
import com.startapp.android.publish.adsCommon.StartAppAd
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener

internal class StartAppInterstitial : Interstitial {
    companion object {
        private const val TAG: String = Constants.TAG + ".StartAppInterstitial"
    }

    private var isAdReady: Boolean = false
    private val ad: StartAppAd
    private var listener = object : AdEventListener {
        override fun onFailedToReceiveAd(p0: Ad?) {
            Tracer.debug(TAG, "onFailedToReceiveAd: $p0")
            onAdvertisementListener.onAdvertisementFailed()
        }

        override fun onReceiveAd(p0: Ad?) {
            Tracer.debug(TAG, "onReceiveAd: $p0")
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
        Tracer.debug(TAG, "Constructor : $adId")
        ad = StartAppAd(context)
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
        ad.loadAd(StartAppAd.AdMode.FULLPAGE, listener)
    }

    override fun shownAd() {
        Tracer.debug(TAG, "shownAd: ${isAdReady()}")
        if (isAdReady()) {
            ad.showAd()
            onAdvertisementListener.onAdvertisementShown()
        }
    }

    override fun isAdReady(): Boolean {
        Tracer.debug(TAG, "isAdReady: $isAdReady")
        return isAdReady
    }

    override fun finishAd() {
        Tracer.debug(TAG, "finishAd: ")
    }
}