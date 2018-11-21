package com.lory.library.advertisement.controller

import android.app.Activity
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.ads.banner.BannerFactory
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ads.Advertisement
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer

internal class BannerAdvertisementController : AdvertisementController {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".BannerAdvertisementController"
    }

    private val activity: Activity
    private val bannerAdView: BannerAdView
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
            ad?.shownAd()
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
        }
    }

    /**
     * Constructor
     * @param context
     * @param bannerAdView
     */
    constructor(activity: Activity, bannerAdView: BannerAdView) {
        Tracer.debug(TAG, "Constructor : ")
        this.activity = activity
        this.bannerAdView = bannerAdView
    }

    /**
     * Method to show the Banner Ad
     */
    override fun showAd() {
        Tracer.debug(TAG, "showAd: ")
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