package com.nazgul.library.advertisement

import android.content.Context
import android.view.ViewGroup
import com.google.android.gms.ads.MobileAds
import com.nazgul.library.BuildConfig
import com.nazgul.library.advertisement.ads.BaseAd
import com.nazgul.library.advertisement.ads.banner.BannerAdMob
import com.nazgul.library.advertisement.ads.interstitial.InterstitialAdMob
import com.nazgul.library.advertisement.utils.*

class AdLib {

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdLib"

        /**
         * Method to initialized the Lib
         */
        fun initialize(context: Context) {
            Tracer.debug(TAG, "initialize: ")
            MobileAds.initialize(context, Utils.getMetaDataString(context, Constants.MetaDataKeys.AD_MOB_APP_ID))
        }
    }

    /**
     * Class to build the Ad
     */
    class Builder {
        private val context: Context
        private var adContainer: ViewGroup? = null
        private var adType: AdType = AdType.INTERSTITIAL
        private var adProvider: AdProvider = AdProvider.AD_MOB

        /**
         * Constructor
         * @param context
         */
        constructor(context: Context) {
            this.context = context
        }

        /**
         * Method to set the Ad Network Provider
         * @param providerIndex
         */
        fun setAdProvider(providerIndex: Int): Builder {
            Tracer.debug(TAG, "setAdProvider: $providerIndex")
            adProvider = AdProvider.getAdProvider(providerIndex)
            return this
        }

        /**
         * Method to set the Ad Type
         * @param adType
         */
        fun setApType(adType: AdType): Builder {
            Tracer.debug(TAG, "setApType: $adType")
            this.adType = adType
            return this
        }

        /**
         * Method to set the Banner Ad Container
         */
        fun setBannerAdContainer(adContainer: ViewGroup): Builder {
            Tracer.debug(TAG, "setBannerAdContainer: $adContainer")
            this.adContainer = adContainer
            return this
        }

        /**
         * Method to build the Ad Object
         */
        fun build(): BaseAd? {
            Tracer.debug(TAG, "build: ")
            when (adType) {
                AdType.BANNER -> {
                    if (adContainer == null) {
                        throw Exception("AdContainer ViewGroup should not be NULL")
                    }
                    return getBannerAd()
                }
                AdType.INTERSTITIAL -> {
                    return getInterstitialAd()
                }
                else -> {
                    return getInterstitialAd()
                }
            }
        }

        /**
         * Method to get the Interstitial Ad
         * @return
         * @exception Exception Caller should handel
         */
        private fun getInterstitialAd(): BaseAd {
            Tracer.debug(TAG, "getInterstitialAd: ")
            when (adProvider) {
                AdProvider.MEDIA_NET -> {
                    return InterstitialAdMob(context)
                }
                AdProvider.AD_MOB -> {
                    return InterstitialAdMob(context)
                }
                AdProvider.START_APP -> {
                    return InterstitialAdMob(context)
                }
                AdProvider.IN_MOBI -> {
                    return InterstitialAdMob(context)
                }
                AdProvider.FLURRY -> {
                    return InterstitialAdMob(context)
                }
                AdProvider.MILLENNIAL_MEDIA -> {
                    return InterstitialAdMob(context)
                }
                AdProvider.SMAATO -> {
                    return InterstitialAdMob(context)
                }
                AdProvider.LEADBOLT -> {
                    return InterstitialAdMob(context)
                }
                AdProvider.CHARTBOOST -> {
                    return InterstitialAdMob(context)
                }
                else -> {
                    return InterstitialAdMob(context)
                }
            }
        }

        /**
         * Method to get the Banner Ad
         */
        private fun getBannerAd(): BaseAd {
            Tracer.debug(TAG, "getBannerAd: ")
            when (adProvider) {
                AdProvider.MEDIA_NET -> {
                    return BannerAdMob(context, adContainer!!)
                }
                AdProvider.AD_MOB -> {
                    return BannerAdMob(context, adContainer!!)
                }
                AdProvider.START_APP -> {
                    return BannerAdMob(context, adContainer!!)
                }
                AdProvider.IN_MOBI -> {
                    return BannerAdMob(context, adContainer!!)
                }
                AdProvider.FLURRY -> {
                    return BannerAdMob(context, adContainer!!)
                }
                AdProvider.MILLENNIAL_MEDIA -> {
                    return BannerAdMob(context, adContainer!!)
                }
                AdProvider.SMAATO -> {
                    return BannerAdMob(context, adContainer!!)
                }
                AdProvider.LEADBOLT -> {
                    return BannerAdMob(context, adContainer!!)
                }
                AdProvider.CHARTBOOST -> {
                    return BannerAdMob(context, adContainer!!)
                }
                else -> {
                    return BannerAdMob(context, adContainer!!)
                }
            }
        }
    }
}