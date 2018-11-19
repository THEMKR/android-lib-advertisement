package com.lory.library.advertisement.controller

import android.app.Activity
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.ads.banner.BannerFactory
import com.lory.library.advertisement.callback.OnAdController
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.callback.OnAdProvider
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer

internal class BannerAdController : OnAdController {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".BannerAdController"
    }

    private val activity: Activity
    private val bannerAdView: BannerAdView
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
            ad?.shownAd()
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
        val adProvider = AdProvider.getAdProvider(PrefData.getInt(activity, PrefData.Key.INTERSTITIAL_PROVIDER))
        val adId: String = PrefData.getString(activity, PrefData.Key.INTERSTITIAL_AD_ID)
        ad = BannerFactory.create(adProvider, activity, adId, onAdListener, bannerAdView)
    }
}