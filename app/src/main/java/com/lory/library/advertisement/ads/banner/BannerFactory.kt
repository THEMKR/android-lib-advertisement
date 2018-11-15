package com.lory.library.advertisement.ads.banner

import android.app.Activity
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.callback.OnAdProvider
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.AdProvider
import com.lory.library.advertisement.utils.Constants

class BannerFactory {
    companion object {
        /**
         * Method to create the BannerAd Object correspond to the Banner Add
         * @param adProvider
         * @param activity
         * @param adId
         * @param onAdListener
         * @param bannerAdView
         */
        fun create(adProvider: AdProvider, activity: Activity, adId: String, onAdListener: OnAdListener, bannerAdView: BannerAdView): OnAdProvider {
            when (adProvider) {
                AdProvider.AD_MOB -> {
                    return AdMobBanner(activity, adId, onAdListener, bannerAdView)
                }
                AdProvider.START_APP -> {
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
            throw Exception("${Constants.ExceptionMessage.BANNER_AD_NOT_SUPPORTED} ${adProvider.name}")
        }
    }
}