package com.lory.library.advertisement.ads.banner

import android.content.Context
import android.view.ViewGroup
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.ads.AD
import com.lory.library.advertisement.ads.AdProvider
import com.lory.library.advertisement.utils.AdNetwork
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer

class BannerAdProvider : AdProvider {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".BannerAdProvider"
    }

    protected val adContainer: ViewGroup

    /**
     * Constructor
     * @param context
     * @param contaierView
     */
    constructor(context: Context, contaierView: ViewGroup) : super(context) {
        Tracer.debug(TAG, "Constructor: ")
        this.adContainer = contaierView
    }

    override fun getAd(adNetwork: AdNetwork): AD {
        Tracer.debug(TAG, "getAd: ")
        val adId = PrefData.getString(context, PrefData.Key.BANNER_AD_ID)
        when (adNetwork) {
            AdNetwork.MEDIA_NET -> {
                return BannerAdMob(context, adId, this, adContainer)
            }
            AdNetwork.AD_MOB -> {
                return BannerAdMob(context, adId, this, adContainer)
            }
            AdNetwork.START_APP -> {
                return BannerAdMob(context, adId, this, adContainer)
            }
            AdNetwork.IN_MOBI -> {
                return BannerAdMob(context, adId, this, adContainer)
            }
            AdNetwork.FLURRY -> {
                return BannerAdMob(context, adId, this, adContainer)
            }
            AdNetwork.MILLENNIAL_MEDIA -> {
                return BannerAdMob(context, adId, this, adContainer)
            }
            AdNetwork.SMAATO -> {
                return BannerAdMob(context, adId, this, adContainer)
            }
            AdNetwork.LEADBOLT -> {
                return BannerAdMob(context, adId, this, adContainer)
            }
            AdNetwork.CHARTBOOST -> {
                return BannerAdMob(context, adId, this, adContainer)
            }
            else -> {
                return BannerAdMob(context, adId, this, adContainer)
            }
        }
    }

    override fun getAdNetwork(): AdNetwork {
        Tracer.debug(TAG, "getAdNetwork: ")
        return AdNetwork.getAdProvider(PrefData.getInt(context, PrefData.Key.BANNER_PROVIDER))
    }
}