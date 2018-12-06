package com.lory.library.advertisement.enums

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

        /**
         * Method to get the Add Provider
         * @param  providerIndex
         */
        fun getAdType(providerIndex: Int): AdType {
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