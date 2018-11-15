package com.lory.library.advertisement

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import com.lory.library.advertisement.controller.ControllerFactory
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer
import com.lory.library.advertisement.utils.Utils

class AdvertisementLib {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdvertisementLib"
        private var permissions: Array<String> = arrayOf(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.RECEIVE_BOOT_COMPLETED,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE
        )

        /**
         * Method to initialized the Lib
         * @param activity
         * @see [ALL REQUIRED PERMISSIONS]
         * @see Manifest.permission.INTERNET
         * @see Manifest.permission.ACCESS_NETWORK_STATE
         * @see Manifest.permission.ACCESS_COARSE_LOCATION
         * @see Manifest.permission.ACCESS_FINE_LOCATION
         * @see Manifest.permission.RECEIVE_BOOT_COMPLETED
         * @see Manifest.permission.BLUETOOTH
         * @see Manifest.permission.INTERNET
         * @see Manifest.permission.ACCESS_WIFI_STATE
         * @see Manifest.permission.ACCESS_NETWORK_STATE
         */
        fun initialize(activity: Activity) {
            Tracer.debug(TAG, "initialize: ")
            if (!isHaveAllRequiredPermission(activity)) {
                throw Exception(Constants.ExceptionMessage.DOES_NOT_HAVE_REQUIRED_PERMISSION)
            }
            if (PrefData.getBoolean(activity, PrefData.Key.LIB_INITIALIZED)) {
                initDefaultValue(activity)
                PrefData.setBoolean(activity, PrefData.Key.LIB_INITIALIZED, true)
            }
            SDKInitializer.initialize(activity)
            showInterstitialAd(activity)
        }

        /**
         * Method to show the Banner Ad
         * @param activity
         * @param bannerAdView Banner Ad dimen should be 320dp X 20dp
         */
        fun showBannerAd(activity: Activity, bannerAdView: BannerAdView) {
            Tracer.debug(TAG, "showBannerAd: ")
            if (!PrefData.getBoolean(activity, PrefData.Key.LIB_INITIALIZED)) {
                throw Exception(Constants.ExceptionMessage.LIB_NOT_INITIALIZED)
            }
            ControllerFactory.getBannerController(activity, bannerAdView).showAd()
        }

        /**
         * Method to show the Interstitial Ad
         * @param activity
         */
        fun showInterstitialAd(activity: Activity) {
            Tracer.debug(TAG, "showInterstitialAd: ")
            if (!PrefData.getBoolean(activity, PrefData.Key.LIB_INITIALIZED)) {
                throw Exception(Constants.ExceptionMessage.LIB_NOT_INITIALIZED)
            }
            ControllerFactory.getInterstitialController(activity).showAd()
        }

        /**
         * Method to initialized the Default Value of Libs
         */
        private fun initDefaultValue(context: Context) {
            Tracer.debug(TAG, "initDefaultValue: ")
            // SET DEFAULT AD PROVIDER
            PrefData.setInt(context, PrefData.Key.BANNER_PROVIDER, Utils.getMetaDataInt(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_INDEX))
            PrefData.setInt(context, PrefData.Key.INTERSTITIAL_PROVIDER, Utils.getMetaDataInt(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_INDEX))
            // SET DEFAULT AD ID
            PrefData.setString(context, PrefData.Key.BANNER_AD_ID, Utils.getMetaDataString(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_BANNER_ID))
            PrefData.setString(context, PrefData.Key.INTERSTITIAL_AD_ID, Utils.getMetaDataString(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_INTERSTITIAL_ID))
            // SET DEFAULT APP ID
            PrefData.setString(context, PrefData.Key.APP_ID_BANNER, Utils.getMetaDataString(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_APP_ID))
            PrefData.setString(context, PrefData.Key.APP_ID_INTERSTITIAL, Utils.getMetaDataString(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_APP_ID))
        }

        /**
         * Method to check weather the Current App Have All required Permission or not
         * @param activity
         */
        private fun isHaveAllRequiredPermission(activity: Activity): Boolean {
            Tracer.debug(TAG, "isHaveAllRequiredPermission: ")
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