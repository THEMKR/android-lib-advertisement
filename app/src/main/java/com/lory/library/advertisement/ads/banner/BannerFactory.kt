package com.lory.library.advertisement.ads.banner

import android.app.Activity
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ads.Advertisement
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.ui.BannerAdView
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.Tracer

class BannerFactory {
    companion object {
        private const val TAG: String = Constants.TAG + ".BannerFactory"

        /**
         * Method to create the BannerAd Object correspond to the Banner Add
         * @param adProvider
         * @param activity
         * @param adId
         * @param onAdvertisementListener
         * @param bannerAdView
         */
        fun create(adProvider: AdProvider, activity: Activity, adId: String, onAdvertisementListener: OnAdvertisementListener, bannerAdView: BannerAdView): Advertisement {
            Tracer.debug(TAG, "create: ${adProvider}    ${adProvider.providerIndex}    ${adId}")
            when (adProvider) {
                AdProvider.AD_MOB -> {
                    return AdMobBanner(activity, adId, onAdvertisementListener, bannerAdView)
                }
                AdProvider.START_APP -> {
                    return StartAppBanner(activity, adId, onAdvertisementListener, bannerAdView)
                }
                AdProvider.IN_MOBI -> {
                    return InMobiBanner(activity, adId, onAdvertisementListener, bannerAdView)
                }
            }
            throw Exception("${Constants.ExceptionMessage.BANNER_AD_NOT_SUPPORTED} ${adProvider.name}")
        }
    }
}