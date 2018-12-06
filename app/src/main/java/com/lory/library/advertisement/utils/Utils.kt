package com.lory.library.advertisement.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.text.TextUtils
import android.util.DisplayMetrics
import com.lory.library.advertisement.BuildConfig


class Utils {
    companion object {
        private const val TAG: String = BuildConfig.BASE_TAG + ".Utils"

        /**
         * Method to get the MetaData String
         * @param context
         * @param key
         */
        fun getMetaDataString(context: Context, key: String): String {
            Tracer.debug(TAG, "getMetaDataString: $key")
            if (TextUtils.isEmpty(key)) {
                return ""
            }
            try {
                val applicationInfo: ApplicationInfo = context.packageManager.getApplicationInfo(context.packageName, PackageManager.GET_META_DATA) ?: return ""
                return (applicationInfo?.metaData.get(key) ?: "").toString()
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }
            return ""
        }

        /**
         * Method to get the MetaData Int
         * @param context
         * @param key
         */
        fun getMetaDataInt(context: Context, key: String): Int {
            Tracer.debug(TAG, "getMetaDataString: $key")
            try {
                return getMetaDataString(context, key).trim().toInt()
            } catch (e: Exception) {
                return 0
            }
        }

        /**
         * Method to get the Firebase key of the Application
         */
        fun getAppFirebaseKey(context: Context): String {
            Tracer.debug(TAG, "getAppFirebaseKey: ")
            return context.packageName.replace(".", "_", true)
        }


        /**
         * Method to convert the DP to pixel
         */
        fun convertDpToPx(context: Context, dp: Float): Int {
            Tracer.debug(TAG, "convertDpToPx: $dp")
            return Math.round(dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT.toFloat()))
        }
    }
}