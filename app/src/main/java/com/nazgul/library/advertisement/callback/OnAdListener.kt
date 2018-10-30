package com.nazgul.library.advertisement.callback

/**
 * Callback used to listen the Ad events
 */
interface OnAdListener {

    /**
     * Method called when lib is init the Ad fetching process
     */
    abstract fun onAdFetching()

    /**
     * Method called when Lib failed to fetch the Ad
     */
    abstract fun onAdFailed()

    /**
     * Method called when ad is ready to show to user
     */
    abstract fun onAdReady()

    /**
     * Method called when ad visible to user
     */
    abstract fun onAdShown()

    /**
     * Method called when user click the ad
     */
    abstract fun onAdClicked()

    /**
     * Method called when user cancel the ad
     */
    abstract fun onAdCancel()
}