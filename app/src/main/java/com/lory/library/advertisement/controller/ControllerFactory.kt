package com.lory.library.advertisement.controller

import android.app.Activity
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ui.BannerAdView

class ControllerFactory {
    companion object {
        /**
         * Method to get the BannerAdvertisementController
         * @param activity
         * @param bannerAdView
         * @param onAdvertisementListener
         */
        fun getBannerController(activity: Activity, bannerAdView: BannerAdView, onAdvertisementListener: OnAdvertisementListener?): AdvertisementController {
            return BannerAdvertisementController(activity, bannerAdView, onAdvertisementListener)
        }

        /**
         * Method to get the InterstitialAdvertisementController
         * @param activity
         * @param onAdvertisementListener
         */
        fun getInterstitialController(activity: Activity, onAdvertisementListener: OnAdvertisementListener?): AdvertisementController {
            return InterstitialAdvertisementController.getInstance(activity, onAdvertisementListener)
        }
    }
}