# MKR-ANDROID-ADVERTISEMENT

#   AndroidManifest.xml
        <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
        <uses-permission android:name="android.permission.READ_CALENDAR" /> //OPTIONAL
        <uses-permission android:name="android.permission.WRITE_CALENDAR" /> //OPTIONAL
        <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
        <uses-permission android:name="android.permission.INTERNET" />
        <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
        <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
        <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
        <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
        <uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
        <uses-permission android:name="android.permission.BLUETOOTH" />

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
			maven { url 'https://jitpack.io' }
		}

#	APP Level Gradle

            implementation 'com.github.THEMKR:android-lib-advertisement:1.0.0'

        <!-- DEPENDENCY INCLUDE IN LIB -->
        implementation"org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
        // START APP
        implementation 'com.startapp:inapp-sdk:4.0.2'
        // AD MOB
        implementation 'com.google.android.gms:play-services-ads:18.0.0'
        // InMobi
        implementation 'com.inmobi.monetization:inmobi-ads:7.2.1'
        implementation 'com.google.android.gms:play-services-base:17.0.0'
        implementation 'com.google.android.gms:play-services-location:17.0.0'
        implementation 'com.google.android.gms:play-services-plus:17.0.0'

        implementation 'com.android.support:multidex:1.0.3'

        // APP CONFIG / FIRE-BASE
        implementation 'com.github.THEMKR:android-lib-appconfig:1.0.0'
        implementation 'com.google.code.gson:gson:2.8.5'
        implementation 'com.google.firebase:firebase-database:18.0.0'
        implementation 'com.google.firebase:firebase-core:17.0.0'
        implementation 'com.github.THEMKR:android-lib-firebase:1.0.0'
        
#   USE
            INIT LIB IN EVERY ACTIVITY
                AdvertisementLib.initialize(this)
            
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
              "syncIntervalHour": 24,
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