package com.lory.library.advertisement.ui

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.annotation.RequiresApi
import com.lory.library.advertisement.utils.Utils

/**
 * @author THEMKR
 */
open class BannerAdView : RelativeLayout {

    var onBannerAdViewListener: OnBannerAdViewListener? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(getBannerWidth(), MeasureSpec.EXACTLY)
        val newHeightMeasureSpec = MeasureSpec.makeMeasureSpec(getBannerHeight(), MeasureSpec.EXACTLY)
        super.onMeasure(newWidthMeasureSpec, newHeightMeasureSpec)
    }


    /**
     * Method to get the Width of the Banner
     */
    fun getBannerWidth(): Int {
        return Utils.convertDpToPx(context, 320F)
    }

    /**
     * Method to get the Height of the Banner
     */
    fun getBannerHeight(): Int {
        return Utils.convertDpToPx(context, 50F)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        onBannerAdViewListener?.onBannerAdViewSizeChanged(w, h)
    }

    /**
     * Method to init the BannerAdView
     */
    private fun init() {
        setBackgroundColor(Color.BLACK)
    }

    /**
     * Callback to listen the Status of the size of Banner View
     */
    interface OnBannerAdViewListener {

        /**
         * Callback to notify the new Size of this View
         * @param width
         * @param height
         */
        fun onBannerAdViewSizeChanged(width: Int, height: Int)
    }
}