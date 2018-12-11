package com.lory.library.advertisement.controller

import android.app.Activity
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.Tracer

class ControllerFactory {
    companion object {
        private const val TAG: String = Constants.TAG + ".ControllerFactory"

        /**
         * Method to get the BannerAdvertisementController
         * @param activity
         * @param bannerAdView
         * @param onAdvertisementListener
         */
        fun getBannerController(activity: Activity, bannerAdView: BannerAdView, onAdvertisementListener: OnAdvertisementListener?): AdvertisementController {
            Tracer.debug(TAG, "getBannerController: $activity  $bannerAdView  $onAdvertisementListener")
            return BannerAdvertisementController(activity, bannerAdView, onAdvertisementListener)
        }

        /**
         * Method to get the InterstitialAdvertisementController
         * @param activity
         * @param onAdvertisementListener
         */
        fun getInterstitialController(activity: Activity, onAdvertisementListener: OnAdvertisementListener?): AdvertisementController {
            Tracer.debug(TAG, "getInterstitialController: $activity  $onAdvertisementListener")
            return InterstitialAdvertisementController.getInstance(activity, onAdvertisementListener)
        }
    }
}