package com.lory.library.advertisement.controller

import android.app.Activity
import android.app.Application
import com.google.gson.Gson
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.dto.DTOAdInfo
import com.lory.library.advertisement.dto.DTOAppConfig
import com.lory.library.advertisement.enums.AdType
import com.lory.library.advertisement.task.AsyncTaskProvider
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.PrefData
import com.lory.library.advertisement.utils.Tracer
import com.lory.library.advertisement.utils.Utils
import com.mkrworld.androidlibasynctask.AsyncCallBack

class SyncController {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".SyncController"
    }

    private val activity: Activity
    private val applicationContext: Application
    private val asyncTaskProvider: AsyncTaskProvider
    private val asyncCallBackAppConfig = object : AsyncCallBack<DTOAppConfig, Any> {
        override fun onProgress(progress: Any?) {
            Tracer.debug(TAG, "onProgress: ")
        }

        override fun onSuccess(mkr: DTOAppConfig?) {
            Tracer.debug(TAG, "onSuccess: ")
            if (mkr == null) {
                return
            }
            val adInfoList = mkr.adInfoList
            for (adInfo in adInfoList) {
                if (adInfo.adProvider == -1) {
                    continue
                }
                when (adInfo.adType) {
                    AdType.BANNER.adTypeIndex -> {

                        saveBannerDetail(adInfo)
                    }
                    AdType.INTERSTITIAL.adTypeIndex -> {

                        saveBannerDetail(adInfo)
                    }
                }
            }
        }
    }

    /**
     * Constructor
     * @param activity
     */
    constructor(activity: Activity) {
        Tracer.debug(TAG, "Constructor : ")
        this.activity = activity
        applicationContext = activity.application
        asyncTaskProvider = AsyncTaskProvider()
        asyncTaskProvider.attachProvider()
    }

    /**
     * Method to sync Library with the server
     */
    fun syncServer() {
        Tracer.debug(TAG, "syncServer: ")
        if ((PrefData.getLong(applicationContext, PrefData.Key.SYNC_TIME) + PrefData.getLong(applicationContext, PrefData.Key.SYNC_INTERVAL)) > System.currentTimeMillis()) {
            return
        }
        asyncTaskProvider.fetchAppConfig(applicationContext, asyncCallBackAppConfig)
    }

    /**
     * Method to sync Library with the server
     */
    fun syncDefaultValue() {
        Tracer.debug(TAG, "syncDefaultValue: ")
        val metaDataString = Utils.getMetaDataString(activity, Constants.MetaDataKeys.DEFAULT_AD_CONFIG)
        asyncCallBackAppConfig.onSuccess(Gson().fromJson<DTOAppConfig>(metaDataString, DTOAppConfig::class.java))
    }

    /**
     * Method to set the Detail of Banner
     * @param adInfo
     */
    private fun saveBannerDetail(adInfo: DTOAdInfo) {
        Tracer.debug(TAG, "saveBannerDetail: ")
        PrefData.setInt(activity, PrefData.Key.BANNER_PROVIDER, adInfo.adProvider)
        PrefData.setString(activity, PrefData.Key.BANNER_PROVIDER_APP_ID, adInfo.appId)
        PrefData.setString(activity, PrefData.Key.BANNER_AD_ID, adInfo.adId)
    }

    /**
     * Method to set the Detail of Interstitial
     * @param adInfo
     */
    private fun saveInterstitialDetail(adInfo: DTOAdInfo) {
        Tracer.debug(TAG, "saveInterstitialDetail: ")
        PrefData.setInt(activity, PrefData.Key.INTERSTITIAL_PROVIDER, adInfo.adProvider)
        PrefData.setString(activity, PrefData.Key.INTERSTITIAL_PROVIDER_APP_ID, adInfo.appId)
        PrefData.setString(activity, PrefData.Key.INTERSTITIAL_AD_ID, adInfo.adId)
    }
}