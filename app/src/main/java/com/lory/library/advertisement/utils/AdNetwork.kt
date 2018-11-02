package com.lory.library.advertisement.utils

import com.lory.library.advertisement.BuildConfig

/**
 * Enum hold the AD-Network provider
 */
enum class AdNetwork {
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
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdNetwork"

        /**
         * Method to get the Add Provider
         * @param  providerIndex
         */
        fun getAdProvider(providerIndex: Int): AdNetwork {
            Tracer.debug(TAG, "getAdProvider: ")
            val arrayOfAdNetwork: Array<AdNetwork> = AdNetwork.values()
            for (adProvider in arrayOfAdNetwork) {
                if (adProvider.providerIndex == providerIndex) {
                    return adProvider
                }
            }
            return AD_MOB
        }
    }
}