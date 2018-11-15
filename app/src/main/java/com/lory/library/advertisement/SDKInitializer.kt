package com.lory.library.advertisement

import android.app.Activity
import com.google.android.gms.ads.MobileAds
import com.lory.library.advertisement.utils.AdProvider
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer
import com.startapp.android.publish.adsCommon.StartAppSDK

internal class SDKInitializer {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".SDKInitializer"

        /**
         * Method to initialized the Lib
         */
        fun initialize(activity: Activity) {
            Tracer.debug(TAG, "initialize: ")
            // INTERSTITIAL
            var interAppId: String = PrefData.getString(activity, PrefData.Key.APP_ID_INTERSTITIAL).trim()
            val interAdProvider: AdProvider = AdProvider.getAdProvider(PrefData.getInt(activity, PrefData.Key.INTERSTITIAL_PROVIDER))
            // BANNER
            var bannerAppId: String = PrefData.getString(activity, PrefData.Key.APP_ID_BANNER).trim()
            val bannerAdProvider: AdProvider = AdProvider.getAdProvider(PrefData.getInt(activity, PrefData.Key.BANNER_PROVIDER))
            // INIT LIB
            initAdNetworkProvider(activity, bannerAdProvider, bannerAppId)
            initAdNetworkProvider(activity, interAdProvider, interAppId)
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