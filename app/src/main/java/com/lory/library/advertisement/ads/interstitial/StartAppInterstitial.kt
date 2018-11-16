package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.utils.Tracer
import com.startapp.android.publish.adsCommon.Ad
import com.startapp.android.publish.adsCommon.StartAppAd
import com.startapp.android.publish.adsCommon.adListeners.AdEventListener

internal class StartAppInterstitial : Interstitial {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".StartAppInterstitial"
    }

    private var isAdReady: Boolean = false
    private val ad: StartAppAd
    private var listener = object : AdEventListener {
        override fun onFailedToReceiveAd(p0: Ad?) {
            Tracer.debug(TAG, "onFailedToReceiveAd: ")
        }

        override fun onReceiveAd(p0: Ad?) {
            Tracer.debug(TAG, "onReceiveAd: ")
            isAdReady = true
        }
    }

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdListener
     */
    constructor(context: Context, adId: String, onAdListener: OnAdListener) : super(context, adId, onAdListener) {
        Tracer.debug(TAG, "Constructor : ")
        ad = StartAppAd(context)
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
        ad.loadAd(StartAppAd.AdMode.FULLPAGE, listener)
    }

    override fun shownAd() {
        Tracer.debug(TAG, "shownAd: ")
        if (isAdReady()) {
            ad.showAd()
        }
    }

    override fun isAdReady(): Boolean {
        Tracer.debug(TAG, "isAdReady: ")
        return isAdReady
    }

    override fun finishAd() {
        Tracer.debug(TAG, "finishAd: ")
    }
}