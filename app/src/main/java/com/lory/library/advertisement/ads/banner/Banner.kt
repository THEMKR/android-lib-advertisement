package com.lory.library.advertisement.ads.banner

import android.app.Activity
import com.lory.library.advertisement.OnAdvertisementListener
import com.lory.library.advertisement.ads.Advertisement
import com.lory.library.advertisement.ui.BannerAdView

internal abstract class Banner : Advertisement {
    protected val activity: Activity
    protected val adId: String
        get() {
            return field.trim()
        }
    protected val onAdvertisementListener: OnAdvertisementListener
    protected val bannerAdView: BannerAdView

    /**
     * Constructor
     * @param activity
     * @param adId
     * @param onAdvertisementListener
     * @param bannerAdView The AD container
     */
    internal constructor(activity: Activity, adId: String, onAdvertisementListener: OnAdvertisementListener, bannerAdView: BannerAdView) {
        this.activity = activity
        this.adId = adId
        this.onAdvertisementListener = onAdvertisementListener
        this.bannerAdView = bannerAdView
    }
}