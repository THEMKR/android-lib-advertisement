package com.lory.library.advertisement.ads

import android.content.Context
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.utils.AdNetwork
import com.lory.library.advertisement.utils.Tracer

/**
 * Class to provide Interstitial Ad
 */
abstract class AdProvider : OnAdListener {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdNetwork"
    }

    protected var ad: AD? = null
    protected val context: Context

    /**
     * Constructor
     * @param context
     */
    constructor(context: Context) {
        Tracer.debug(TAG, "AdProvider: ")
        this.context = context
        initializedNewAd()
    }

    /**
     * Method to show Ad
     */
    open fun showAd() {
        Tracer.debug(TAG, "showAd: ")
        if (ad?.isAdLoaded() ?: false) {
            ad?.showAd()
        }
    }

    /**
     * Method to initialized Ad
     */
    protected fun initializedNewAd() {
        Tracer.debug(TAG, "initilizedNewAd: ")
        ad = getAd(getAdNetwork())
        ad?.fetchAd()
    }

    override fun onAdFailed() {
        Tracer.debug(TAG, "onAdFailed: ")
        initializedNewAd()
    }

    override fun onAdFetching() {
        Tracer.debug(TAG, "onAdFetching: ")
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
        initializedNewAd()
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