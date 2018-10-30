package com.nazgul.library.advertisement.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.text.TextUtils
import com.nazgul.library.advertisement.BuildConfig


class Utils {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".Utils"

        /**
         * Method to get the MetaData String
         */
        fun getMetaDataString(context: Context, key: String): String {
            Tracer.debug(TAG, "getMetaDataString: $key")
            if (TextUtils.isEmpty(key)) {
                return ""
            }
            try {
                val applicationInfo: ApplicationInfo? = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA)
                if (applicationInfo == null) {
                    return ""
                }
                return (applicationInfo?.metaData.get(key) ?: "").toString()
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return ""
        }
    }
}