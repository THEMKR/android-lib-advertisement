package com.mkrworld.mkr_android_lib_ads

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        AdLib.initialize(this)
    }
}
