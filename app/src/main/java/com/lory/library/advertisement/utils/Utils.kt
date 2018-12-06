package com.lory.library.advertisement.utils

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.text.TextUtils
import android.util.DisplayMetrics


class Utils {
    companion object {

        /**
         * Method to get the MetaData String
         * @param context
         * @param key
         */
        fun getMetaDataString(context: Context, key: String): String {
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
            return context.packageName.replace(".", "_", true)
        }


        /**
         * Method to convert the DP to pixel
         */
        fun convertDpToPx(context: Context, dp: Float): Int {
            return Math.round(dp * (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT.toFloat()))
        }
    }
}