package com.lory.library.advertisement.ads.banner

import android.app.Activity
import android.util.Log
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
            Log.e("MKR","BannerFactory.create() ${adProvider}    ${adProvider.providerIndex}    ${adId}")
            when (adProvider) {
                AdProvider.AD_MOB -> {
                    Log.e("MKR","BannerFactory.create().AD_MOB ${adProvider}    ${adProvider.providerIndex}    ${adId}")
                    return AdMobBanner(activity, adId, onAdvertisementListener, bannerAdView)
                }
                AdProvider.START_APP -> {
                    Log.e("MKR","BannerFactory.create().START_APP ${adProvider}    ${adProvider.providerIndex}    ${adId}")
                    return StartAppBanner(activity, adId, onAdvertisementListener, bannerAdView)
                }
                AdProvider.IN_MOBI -> {
                    Log.e("MKR","BannerFactory.create().IN_MOBI ${adProvider}    ${adProvider.providerIndex}    ${adId}")
                    return InMobiBanner(activity, adId, onAdvertisementListener, bannerAdView)
                }
            }
            throw Exception("${Constants.ExceptionMessage.BANNER_AD_NOT_SUPPORTED} ${adProvider.name}")
        }
    }
}