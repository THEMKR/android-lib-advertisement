package com.lory.library.advertisement.controller

import android.content.Context
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.ads.interstitial.InterstitialFactory
import com.lory.library.advertisement.callback.OnAdController
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.callback.OnAdProvider
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer

internal class InterstitialAdController : OnAdController {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".InterstitialAdController"
        private var instance: InterstitialAdController? = null

        /**
         * Method to get the Instance of this Controller
         */
        fun getInstance(context: Context): InterstitialAdController {
            if (instance == null) {
                instance = InterstitialAdController(context)
            }
            return instance!!
        }
    }

    private val context: Context
    private var ad: OnAdProvider? = null
    private val onAdListener: OnAdListener = object : OnAdListener {
        override fun onAdFetching() {
            Tracer.debug(TAG, "onAdFetching: ")
        }

        override fun onAdFailed() {
            Tracer.debug(TAG, "onAdFailed: ")
            createAd()
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
            createAd()
        }

        override fun onAdFinished() {
            Tracer.debug(TAG, "onAdFinished: ")
            createAd()
        }
    }

    /**
     * Constructor
     * @param context
     * @param bannerAdView
     */
    private constructor(context: Context) {
        Tracer.debug(TAG, "Constructor : ")
        this.context = context.applicationContext
    }

    /**
     * Method to show the Banner Ad
     */
    override fun showAd() {
        Tracer.debug(TAG, "showAd: ")
        if (ad?.isAdReady() == true) {
            ad?.shownAd()
            createAd()
        }
    }

    override fun createAd() {
        Tracer.debug(TAG, "fetchAd: ")
        val adProvider = AdProvider.getAdProvider(PrefData.getInt(context, PrefData.Key.INTERSTITIAL_PROVIDER))
        val adId: String = PrefData.getString(context, PrefData.Key.INTERSTITIAL_AD_ID)
        ad = InterstitialFactory.create(adProvider, context, adId, onAdListener)
        ad?.fetchAd()
    }
}