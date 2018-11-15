package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.utils.Tracer

internal class AdMobInterstitial : Interstitial {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdMobInterstitial"
    }

    private var ad: InterstitialAd
    private var listener = object : AdListener() {
        override fun onAdClicked() {
            super.onAdClicked()
            onAdListener.onAdClicked()
        }

        override fun onAdClosed() {
            super.onAdClosed()
            onAdListener.onAdFinished()
        }

        override fun onAdFailedToLoad(index: Int) {
            super.onAdFailedToLoad(index)
            onAdListener.onAdFailed()
        }

        override fun onAdImpression() {
            super.onAdImpression()
        }

        override fun onAdLeftApplication() {
            super.onAdLeftApplication()
            onAdListener.onAdFinished()
        }

        override fun onAdLoaded() {
            super.onAdLoaded()
            onAdListener.onAdReady()
        }

        override fun onAdOpened() {
            super.onAdOpened()
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
        ad = InterstitialAd(context)
        ad.adUnitId = adId
        ad.adListener = listener
    }

    override fun fetchAd() {
        Tracer.debug(TAG, "fetchAd: ")
        ad.loadAd(AdRequest.Builder().build())
    }

    override fun shownAd() {
        Tracer.debug(TAG, "shownAd: ")
        if (isAdReady()) {
            ad.show()
            onAdListener.onAdShown()
        }
    }

    override fun isAdReady(): Boolean {
        Tracer.debug(TAG, "isAdReady: ")
        return ad.isLoaded
    }

    override fun finishAd() {
        Tracer.debug(TAG, "finishAd: ")
    }
}