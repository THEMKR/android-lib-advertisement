package com.lory.library.advertisement

import android.app.Activity
import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer
import com.lory.library.advertisement.utils.Utils
import com.startapp.android.publish.adsCommon.StartAppAd
import com.startapp.android.publish.adsCommon.StartAppSDK

internal class SDKInitializer {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".SDKInitializer"

        /**
         * Method to initialized the Lib
         */
        fun initialize(activity: Activity) {
            Tracer.debug(TAG, "initialize: ")
            // INIT THE LIB DEFAULT VALUE
            if (!PrefData.getBoolean(activity, PrefData.Key.LIB_INITIALIZED)) {
                initDefaultValue(activity)
                PrefData.setBoolean(activity, PrefData.Key.LIB_INITIALIZED, true)
            }
            // INIT BANNER
            initBannerProvider(activity)
            // INIT INTERSTITIAL
            initInterstitialProvider(activity)
        }

        /**
         * Method to init the Banner Provider
         * @param activity
         */
        private fun initBannerProvider(activity: Activity) {
            Tracer.debug(TAG, "initBannerSDK: ")
            val provider: AdProvider = AdProvider.getAdProvider(PrefData.getInt(activity, PrefData.Key.BANNER_PROVIDER))
            var appId: String = PrefData.getString(activity, PrefData.Key.BANNER_PROVIDER_APP_ID).trim()
            initProvider(activity, provider, appId)
        }

        /**
         * Method to init the Interstitial Provider
         * @param activity
         */
        private fun initInterstitialProvider(activity: Activity) {
            Tracer.debug(TAG, "initInterstitialProvider: ")
            val provider: AdProvider = AdProvider.getAdProvider(PrefData.getInt(activity, PrefData.Key.INTERSTITIAL_PROVIDER))
            var appId: String = PrefData.getString(activity, PrefData.Key.INTERSTITIAL_PROVIDER_APP_ID).trim()
            initProvider(activity, provider, appId)
        }

        /**
         * Method to initialized The Ad Network Provider
         * @param activity
         * @param adProvider
         * @param appId
         */
        private fun initProvider(activity: Activity, adProvider: AdProvider, appId: String) {
            Tracer.debug(TAG, "initProvider: ")
            when (adProvider) {
                AdProvider.AD_MOB -> {
                    MobileAds.initialize(activity, appId)
                }
                AdProvider.START_APP -> {
                    StartAppSDK.init(activity, appId)
                    StartAppSDK.setUserConsent(activity, "pas", System.currentTimeMillis(), false);
                    StartAppAd.disableSplash()
                    StartAppAd.disableAutoInterstitial()
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

        /**
         * Method to initialized the Default Value of Libs
         * @param context
         */
        private fun initDefaultValue(context: Context) {
            Tracer.debug(TAG, "initDefaultValue: ")
            // SET DEFAULT BANNER AD DATA
            PrefData.setInt(context, PrefData.Key.BANNER_PROVIDER, Utils.getMetaDataInt(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_INDEX))
            PrefData.setString(context, PrefData.Key.BANNER_PROVIDER_APP_ID, Utils.getMetaDataString(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_APP_ID))
            PrefData.setString(context, PrefData.Key.BANNER_AD_ID, Utils.getMetaDataString(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_BANNER_ID))
            // SET DEFAULT INTERSTITIAL AD DATA
            PrefData.setInt(context, PrefData.Key.INTERSTITIAL_PROVIDER, Utils.getMetaDataInt(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_INDEX))
            PrefData.setString(context, PrefData.Key.INTERSTITIAL_PROVIDER_APP_ID, Utils.getMetaDataString(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_APP_ID))
            PrefData.setString(context, PrefData.Key.INTERSTITIAL_AD_ID, Utils.getMetaDataString(context, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_INTERSTITIAL_ID))
        }
    }
}