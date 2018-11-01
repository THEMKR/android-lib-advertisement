package com.nazgul.library.advertisement.ads

import android.content.Context
import com.nazgul.library.advertisement.BuildConfig
import com.nazgul.library.advertisement.callback.OnAdListener
import com.nazgul.library.advertisement.utils.AdNetwork
import com.nazgul.library.advertisement.utils.Tracer

/**
 * Class to provide Interstitial Ad
 */
abstract class AdProvider : OnAdListener {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdNetwork"
    }

    protected lateinit var ad: AD
    protected val context: Context

    /**
     * Constructor
     * @param context
     */
    constructor(context: Context) {
        Tracer.debug(TAG, "AdProvider: ")
        this.context = context
        ad = getAd(getAdNetwork())
        ad.fetchAd()
    }

    /**
     * Method to show Ad
     */
    open fun showAd() {
        Tracer.debug(TAG, "showAd: ")
        if (ad.isAdLoaded()) {
            ad.showAd()
        }
    }

    override fun onAdFetching() {
        Tracer.debug(TAG, "onAdFetching: ")
    }

    override fun onAdFailed() {
        Tracer.debug(TAG, "onAdFailed: ")
        onAdFinished()
    }

    override fun onAdReady() {
        Tracer.debug(TAG, "onAdReady: ")
    }

    override fun onAdShown() {
        Tracer.debug(TAG, "onAdShown: ")
    }

    override fun onAdClicked() {
        Tracer.debug(TAG, "onAdClicked: ")
    }

    override fun onAdCancel() {
        Tracer.debug(TAG, "onAdCancel: ")
    }

    override fun onAdFinished() {
        Tracer.debug(TAG, "onAdFinished: ")
        ad = getAd(getAdNetwork())
        ad.fetchAd()
    }

    /**
     * Method to get the AD correspond to the network
     * @param adNetwork
     * @return AD
     */
    internal abstract fun getAd(adNetwork: AdNetwork): AD

    /**
     * Method to get the Ad Network
     * @return Ad Network Supported
     */
    internal abstract fun getAdNetwork(): AdNetwork
}