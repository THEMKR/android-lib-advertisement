package com.lory.library.advertisement.controller

import android.app.Activity
import android.app.AlarmManager
import android.app.Application
import com.google.gson.Gson
import com.lory.library.advertisement.SDKInitializer
import com.lory.library.advertisement.dto.DTOAdConfig
import com.lory.library.advertisement.dto.DTOAdInfo
import com.lory.library.advertisement.enums.AdType
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer
import com.lory.library.advertisement.utils.Utils
import com.lory.library.appconfig.AppConfigLib
import com.lory.library.firebaselib.FirebaseCallBack

class SyncController {
    companion object {
        private const val TAG: String = Constants.TAG + ".SyncController"
    }

    private var activity: Activity? = null
    private var applicationContext: Application? = null
    private val asyncCallBackAppConfig = object : FirebaseCallBack<DTOAdConfig> {
        override fun onFirebaseFailed(errorCode: Int, errorMessage: String) {
            // DO NOTHING
        }

        override fun onFirebaseSuccess(mkr: DTOAdConfig?) {
            Tracer.debug(TAG, "onFirebaseSuccess: 1:  $mkr")
            if (mkr == null) {
                return
            }
            val adInfoList = mkr.adInfoList!!
            for (adInfo in adInfoList) {
                if (adInfo.adProvider == -1) {
                    continue
                }
                Tracer.debug(TAG, "onFirebaseSuccess:  ${AdType.getAdType(adInfo.adType!!)}      $adInfo")
                when (AdType.getAdType(adInfo.adType!!)) {
                    AdType.BANNER -> {
                        saveBannerDetail(adInfo)
                    }
                    AdType.INTERSTITIAL -> {
                        saveInterstitialDetail(adInfo)
                    }
                }
            }
            // SET SYNC TIME
            if (applicationContext != null) {
                PrefData.setLong(applicationContext!!, PrefData.Key.SYNC_TIME, System.currentTimeMillis())
                PrefData.setLong(applicationContext!!, PrefData.Key.SYNC_INTERVAL, mkr.syncIntervalHour!!.toLong() * AlarmManager.INTERVAL_HOUR)
            }
            if (activity != null) {
                SDKInitializer.initialize(activity!!)
            }
        }
    }

    /**
     * Constructor
     * @param activity
     */
    constructor(activity: Activity) {
        this.activity = activity
        applicationContext = activity.application
    }

    /**
     * Method to sync Library with the server
     */
    fun syncServer() {
        Tracer.debug(TAG, "syncServer: ")
        if ((PrefData.getLong(applicationContext!!, PrefData.Key.SYNC_TIME) + PrefData.getLong(applicationContext!!, PrefData.Key.SYNC_INTERVAL)) > System.currentTimeMillis()) {
            return
        }
        Tracer.debug(TAG, "syncServer: [syncing server]")
        AppConfigLib.fetchAdConfig(applicationContext!!, asyncCallBackAppConfig)
    }

    /**
     * Method to sync Library with the server
     */
    fun syncDefaultValue() {
        Tracer.debug(TAG, "syncDefaultValue: ")
        val metaDataString = Utils.getMetaDataString(applicationContext!!, Constants.MetaDataKeys.DEFAULT_AD_CONFIG)
        asyncCallBackAppConfig.onFirebaseSuccess(Gson().fromJson<DTOAdConfig>(metaDataString, DTOAdConfig::class.java))
    }

    /**
     * Method to set the Detail of Banner
     * @param adInfo
     */
    private fun saveBannerDetail(adInfo: DTOAdInfo) {
        Tracer.debug(TAG, "saveBannerDetail: $adInfo")
        PrefData.setInt(applicationContext!!, PrefData.Key.BANNER_PROVIDER, adInfo.adProvider!!)
        PrefData.setString(applicationContext!!, PrefData.Key.BANNER_PROVIDER_APP_ID, adInfo.appId!!)
        PrefData.setString(applicationContext!!, PrefData.Key.BANNER_AD_ID, adInfo.adId!!)
    }

    /**
     * Method to set the Detail of Interstitial
     * @param adInfo
     */
    private fun saveInterstitialDetail(adInfo: DTOAdInfo) {
        Tracer.debug(TAG, "saveInterstitialDetail: $adInfo")
        PrefData.setInt(applicationContext!!, PrefData.Key.INTERSTITIAL_PROVIDER, adInfo.adProvider!!)
        PrefData.setString(applicationContext!!, PrefData.Key.INTERSTITIAL_PROVIDER_APP_ID, adInfo.appId!!)
        PrefData.setString(applicationContext!!, PrefData.Key.INTERSTITIAL_AD_ID, adInfo.adId!!)
    }
}