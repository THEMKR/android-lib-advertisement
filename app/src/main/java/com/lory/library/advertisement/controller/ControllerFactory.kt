package com.lory.library.advertisement.controller

import android.app.Activity
import com.lory.library.advertisement.callback.OnAdController
import com.lory.library.advertisement.ui.BannerAdView

class ControllerFactory {
    companion object {
        /**
         * Method to get the BannerAdController
         */
        fun getBannerController(activity: Activity, bannerAdView: BannerAdView): OnAdController {
            return BannerAdController(activity, bannerAdView)
        }

        /**
         * Method to get the InterstitialAdController
         */
        fun getInterstitialController(activity: Activity): OnAdController {
            return InterstitialAdController(activity)
        }
    }
}