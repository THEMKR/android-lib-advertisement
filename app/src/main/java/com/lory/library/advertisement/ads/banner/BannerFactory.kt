package com.lory.library.advertisement.ads.banner

import android.app.Activity
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ads.Advertisement
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Constants

class BannerFactory {
    companion object {
        /**
         * Method to create the BannerAd Object correspond to the Banner Add
         * @param adProvider
         * @param activity
         * @param adId
         * @param onAdvertisementListener
         * @param bannerAdView
         */
        fun create(adProvider: AdProvider, activity: Activity, adId: String, onAdvertisementListener: OnAdvertisementListener, bannerAdView: BannerAdView): Advertisement {
            when (adProvider) {
                AdProvider.AD_MOB -> {
                    return AdMobBanner(activity, adId, onAdvertisementListener, bannerAdView)
                }
                AdProvider.START_APP -> {
                    return StartAppBanner(activity, adId, onAdvertisementListener, bannerAdView)
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