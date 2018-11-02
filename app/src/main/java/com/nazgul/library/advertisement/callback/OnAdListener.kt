package com.nazgul.library.advertisement.callback

/**
 * Callback used to listen the AD events
 */
interface OnAdListener {

    /**
     * Method called when lib is init the AD fetching process
     */
    fun onAdFetching()

    /**
     * Method called when Lib failed to fetch the AD
     */
    fun onAdFailed()

    /**
     * Method called when ad is ready to show to user
     */
    fun onAdReady()

    /**
     * Method called when ad visible to user
     */
    fun onAdShown()

    /**
     * Method called when user click the ad
     */
    fun onAdClicked()

    /**
     * Method called when user cancel the ad
     */
    fun onAdCancel()

    /**
     * Method called when this ad is finished
     */
    fun onAdFinished()
}