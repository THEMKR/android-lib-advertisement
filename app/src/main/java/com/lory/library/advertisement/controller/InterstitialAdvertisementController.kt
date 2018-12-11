package com.lory.library.advertisement.controller

import android.content.Context
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ads.Advertisement
import com.lory.library.advertisement.ads.interstitial.InterstitialFactory
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer

internal class InterstitialAdvertisementController : AdvertisementController {

    companion object {
        private const val TAG: String = Constants.TAG + ".InterstitialAdvertisementController"
        private var instance: InterstitialAdvertisementController? = null
        private var advertisementListenerCallback: OnAdvertisementListener? = null

        /**
         * Method to get the Instance of this Controller
         */
        fun getInstance(context: Context, onAdvertisementListener: OnAdvertisementListener?): InterstitialAdvertisementController {
            Tracer.debug(TAG, "getInstance: $onAdvertisementListener")
            advertisementListenerCallback = onAdvertisementListener
            if (instance == null) {
                instance = InterstitialAdvertisementController(context)
            }
            return instance!!
        }
    }

    private val context: Context
    private var ad: Advertisement? = null
    private val advertisementListener: OnAdvertisementListener = object : OnAdvertisementListener {
        override fun onAdvertisementFetching() {
            Tracer.debug(TAG, "onAdvertisementFetching: ")
            try {
                advertisementListenerCallback?.onAdvertisementFetching()
            } catch (e: Exception) {
                Tracer.error(TAG, "onAdvertisementFetching:  ${e.message}")
            }
        }

        override fun onAdvertisementFailed() {
            Tracer.debug(TAG, "onAdvertisementFailed: ")
            createAd()
            try {
                advertisementListenerCallback?.onAdvertisementFailed()
            } catch (e: Exception) {
                Tracer.error(TAG, "onAdvertisementFailed:  ${e.message}")
            }
        }

        override fun onAdvertisementReady() {
            Tracer.debug(TAG, "onAdvertisementReady: ")
            try {
                advertisementListenerCallback?.onAdvertisementReady()
            } catch (e: Exception) {
                Tracer.error(TAG, "onAdvertisementReady:  ${e.message}")
            }
        }

        override fun onAdvertisementShown() {
            Tracer.debug(TAG, "onAdvertisementShown: ")
            createAd()
            try {
                advertisementListenerCallback?.onAdvertisementShown()
            } catch (e: Exception) {
                Tracer.error(TAG, "onAdvertisementShown:  ${e.message}")
            }
        }

        override fun onAdvertisementClicked() {
            Tracer.debug(TAG, "onAdvertisementClicked: ")
            try {
                advertisementListenerCallback?.onAdvertisementClicked()
            } catch (e: Exception) {
                Tracer.error(TAG, "onAdvertisementClicked:  ${e.message}")
            }
        }

        override fun onAdvertisementCancel() {
            Tracer.debug(TAG, "onAdvertisementCancel: ")
            try {
                advertisementListenerCallback?.onAdvertisementCancel()
            } catch (e: Exception) {
                Tracer.error(TAG, "onAdvertisementCancel:  ${e.message}")
            }
        }

        override fun onAdvertisementFinished() {
            Tracer.debug(TAG, "onAdvertisementFinished: ")
            try {
                advertisementListenerCallback?.onAdvertisementFinished()
            } catch (e: Exception) {
                Tracer.error(TAG, "onAdvertisementFinished:  ${e.message}")
            }
        }
    }

    /**
     * Constructor
     * @param context
     */
    private constructor(context: Context) {
        this.context = context.applicationContext
    }

    /**
     * Method to show the Banner Ad
     */
    override fun showAd() {
        Tracer.debug(TAG, "showAd: $ad")
        if (ad?.isAdReady() == true) {
            ad?.shownAd()
        }
    }

    override fun createAd() {
        Tracer.debug(TAG, "createAd: ")
        try {
            val adProvider = AdProvider.getAdProvider(PrefData.getInt(context, PrefData.Key.INTERSTITIAL_PROVIDER))
            val adId: String = PrefData.getString(context, PrefData.Key.INTERSTITIAL_AD_ID)
            ad = InterstitialFactory.create(adProvider, context, adId, advertisementListener)
            ad?.fetchAd()
        } catch (e: Exception) {
            Tracer.error(TAG, "createAd:  " + e.message)
        }
    }
}