package com.lory.library.advertisement

/**
 * @author THEMKR
 * Callback used to listen the AD events
 */
interface OnAdvertisementListener {

    /**
     * Method called when lib is init the Advertisement fetching process
     */
    fun onAdvertisementFetching()

    /**
     * Method called when Lib failed to fetch the Advertisement
     */
    fun onAdvertisementFailed()

    /**
     * Method called when Advertisement is ready to show to user
     */
    fun onAdvertisementReady()

    /**
     * Method called when Advertisement visible to user
     */
    fun onAdvertisementShown()

    /**
     * Method called when user click the Advertisement
     */
    fun onAdvertisementClicked()

    /**
     * Method called when user cancel the Advertisement
     */
    fun onAdvertisementCancel()

    /**
     * Method called when this Advertisement is finished
     */
    fun onAdvertisementFinished()
}