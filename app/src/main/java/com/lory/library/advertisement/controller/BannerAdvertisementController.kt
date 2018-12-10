package com.lory.library.advertisement.controller

import android.app.Activity
import android.util.Log
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ads.Advertisement
import com.lory.library.advertisement.ads.banner.BannerFactory
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.PrefData

internal class BannerAdvertisementController : AdvertisementController {
    private val activity: Activity
    private val bannerAdView: BannerAdView
    private var advertisementListenerCallback: OnAdvertisementListener? = null
    private var ad: Advertisement? = null
    private val onAdvertisementListener: OnAdvertisementListener = object : OnAdvertisementListener {
        override fun onAdvertisementFetching() {
            advertisementListenerCallback?.onAdvertisementFetching()
        }

        override fun onAdvertisementFailed() {
            advertisementListenerCallback?.onAdvertisementFailed()
            createAd()
        }

        override fun onAdvertisementReady() {
            ad?.shownAd()
            advertisementListenerCallback?.onAdvertisementReady()
        }

        override fun onAdvertisementShown() {
            advertisementListenerCallback?.onAdvertisementShown()
        }

        override fun onAdvertisementClicked() {
            advertisementListenerCallback?.onAdvertisementClicked()
            createAd()
        }

        override fun onAdvertisementCancel() {
            advertisementListenerCallback?.onAdvertisementCancel()
        }

        override fun onAdvertisementFinished() {
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
        this.activity = activity
        this.bannerAdView = bannerAdView
        this.advertisementListenerCallback = onAdvertisementListener
    }

    /**
     * Method to show the Banner Ad
     */
    override fun showAd() {
        ad?.fetchAd()
    }

    override fun createAd() {
        try {
            val adProvider = AdProvider.getAdProvider(PrefData.getInt(activity, PrefData.Key.BANNER_PROVIDER))
            val adId: String = PrefData.getString(activity, PrefData.Key.BANNER_AD_ID)
            ad = BannerFactory.create(adProvider, activity, adId, onAdvertisementListener, bannerAdView)
        } catch (e: Exception) {
            Log.e("MKR", "BannerAdvertisementController.createAd: " + e.message)
        }
    }
}