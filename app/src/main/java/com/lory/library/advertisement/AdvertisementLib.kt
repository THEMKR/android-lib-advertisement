package com.lory.library.advertisement

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import com.lory.library.advertisement.controller.ControllerFactory
import com.lory.library.advertisement.controller.SyncController
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer

class AdvertisementLib {

    companion object {
        private const val TAG: String = Constants.TAG + ".AdvertisementLib"

        private var permissions: Array<String> = arrayOf(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.RECEIVE_BOOT_COMPLETED
        )

        /**
         * Method to initialized the Lib on Activity Level
         * @param activity
         */
        fun initialize(activity: Activity) {
            Tracer.LOG_ENABLE = PrefData.getBoolean(activity, PrefData.Key.IS_SHOW_LOG)
            Tracer.debug(TAG, "initialize: ")
            if (!isHaveAllRequiredPermission(activity)) {
                throw Exception(Constants.ExceptionMessage.DOES_NOT_HAVE_REQUIRED_PERMISSION)
            }
            val syncController = SyncController(activity)
            // INIT THE PROVIDER DEFAULT VALUE
            if (!PrefData.getBoolean(activity, PrefData.Key.LIB_INITIALIZED)) {
                syncController.syncDefaultValue()
                PrefData.setBoolean(activity, PrefData.Key.LIB_INITIALIZED, true)
            }
            SDKInitializer.initialize(activity)
            initInterstitialAd(activity)
            syncController.syncServer()
        }

        /**
         * Method to show the Banner Ad
         * @param activity
         * @param bannerAdView Banner Ad dimen should be 320dp X 20dp
         * @param onAdvertisementListener Listen Ad Callback only [DONT TRY TO CUSTOMIZED FUNCTIONALITY. IT WILL HANDEL BY LIB]
         */
        fun showBannerAd(activity: Activity, bannerAdView: BannerAdView, onAdvertisementListener: OnAdvertisementListener?) {
            Tracer.LOG_ENABLE = PrefData.getBoolean(activity, PrefData.Key.IS_SHOW_LOG)
            Tracer.debug(TAG, "showBannerAd: ")
            if (!PrefData.getBoolean(activity, PrefData.Key.LIB_INITIALIZED)) {
                throw Exception(Constants.ExceptionMessage.LIB_NOT_INITIALIZED)
            }
            val adController = ControllerFactory.getBannerController(activity, bannerAdView, onAdvertisementListener)
            adController.createAd()
            adController.showAd()
        }

        /**
         * Method to show the Interstitial Ad
         * @param activity
         * @param onAdvertisementListener Listen Ad Callback only [DONT TRY TO CUSTOMIZED FUNCTIONALITY. IT WILL HANDEL BY LIB]
         */
        fun showInterstitialAd(activity: Activity, onAdvertisementListener: OnAdvertisementListener?) {
            Tracer.LOG_ENABLE = PrefData.getBoolean(activity, PrefData.Key.IS_SHOW_LOG)
            Tracer.debug(TAG, "showInterstitialAd: ")
            if (!PrefData.getBoolean(activity, PrefData.Key.LIB_INITIALIZED)) {
                throw Exception(Constants.ExceptionMessage.LIB_NOT_INITIALIZED)
            }
            ControllerFactory.getInterstitialController(activity, onAdvertisementListener).showAd()
        }

        /**
         * Method to set weather to show the logs or not
         * @param context
         * @param isLogging If TRUE then show log, Else disable log
         */
        fun logging(context: Activity, isLogging: Boolean) {
            Tracer.debug(TAG, "logging: $isLogging")
            PrefData.setBoolean(context, PrefData.Key.IS_SHOW_LOG, isLogging)
            Tracer.LOG_ENABLE = isLogging
        }

        //==========================================================================================
        //==========================================================================================
        //==========================================================================================
        //==========================================================================================
        //==========================================================================================

        /**
         * Method to show the Interstitial Ad
         * @param activity
         */
        private fun initInterstitialAd(activity: Activity) {
            if (!PrefData.getBoolean(activity, PrefData.Key.LIB_INITIALIZED)) {
                throw Exception(Constants.ExceptionMessage.LIB_NOT_INITIALIZED)
            }
            ControllerFactory.getInterstitialController(activity, null).createAd()
        }

        /**
         * Method to check weather the Current App Have All required Permission or not
         * @param activity
         */
        private fun isHaveAllRequiredPermission(activity: Activity): Boolean {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                for (permission in permissions) {
                    if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                        return false
                    }
                }
            }
            return true
        }
    }
}