package com.lory.library.advertisement.enums

import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.utils.Tracer

/**
 * Enum hold the AD-Network provider
 */
enum class AdProvider {
    AD_MOB(0),
    MEDIA_NET(1),
    START_APP(2),
    IN_MOBI(3),
    FLURRY(4),
    MILLENNIAL_MEDIA(5),
    SMAATO(6),
    LEADBOLT(7),
    CHARTBOOST(8);

    val providerIndex: Int

    constructor(providerIndex: Int) {
        this.providerIndex = providerIndex
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
}