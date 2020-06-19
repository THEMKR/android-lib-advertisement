# MKR-ANDROID-ADVERTISEMENT

#   AndroidManifest.xml
        <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
        <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />

        <!-- DEFAULT AD DETAIL START -->
        <meta-data
            android:name="default_ad_config"
            android:value="@string/default_config" />

        <meta-data
            android:name="admob_test_device_id"
            android:value="7D7D0BB53322C0DB49F2F2CCE8550FA0" />
        <!-- DEFAULT AD DETAIL END -->

        <meta-data
            android:name="com.google.android.gms.version"
            android:value="@integer/google_play_services_version" />

#	Project Level Gradle
		repositories {
			maven { url "https://api.bitbucket.org/2.0/repositories/THEMKR/android-libs/src/releases" }
		}

#	APP Level Gradle

        implementation 'com.lory.library:ad:1.0.1'

        implementation"org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
        implementation 'com.android.support:multidex:1.0.3'
        implementation 'com.startapp:inapp-sdk:4.6.+'
        implementation 'com.google.android.gms:play-services-ads:19.2.0'
        implementation 'com.google.code.gson:gson:2.8.6'
        
#   USE
            INIT LIB IN EVERY ACTIVITY
                AdvertisementLib.initialize(this)
                AdType -> BANNER[0], INTERSTITIAL[1]
            
            SHOW BANNER
                AdvertisementLib.showBannerAd(this, banner_ad_view, <CALLBACK>)
                
            SHOW INTERSTITIAL
                AdvertisementLib.showInterstitialAd(this@MainActivity, <CALLBACK>)        
                
            BANNER LAYOUT:
                <com.lory.library.advertisement.ui.BannerAdView
                    android:id="@+id/banner_ad_view"
                    android:layout_width="320dp"
                    android:layout_height="50dp" />    
                    
            DEFAULT AD JSON PASS IN MANIFEST (default_ad_config)
            {
              "syncIntervalHour": 168,
              "adInfoList": [
                {
                  "adId": "1542454282915",
                  "appId": "1542553549716",
                  "adType": 0,
                  "adProvider": 1
                },
                {
                  "adId": "1542583872851",
                  "appId": "1542553549716",
                  "adType": 1,
                  "adProvider": 1
                }
              ]
            }              