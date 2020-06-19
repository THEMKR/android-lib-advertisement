package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.Tracer
import com.lory.library.advertisement.utils.Utils

/**
 * @author THEMKR
 */
internal class AdMobInterstitial : Interstitial {
    companion object {
        private const val TAG: String = Constants.TAG + ".AdMobInterstitial"
    }

    private var ad: InterstitialAd
    private var listener = object : AdListener() {
        override fun onAdClicked() {
            Tracer.debug(TAG, "onAdClicked: ")
            super.onAdClicked()
            onAdvertisementListener.onAdvertisementClicked()
        }

        override fun onAdClosed() {
            Tracer.debug(TAG, "onAdClosed: ")
            super.onAdClosed()
            onAdvertisementListener.onAdvertisementFinished()
        }

        override fun onAdFailedToLoad(index: Int) {
            Tracer.debug(TAG, "onAdFailedToLoad: $index")
            super.onAdFailedToLoad(index)
            onAdvertisementListener.onAdvertisementFailed()
        }

        override fun onAdImpression() {
            Tracer.debug(TAG, "onAdImpression: ")
            super.onAdImpression()
        }

        override fun onAdLeftApplication() {
            Tracer.debug(TAG, "onAdLeftApplication: ")
            super.onAdLeftApplication()
            onAdvertisementListener.onAdvertisementFinished()
        }

        override fun onAdLoaded() {
            Tracer.debug(TAG, "onAdLoaded: ")
            super.onAdLoaded()
            onAdvertisementListener.onAdvertisementReady()
        }

        override fun onAdOpened() {
            Tracer.debug(TAG, "onAdOpened: ")
            super.onAdOpened()
        }
    }

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdvertisementListener
     */
    internal constructor(context: Context, adId: String, onAdvertisementListener: OnAdvertisementListener) : super(context, adId, onAdvertisementListener) {
        Tracer.debug(TAG, "Constructor: $adId")
        ad = InterstitialAd(context)
        ad.adUnitId = adId
        ad.adListener = listener
    }

    override fun fetchAd() {
        val testId = Utils.getMetaDataString(context, Constants.MetaDataKeys.ADMOB_TEST_ID).trim()
        Tracer.debug(TAG, "fetchAd: TEST [${testId}]")
        if (testId.isNotEmpty()) {
            ad.loadAd(AdRequest.Builder().addTestDevice(testId).build())
        } else {
            ad.loadAd(AdRequest.Builder().build())
        }
    }

    override fun shownAd() {
        Tracer.debug(TAG, "shownAd: ${isAdReady()}")
        if (isAdReady()) {
            ad.show()
            onAdvertisementListener.onAdvertisementShown()
        }
    }

    override fun isAdReady(): Boolean {
        Tracer.debug(TAG, "isAdReady: ${ad.isLoaded}")
        return ad.isLoaded
    }

    override fun finishAd() {
        Tracer.debug(TAG, "finishAd: ")
    }
}