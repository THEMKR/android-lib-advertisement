package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ads.Advertisement
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.utils.Constants

class InterstitialFactory {
    companion object {
        /**
         * Method to create the InterstitialAd Object correspond to the Banner Add
         * @param adProvider
         * @param context
         * @param adId
         * @param onAdvertisementListener
         */
        fun create(adProvider: AdProvider, context: Context, adId: String, onAdvertisementListener: OnAdvertisementListener): Advertisement {
            when (adProvider) {
                AdProvider.AD_MOB -> {
                    return AdMobInterstitial(context, adId, onAdvertisementListener)
                }
                AdProvider.START_APP -> {
                    return StartAppInterstitial(context, adId, onAdvertisementListener)
                }
                AdProvider.MEDIA_NET -> {
                }
                AdProvider.IN_MOBI -> {
                    return InMobiInterstitial(context, adId, onAdvertisementListener)
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
            throw Exception("${Constants.ExceptionMessage.INTERSTITIAL_AD_NOT_SUPPORTED} ${adProvider.name}")
        }
    }
}