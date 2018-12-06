package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.Utils

internal class AdMobInterstitial : Interstitial {
    private var ad: InterstitialAd
    private var listener = object : AdListener() {
        override fun onAdClicked() {
            super.onAdClicked()
            onAdvertisementListener.onAdvertisementClicked()
        }

        override fun onAdClosed() {
            super.onAdClosed()
            onAdvertisementListener.onAdvertisementFinished()
        }

        override fun onAdFailedToLoad(index: Int) {
            super.onAdFailedToLoad(index)
            onAdvertisementListener.onAdvertisementFailed()
        }

        override fun onAdImpression() {
            super.onAdImpression()
        }

        override fun onAdLeftApplication() {
            super.onAdLeftApplication()
            onAdvertisementListener.onAdvertisementFinished()
        }

        override fun onAdLoaded() {
            super.onAdLoaded()
            onAdvertisementListener.onAdvertisementReady()
        }

        override fun onAdOpened() {
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
        ad = InterstitialAd(context)
        ad.adUnitId = adId
        ad.adListener = listener
    }

    override fun fetchAd() {
        val testId = Utils.getMetaDataString(context, Constants.MetaDataKeys.ADMOB_TEST_ID).trim()
        if (testId.isNotEmpty()) {
            ad.loadAd(AdRequest.Builder().addTestDevice(testId).build())
        } else {
            ad.loadAd(AdRequest.Builder().build())
        }
    }

    override fun shownAd() {
        if (isAdReady()) {
            ad.show()
            onAdvertisementListener.onAdvertisementShown()
        }
    }

    override fun isAdReady(): Boolean {
        return ad.isLoaded
    }

    override fun finishAd() {
    }
}