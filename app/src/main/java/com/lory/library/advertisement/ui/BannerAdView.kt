package com.lory.library.advertisement.ui

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.annotation.RequiresApi
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.lory.library.advertisement.R

class BannerAdView : RelativeLayout {

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
     * Method to init the BannerAdView
     */
    private fun init() {
        setBackgroundColor(Color.BLACK)
    }

    /**
     * Method to get the Width of the Banner
     */
    protected fun getBannerWidth(): Int {
        return context.resources.getDimensionPixelSize(R.dimen.banner_width)
    }

    /**
     * Method to get the Height of the Banner
     */
    protected fun getBannerHeight(): Int {
        return context.resources.getDimensionPixelSize(R.dimen.banner_height)
    }
}