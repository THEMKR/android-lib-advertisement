package com.lory.library.advertisement.controller

import android.app.Activity
import com.lory.library.advertisement.ui.BannerAdView

class ControllerFactory {
    companion object {
        /**
         * Method to get the BannerAdvertisementController
         */
        fun getBannerController(activity: Activity, bannerAdView: BannerAdView): AdvertisementController {
            return BannerAdvertisementController(activity, bannerAdView)
        }

        /**
         * Method to get the InterstitialAdvertisementController
         */
        fun getInterstitialController(activity: Activity): AdvertisementController {
            return InterstitialAdvertisementController.getInstance(activity)
        }
    }
}