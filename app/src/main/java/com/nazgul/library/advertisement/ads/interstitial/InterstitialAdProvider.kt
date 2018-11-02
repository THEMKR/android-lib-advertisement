package com.nazgul.library.advertisement.ads.interstitial

import android.content.Context
import com.nazgul.library.advertisement.BuildConfig
import com.nazgul.library.advertisement.ads.AD
import com.nazgul.library.advertisement.ads.AdProvider
import com.nazgul.library.advertisement.utils.AdNetwork
import com.nazgul.library.advertisement.utils.PrefData
import com.nazgul.library.advertisement.utils.Tracer

/**
 * Class to provide Interstitial Ad
 */
class InterstitialAdProvider : AdProvider {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".InterstitialAdProvider"
        private var instance: InterstitialAdProvider? = null


        /**
         * Method to get the Instance of the Provider
         * @param context
         * @return Current Instance of this class
         */
        fun getInstance(context: Context): InterstitialAdProvider {
            if (instance == null) {
                instance = InterstitialAdProvider(context.applicationContext)
            }
            return instance!!
        }
    }

    protected constructor(context: Context) : super(context) {
        Tracer.debug(TAG, "Constructor: ")
    }

    override fun getAdNetwork(): AdNetwork {
        Tracer.debug(TAG, "getAdNetwork: ")
        return AdNetwork.getAdProvider(PrefData.getInt(context, PrefData.Key.INTERSTITIAL_PROVIDER))
    }

    override fun getAd(adNetwork: AdNetwork): AD {
        Tracer.debug(TAG, "getAd: ")
        val adId = PrefData.getString(context, PrefData.Key.INTERSTITIAL_AD_ID)
        when (getAdNetwork()) {
            AdNetwork.MEDIA_NET -> {
                return InterstitialAdMob(context, adId, this)
            }
            AdNetwork.AD_MOB -> {
                return InterstitialAdMob(context, adId, this)
            }
            AdNetwork.START_APP -> {
                return InterstitialAdMob(context, adId, this)
            }
            AdNetwork.IN_MOBI -> {
                return InterstitialAdMob(context, adId, this)
            }
            AdNetwork.FLURRY -> {
                return InterstitialAdMob(context, adId, this)
            }
            AdNetwork.MILLENNIAL_MEDIA -> {
                return InterstitialAdMob(context, adId, this)
            }
            AdNetwork.SMAATO -> {
                return InterstitialAdMob(context, adId, this)
            }
            AdNetwork.LEADBOLT -> {
                return InterstitialAdMob(context, adId, this)
            }
            AdNetwork.CHARTBOOST -> {
                return InterstitialAdMob(context, adId, this)
            }
            else -> {
                return InterstitialAdMob(context, adId, this)
            }
        }
    }
}