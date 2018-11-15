package com.lory.library.advertisement.ads.banner

import android.app.Activity
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.callback.OnAdListener
import com.lory.library.advertisement.callback.OnAdProvider
import com.lory.library.advertisement.ui.BannerAdView

internal abstract class Banner : OnAdProvider {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".Banner"
    }

    protected val activity: Activity
    protected val adId: String
        get() {
            return (field ?: "").trim()
        }
    protected val onAdListener: OnAdListener
    protected val bannerAdView: BannerAdView

    /**
     * Constructor
     * @param activity
     * @param adId
     * @param onAdListener
     * @param bannerAdView The AD container
     */
    constructor(activity: Activity, adId: String, onAdListener: OnAdListener, bannerAdView: BannerAdView) {
        this.activity = activity
        this.adId = adId
        this.onAdListener = onAdListener
        this.bannerAdView = bannerAdView
    }
}