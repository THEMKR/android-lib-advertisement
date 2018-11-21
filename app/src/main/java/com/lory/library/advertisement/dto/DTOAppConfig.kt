package com.lory.library.advertisement.dto

class DTOAppConfig : DTOBaseNetworkResponse() {

    /**
     * Current Version of app on firebase
     */
    var appVersion: Long = 0L

    /**
     * Method to get the Index of banner Ad Provider App Id
     */
    var bannerAdProviderAppId: String = ""

    /**
     * Method to get the Index of banner Ad Provider
     */
    var bannerAdProvider: Long = 0L

    /**
     * Method to get the Index of banner Ad Id
     */
    var bannerAdId: String = ""

    /**
     * Method to get the Index of interstitial Ad Provider App Id
     */
    var interstitialAdProviderAppId: String = ""

    /**
     * Method to get the Index of interstitial Ad Provider
     */
    var interstitialAdProvider: Long = 0L

    /**
     * Method to get the Index of interstitial Ad Id
     */
    var interstitialAdId: String = ""

}