package com.lory.library.advertisement.enums

import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.utils.Tracer

/**
 * Enum hold the AD-Network provider
 */
enum class AdProvider {
    AD_MOB(0, arrayListOf()),
    MEDIA_NET(1, arrayListOf()),
    START_APP(2, arrayListOf<AdType>(AdType.BANNER, AdType.INTERSTITIAL)),
    IN_MOBI(3, arrayListOf()),
    FLURRY(4, arrayListOf()),
    MILLENNIAL_MEDIA(5, arrayListOf()),
    SMAATO(6, arrayListOf()),
    LEADBOLT(7, arrayListOf()),
    CHARTBOOST(8, arrayListOf());

    val providerIndex: Int
    val adTypeList: List<AdType>

    constructor(providerIndex: Int, adTypeList: List<AdType>) {
        this.providerIndex = providerIndex
        this.adTypeList = adTypeList
    }

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdProvider"

        /**
         * Method to get the Add Provider
         * @param  providerIndex
         */
        fun getAdProvider(providerIndex: Int): AdProvider {
            Tracer.debug(TAG, "getAdProvider: ")
            val arrayOfAdProvider: Array<AdProvider> = AdProvider.values()
            for (adProvider in arrayOfAdProvider) {
                if (adProvider.providerIndex == providerIndex) {
                    return adProvider
                }
            }
            return AD_MOB
        }
    }

    /**
     * Method to check Weather this Provider Support the called AdType or Not
     */
    fun isSupportAd(adType: AdType): Boolean {
        for (type in adTypeList) {
            if (type.equals(adType)) {
                return true
            }
        }
        return false
    }
}