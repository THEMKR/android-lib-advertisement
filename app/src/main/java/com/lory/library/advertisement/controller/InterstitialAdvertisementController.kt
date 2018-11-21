package com.lory.library.advertisement.controller

import android.content.Context
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ads.Advertisement
import com.lory.library.advertisement.ads.interstitial.InterstitialFactory
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer

internal class InterstitialAdvertisementController : AdvertisementController {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".InterstitialAdvertisementController"
        private var instance: InterstitialAdvertisementController? = null

        /**
         * Method to get the Instance of this Controller
         */
        fun getInstance(context: Context): InterstitialAdvertisementController {
            if (instance == null) {
                instance = InterstitialAdvertisementController(context)
            }
            return instance!!
        }
    }

    private val context: Context
    private var ad: Advertisement? = null
    private val onAdvertisementListener: OnAdvertisementListener = object : OnAdvertisementListener {
        override fun onAdvertisementFetching() {
            Tracer.debug(TAG, "onAdvertisementFetching: ")
        }

        override fun onAdvertisementFailed() {
            Tracer.debug(TAG, "onAdvertisementFailed: ")
            createAd()
        }

        override fun onAdvertisementReady() {
            Tracer.debug(TAG, "onAdvertisementReady: ")
        }

        override fun onAdvertisementShown() {
            Tracer.debug(TAG, "onAdvertisementShown: ")
        }

        override fun onAdvertisementClicked() {
            Tracer.debug(TAG, "onAdvertisementClicked: ")
        }

        override fun onAdvertisementCancel() {
            Tracer.debug(TAG, "onAdvertisementCancel: ")
            createAd()
        }

        override fun onAdvertisementFinished() {
            Tracer.debug(TAG, "onAdvertisementFinished: ")
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
        try {
            val adProvider = AdProvider.getAdProvider(PrefData.getInt(context, PrefData.Key.INTERSTITIAL_PROVIDER))
            val adId: String = PrefData.getString(context, PrefData.Key.INTERSTITIAL_AD_ID)
            ad = InterstitialFactory.create(adProvider, context, adId, onAdvertisementListener)
            ad?.fetchAd()
        } catch (e: Exception) {
            Tracer.error(TAG, "createAd: " + e.message)
        }
    }
}