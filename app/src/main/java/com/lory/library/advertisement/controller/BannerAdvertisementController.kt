package com.lory.library.advertisement.controller

import android.app.Activity
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ads.Advertisement
import com.lory.library.advertisement.ads.banner.BannerFactory
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer

internal class BannerAdvertisementController : AdvertisementController {
    companion object {
        private const val TAG: String = Constants.TAG + ".BannerAdvertisementController"
    }

    private val activity: Activity
    private val bannerAdView: BannerAdView
    private var advertisementListenerCallback: OnAdvertisementListener? = null
    private var ad: Advertisement? = null
    private val onAdvertisementListener: OnAdvertisementListener = object : OnAdvertisementListener {
        override fun onAdvertisementFetching() {
            Tracer.debug(TAG, "onAdvertisementFetching: ")
            advertisementListenerCallback?.onAdvertisementFetching()
        }

        override fun onAdvertisementFailed() {
            Tracer.debug(TAG, "onAdvertisementFailed: ")
            advertisementListenerCallback?.onAdvertisementFailed()
            createAd()
        }

        override fun onAdvertisementReady() {
            Tracer.debug(TAG, "onAdvertisementReady: ")
            advertisementListenerCallback?.onAdvertisementReady()
            ad?.shownAd()
        }

        override fun onAdvertisementShown() {
            Tracer.debug(TAG, "onAdvertisementShown: ")
            advertisementListenerCallback?.onAdvertisementShown()
        }

        override fun onAdvertisementClicked() {
            Tracer.debug(TAG, "onAdvertisementClicked: ")
            advertisementListenerCallback?.onAdvertisementClicked()
            createAd()
        }

        override fun onAdvertisementCancel() {
            Tracer.debug(TAG, "onAdvertisementCancel: ")
            advertisementListenerCallback?.onAdvertisementCancel()
        }

        override fun onAdvertisementFinished() {
            Tracer.debug(TAG, "onAdvertisementFinished: ")
            advertisementListenerCallback?.onAdvertisementFinished()
        }
    }

    /**
     * Constructor
     * @param activity
     * @param bannerAdView
     * @param onAdvertisementListener
     */
    constructor(activity: Activity, bannerAdView: BannerAdView, onAdvertisementListener: OnAdvertisementListener?) {
        Tracer.debug(TAG, "Constructor: ")
        this.activity = activity
        this.bannerAdView = bannerAdView
        this.advertisementListenerCallback = onAdvertisementListener
    }

    /**
     * Method to show the Banner Ad
     */
    override fun showAd() {
        Tracer.debug(TAG, "showAd: $ad")
        ad?.fetchAd()
    }

    override fun createAd() {
        Tracer.debug(TAG, "createAd: ")
        try {
            val adProvider = AdProvider.getAdProvider(PrefData.getInt(activity, PrefData.Key.BANNER_PROVIDER))
            val adId: String = PrefData.getString(activity, PrefData.Key.BANNER_AD_ID)
            ad = BannerFactory.create(adProvider, activity, adId, onAdvertisementListener, bannerAdView)
        } catch (e: Exception) {
            Tracer.error(TAG, "createAd: " + e.message)
        }
    }
}