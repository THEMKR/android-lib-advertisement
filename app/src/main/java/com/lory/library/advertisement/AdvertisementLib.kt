package com.lory.library.advertisement

import android.app.Activity
import android.view.ViewGroup
import com.google.android.gms.ads.MobileAds
import com.lory.library.advertisement.utils.*
import com.startapp.android.publish.adsCommon.StartAppSDK

class AdvertisementLib {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdvertisementLib"

        /**
         * Method to initialized the Lib
         */
        fun initialize(activity: Activity) {
            Tracer.debug(TAG, "initialize: ")
            PrefData.setBoolean(activity, PrefData.Key.INITIALIZED, true)
            // INTERSTITIAL
            var interAppId: String = PrefData.getString(activity, PrefData.Key.INTERSTITIAL_AD_ID).trim()
            if (interAppId.isEmpty()) {
                interAppId = Utils.getMetaDataString(activity, Constants.MetaDataKeys.AD_MOB_APP_ID)
            }
            val interAdProvider: AdProvider = AdProvider.getAdProvider(PrefData.getInt(activity, PrefData.Key.INTERSTITIAL_PROVIDER))

            // BANNER
            var bannerAppId: String = PrefData.getString(activity, PrefData.Key.BANNER_AD_ID).trim()
            if (bannerAppId.isEmpty()) {
                bannerAppId = Utils.getMetaDataString(activity, Constants.MetaDataKeys.AD_MOB_APP_ID)
            }
            val bannerAdProvider: AdProvider = AdProvider.getAdProvider(PrefData.getInt(activity, PrefData.Key.BANNER_PROVIDER))

            // INIT LIB
            initAdNetworkProvider(activity, interAdProvider, interAppId)
            initAdNetworkProvider(activity, bannerAdProvider, bannerAppId)
        }

        /**
         * Method to show the Banner Ad
         * @param activity
         * @param adContainer Banner Ad dimen should be 320dp X 20dp
         */
        fun showBannerAd(activity: Activity, adContainer: ViewGroup) {
            Tracer.debug(TAG, "showBannerAd: ")
            if (!PrefData.getBoolean(activity, PrefData.Key.INITIALIZED)) {
                throw Exception(Constants.ExceptionMessage.LIB_NOT_INITIALIZED)
            }
        }

        /**
         * Method to show the Interstitial Ad
         * @param activity
         */
        fun showInterstitialAd(activity: Activity) {
            Tracer.debug(TAG, "showInterstitialAd: ")
            if (!PrefData.getBoolean(activity, PrefData.Key.INITIALIZED)) {
                throw Exception(Constants.ExceptionMessage.LIB_NOT_INITIALIZED)
            }
        }

        /**
         * Method to initialized The Ad Network Provider
         * @param activity
         * @param adProvider
         * @param appId
         */
        private fun initAdNetworkProvider(activity: Activity, adProvider: AdProvider, appId: String) {
            Tracer.debug(TAG, "initAdNetworkProvider: ")
            when (adProvider) {
                AdProvider.AD_MOB -> {
                    MobileAds.initialize(activity, appId)
                }
                AdProvider.START_APP -> {
                    StartAppSDK.init(activity, appId)
                }
                AdProvider.MEDIA_NET -> {
                }
                AdProvider.IN_MOBI -> {
                }
                AdProvider.FLURRY -> {
                }
                AdProvider.MILLENNIAL_MEDIA -> {
                }
                AdProvider.SMAATO -> {
                }
                AdProvider.LEADBOLT -> {
                }
                AdProvider.CHARTBOOST -> {
                }
            }
        }
    }
}