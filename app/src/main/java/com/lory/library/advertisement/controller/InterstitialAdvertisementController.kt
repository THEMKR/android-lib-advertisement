package com.lory.library.advertisement.controller

import android.content.Context
import android.util.Log
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
        private var advertisementListenerCallback: OnAdvertisementListener? = null

        /**
         * Method to get the Instance of this Controller
         */
        fun getInstance(context: Context, onAdvertisementListener: OnAdvertisementListener?): InterstitialAdvertisementController {
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
                Log.e("MKR", "InterstitialAdvertisementController.onAdvertisementFetching()  ${e.message}")
            }
        }

        override fun onAdvertisementFailed() {
            Tracer.debug(TAG, "onAdvertisementFailed: ")
            createAd()
            try {
                advertisementListenerCallback?.onAdvertisementFailed()
            } catch (e: Exception) {
                Log.e("MKR", "InterstitialAdvertisementController.onAdvertisementFailed()  ${e.message}")
            }
        }

        override fun onAdvertisementReady() {
            Tracer.debug(TAG, "onAdvertisementReady: ")
            try {
                advertisementListenerCallback?.onAdvertisementReady()
            } catch (e: Exception) {
                Log.e("MKR", "InterstitialAdvertisementController.onAdvertisementReady()  ${e.message}")
            }
        }

        override fun onAdvertisementShown() {
            Tracer.debug(TAG, "onAdvertisementShown: ")
            try {
                advertisementListenerCallback?.onAdvertisementShown()
            } catch (e: Exception) {
                Log.e("MKR", "InterstitialAdvertisementController.onAdvertisementShown()  ${e.message}")
            }
        }

        override fun onAdvertisementClicked() {
            Tracer.debug(TAG, "onAdvertisementClicked: ")
            try {
                advertisementListenerCallback?.onAdvertisementClicked()
            } catch (e: Exception) {
                Log.e("MKR", "InterstitialAdvertisementController.onAdvertisementClicked()  ${e.message}")
            }
        }

        override fun onAdvertisementCancel() {
            Tracer.debug(TAG, "onAdvertisementCancel: ")
            createAd()
            try {
                advertisementListenerCallback?.onAdvertisementCancel()
            } catch (e: Exception) {
                Log.e("MKR", "InterstitialAdvertisementController.onAdvertisementCancel()  ${e.message}")
            }
        }

        override fun onAdvertisementFinished() {
            Tracer.debug(TAG, "onAdvertisementFinished: ")
            createAd()
            try {
                advertisementListenerCallback?.onAdvertisementFinished()
            } catch (e: Exception) {
                Log.e("MKR", "InterstitialAdvertisementController.onAdvertisementFinished()  ${e.message}")
            }
        }
    }

    /**
     * Constructor
     * @param context
     * @param onAdvertisementListener
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
            ad = InterstitialFactory.create(adProvider, context, adId, advertisementListener)
            ad?.fetchAd()
        } catch (e: Exception) {
            Tracer.error(TAG, "createAd: " + e.message)
        }
    }
}