package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.callback.OnAdProvider
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.utils.Constants

class InterstitialFactory {
    companion object {
        /**
         * Method to create the InterstitialAd Object correspond to the Banner Add
         * @param adProvider
         * @param context
         * @param adId
         * @param onAdListener
         */
        fun create(adProvider: AdProvider, context: Context, adId: String, onAdListener: OnAdListener): OnAdProvider {
            when (adProvider) {
                AdProvider.AD_MOB -> {
                    return AdMobInterstitial(context, adId, onAdListener)
                }
                AdProvider.START_APP -> {
                    return StartAppInterstitial(context, adId, onAdListener)
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
            throw Exception("${Constants.ExceptionMessage.INTERSTITIAL_AD_NOT_SUPPORTED} ${adProvider.name}")
        }
    }
}