package com.lory.library.advertisement.ads

/**
 * @author THEMKR
 * Callback used to listen the AD events
 */
interface Advertisement {

    /**
     * Method called when lib is init the AD fetching process
     */
    fun fetchAd()

    /**
     * Method called when ad visible to user
     */
    fun shownAd()

    /**
     * Method called when this ad is ready
     * @return TRUE if Add is ready Else FALSE
     */
    fun isAdReady(): Boolean

    /**
     * Method called when this ad is finished
     */
    fun finishAd()
}