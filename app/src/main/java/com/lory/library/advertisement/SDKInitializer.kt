package com.lory.library.advertisement

import android.app.Activity
import com.google.android.gms.ads.MobileAds
import com.inmobi.sdk.InMobiSdk
import com.lory.library.advertisement.enums.AdProvider
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer
import com.startapp.android.publish.adsCommon.StartAppAd
import com.startapp.android.publish.adsCommon.StartAppSDK
import org.json.JSONObject

internal class SDKInitializer {
    companion object {
        private const val TAG: String = Constants.TAG + ".SDKInitializer"

        /**
         * Method to initialized the Lib
         */
        fun initialize(activity: Activity) {
            // INIT BANNER
            initBannerProvider(activity)
            // INIT INTERSTITIAL
            initInterstitialProvider(activity)
        }

        /**
         * Method to init the Banner Provider
         * @param activity
         */
        private fun initBannerProvider(activity: Activity) {
            val provider: AdProvider = AdProvider.getAdProvider(PrefData.getInt(activity, PrefData.Key.BANNER_PROVIDER))
            var appId: String = PrefData.getString(activity, PrefData.Key.BANNER_PROVIDER_APP_ID).trim()
            initProvider(activity, provider, appId)
        }

        /**
         * Method to init the Interstitial Provider
         * @param activity
         */
        private fun initInterstitialProvider(activity: Activity) {
            val provider: AdProvider = AdProvider.getAdProvider(PrefData.getInt(activity, PrefData.Key.INTERSTITIAL_PROVIDER))
            var appId: String = PrefData.getString(activity, PrefData.Key.INTERSTITIAL_PROVIDER_APP_ID).trim()
            initProvider(activity, provider, appId)
        }

        /**
         * Method to initialized The Ad Network Provider
         * @param activity
         * @param adProvider
         * @param appId
         */
        private fun initProvider(activity: Activity, adProvider: AdProvider, appId: String) {
            Tracer.debug(TAG, "initProvider: ${adProvider}    ${adProvider.providerIndex}")
            when (adProvider) {
                AdProvider.AD_MOB -> {
                    MobileAds.initialize(activity, appId)
                }
                AdProvider.START_APP -> {
                    StartAppSDK.init(activity, appId)
                    StartAppSDK.setUserConsent(activity, "pas", System.currentTimeMillis(), false);
                    StartAppAd.disableSplash()
                    StartAppAd.disableAutoInterstitial()
                }
                AdProvider.IN_MOBI -> {
                    val consentObject = JSONObject()
                    try {
                        consentObject.put(InMobiSdk.IM_GDPR_CONSENT_AVAILABLE, true)
                        consentObject.put("gdpr", "1")
                    } catch (e: Exception) {
                        Tracer.debug(TAG, "initProvider: ${e.message}")
                    }
                    InMobiSdk.init(activity, appId, consentObject)
                    InMobiSdk.setLogLevel(InMobiSdk.LogLevel.DEBUG);
                }
            }
        }
    }
}