package com.nazgul.library.advertisement.utils

import com.nazgul.library.BuildConfig

/**
 * Enum hold the Ad-Network provider
 */
enum class AdProvider {
    MEDIA_NET(0),
    AD_MOB(1),
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