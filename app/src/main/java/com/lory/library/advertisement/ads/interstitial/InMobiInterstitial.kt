package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.inmobi.ads.InMobiAdRequestStatus
import com.inmobi.ads.InMobiInterstitial
import com.inmobi.ads.listeners.InterstitialAdEventListener
import com.lory.library.advertisement.OnAdvertisementListener

internal class InMobiInterstitial : Interstitial {
    private val ad: InMobiInterstitial
    private val inMobiListener = object : InterstitialAdEventListener() {
        override fun onAdClicked(p0: InMobiInterstitial?, p1: MutableMap<Any, Any>?) {
            super.onAdClicked(p0, p1)
            onAdvertisementListener.onAdvertisementClicked()
        }

        override fun onAdDismissed(p0: InMobiInterstitial?) {
            super.onAdDismissed(p0)
            onAdvertisementListener.onAdvertisementFinished()
        }

        override fun onAdLoadFailed(p0: InMobiInterstitial?, p1: InMobiAdRequestStatus?) {
            super.onAdLoadFailed(p0, p1)
            onAdvertisementListener.onAdvertisementFailed()
        }

        override fun onAdDisplayFailed(p0: InMobiInterstitial?) {
            super.onAdDisplayFailed(p0)
            onAdvertisementListener.onAdvertisementFailed()
        }

        override fun onAdDisplayed(p0: InMobiInterstitial?) {
            super.onAdDisplayed(p0)
            onAdvertisementListener.onAdvertisementShown()
        }

        override fun onAdLoadSucceeded(p0: InMobiInterstitial?) {
            super.onAdLoadSucceeded(p0)
            onAdvertisementListener.onAdvertisementReady()
        }

        override fun onUserLeftApplication(p0: InMobiInterstitial?) {
            super.onUserLeftApplication(p0)
            onAdvertisementListener.onAdvertisementFinished()
        }

        override fun onAdReceived(p0: InMobiInterstitial?) {
            super.onAdReceived(p0)
            onAdvertisementListener.onAdvertisementReady()
        }
    }

    /**
     * Constructor
     * @param context
     * @param adId
     * @param onAdvertisementListener
     */
    internal constructor(context: Context, adId: String, onAdvertisementListener: OnAdvertisementListener) : super(context, adId, onAdvertisementListener) {
        ad = InMobiInterstitial(context, adId.toLong(), inMobiListener)
    }

    override fun fetchAd() {
        ad.load()
    }

    override fun shownAd() {
        if (isAdReady()) {
            ad.show()
        }
    }

    override fun isAdReady(): Boolean {
        return ad.isReady
    }

    override fun finishAd() {
    }

}