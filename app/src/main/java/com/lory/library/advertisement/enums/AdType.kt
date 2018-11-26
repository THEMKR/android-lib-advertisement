package com.lory.library.advertisement.enums

import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.utils.Tracer

/**
 * Enum hold the Type of AD
 */
enum class AdType {
    BANNER(0),
    INTERSTITIAL(1);

    val adTypeIndex: Int

    constructor(providerIndex: Int) {
        this.adTypeIndex = providerIndex
    }

    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".AdType"

        /**
         * Method to get the Add Provider
         * @param  providerIndex
         */
        fun getAdType(providerIndex: Int): AdType {
            Tracer.debug(TAG, "getAdProvider: ")
            val arrayOfAdType: Array<AdType> = AdType.values()
            for (adType in arrayOfAdType) {
                if (adType.adTypeIndex == providerIndex) {
                    return adType
                }
            }
            return BANNER
        }
    }
}