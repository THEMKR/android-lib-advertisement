package com.lory.library.advertisement

import android.app.Activity
import android.content.Context
import android.view.ViewGroup
import com.google.android.gms.ads.MobileAds
import com.lory.library.advertisement.ads.banner.BannerAdProvider
import com.lory.library.advertisement.ads.interstitial.InterstitialAdProvider
import com.lory.library.advertisement.utils.*

class AdvertisementLib {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdvertisementLib"

        /**
         * Method to initialized the Lib
         */
        fun initialize(context: Context) {
            Tracer.debug(TAG, "initialize: ")
            PrefData.setBoolean(context, PrefData.Key.INITIALIZED, true)
            initAdNetworkProvider(context, AdNetwork.getAdProvider(PrefData.getInt(context, PrefData.Key.INTERSTITIAL_PROVIDER)))
            initAdNetworkProvider(context, AdNetwork.getAdProvider(PrefData.getInt(context, PrefData.Key.BANNER_PROVIDER)))
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
            BannerAdProvider(activity.applicationContext, adContainer).showAd()
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
            InterstitialAdProvider.getInstance(activity.applicationContext).showAd()
        }

        /**
         * Method to initialized The Ad Network Provider
         * @param context
         * @param adNetwork
         */
        private fun initAdNetworkProvider(context: Context, adNetwork: AdNetwork) {
            Tracer.debug(TAG, "initAdNetworkProvider: ")
            when (adNetwork) {
                AdNetwork.MEDIA_NET -> {
                    MobileAds.initialize(context, Utils.getMetaDataString(context, Constants.MetaDataKeys.AD_MOB_APP_ID))
                }
                AdNetwork.AD_MOB -> {
                    MobileAds.initialize(context, Utils.getMetaDataString(context, Constants.MetaDataKeys.AD_MOB_APP_ID))
                }
                AdNetwork.START_APP -> {
                    MobileAds.initialize(context, Utils.getMetaDataString(context, Constants.MetaDataKeys.AD_MOB_APP_ID))
                }
                AdNetwork.IN_MOBI -> {
                    MobileAds.initialize(context, Utils.getMetaDataString(context, Constants.MetaDataKeys.AD_MOB_APP_ID))
                }
                AdNetwork.FLURRY -> {
                    MobileAds.initialize(context, Utils.getMetaDataString(context, Constants.MetaDataKeys.AD_MOB_APP_ID))
                }
                AdNetwork.MILLENNIAL_MEDIA -> {
                    MobileAds.initialize(context, Utils.getMetaDataString(context, Constants.MetaDataKeys.AD_MOB_APP_ID))
                }
                AdNetwork.SMAATO -> {
                    MobileAds.initialize(context, Utils.getMetaDataString(context, Constants.MetaDataKeys.AD_MOB_APP_ID))
                }
                AdNetwork.LEADBOLT -> {
                    MobileAds.initialize(context, Utils.getMetaDataString(context, Constants.MetaDataKeys.AD_MOB_APP_ID))
                }
                AdNetwork.CHARTBOOST -> {
                    MobileAds.initialize(context, Utils.getMetaDataString(context, Constants.MetaDataKeys.AD_MOB_APP_ID))
                }
                else -> {
                    MobileAds.initialize(context, Utils.getMetaDataString(context, Constants.MetaDataKeys.AD_MOB_APP_ID))
                }
            }
        }
    }
}