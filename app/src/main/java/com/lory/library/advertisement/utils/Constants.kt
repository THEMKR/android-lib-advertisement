package com.lory.library.advertisement.utils

class Constants {
    companion object {

    }

    /**
     * Class to hold the MetaData keys
     */
    interface ExceptionMessage {
        companion object {
            val LIB_NOT_INITIALIZED = "Advertisement Library is not initialized. Plz initialized it before any operation"
            val BANNER_AD_NOT_SUPPORTED = "Banner Ad is not supported by Provider : "
            val INTERSTITIAL_AD_NOT_SUPPORTED = "Interstitial Ad is not supported by Provider : "
        }
    }

    /**
     * Class to hold the MetaData keys
     */
    class MetaDataKeys {
        companion object {
            const val DEFAULT_AD_PROVIDER_INDEX = "default_ad_provider_index"
            const val DEFAULT_AD_PROVIDER_APP_ID = "default_ad_provider_app_id"
            const val DEFAULT_AD_PROVIDER_BANNER_ID = "default_ad_provider_banner_id"
            const val DEFAULT_AD_PROVIDER_INTERSTITIAL_ID = "default_ad_provider_interstitial_id"
        }
    }
}