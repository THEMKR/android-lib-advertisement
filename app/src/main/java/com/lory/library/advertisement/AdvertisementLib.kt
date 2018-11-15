package com.lory.library.advertisement

import android.app.Activity
import android.content.Context
import com.lory.library.advertisement.controller.AdControllerProvider
import com.lory.library.advertisement.controller.BannerAdController
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer
import com.lory.library.advertisement.utils.Utils

class AdvertisementLib {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdvertisementLib"

        /**
         * Method to initialized the Lib
         */
        fun initialize(activity: Activity) {
            Tracer.debug(TAG, "initialize: ")
            if (PrefData.getBoolean(activity, PrefData.Key.LIB_INITIALIZED)) {
                initDefaultValue(activity)
                PrefData.setBoolean(activity, PrefData.Key.LIB_INITIALIZED, true)
            }
            SDKInitializer.initialize(activity)
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
            AdControllerProvider.getBannerAdController(activity, bannerAdView).showAd()
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
            AdControllerProvider.getInterstitialAdController(activity).showAd()
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
    }
}