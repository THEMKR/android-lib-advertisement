package com.lory.library.advertisement.ads.interstitial

import android.content.Context
import android.util.Log
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
            Log.e("MKR", "InterstitialFactory.create() ${adProvider}    ${adProvider.providerIndex}    ${adId}")
            when (adProvider) {
                AdProvider.AD_MOB -> {
                    Log.e("MKR", "InterstitialFactory.create().AD_MOB ${adProvider}    ${adProvider.providerIndex}    ${adId}")
                    return AdMobInterstitial(context, adId, onAdvertisementListener)
                }
                AdProvider.START_APP -> {
                    Log.e("MKR", "InterstitialFactory.create().START_APP ${adProvider}    ${adProvider.providerIndex}    ${adId}")
                    return StartAppInterstitial(context, adId, onAdvertisementListener)
                }
                AdProvider.IN_MOBI -> {
                    Log.e("MKR", "InterstitialFactory.create().IN_MOBI ${adProvider}    ${adProvider.providerIndex}    ${adId}")
                    return InMobiInterstitial(context, adId, onAdvertisementListener)
                }
            }
            throw Exception("${Constants.ExceptionMessage.INTERSTITIAL_AD_NOT_SUPPORTED} ${adProvider.name}")
        }
    }
}