package com.lory.library.advertisement.enums

/**
 * @author THEMKR
 * Enum hold the AD-Network provider
 */
enum class AdProvider {
    AD_MOB(0),
    START_APP(1);

    val providerIndex: Int

    constructor(providerIndex: Int) {
        this.providerIndex = providerIndex
    }

    companion object {

        /**
         * Method to get the Add Provider
         * @param  providerIndex
         */
        fun getAdProvider(providerIndex: Int): AdProvider {
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