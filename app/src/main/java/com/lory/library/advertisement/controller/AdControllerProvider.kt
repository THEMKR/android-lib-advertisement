package com.lory.library.advertisement.controller

import android.app.Activity
import com.lory.library.advertisement.callback.OnAdController
import com.lory.library.advertisement.ui.BannerAdView

class AdControllerProvider {
    companion object {
        /**
         * Method to get the BannerAdController
         */
        fun getBannerAdController(activity: Activity, bannerAdView: BannerAdView): OnAdController {
            return BannerAdController(activity, bannerAdView)
        }

        /**
         * Method to get the InterstitialAdController
         */
        fun getInterstitialAdController(activity: Activity): OnAdController {
            return InterstitialAdController(activity)
        }
    }
}