# MKR-ANDROID-ADVERTISEMENT

#   AndroidManifest.xml
        <uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
        <uses-permission android:name="android.permission.READ_CALENDAR" />
        <uses-permission android:name="android.permission.WRITE_CALENDAR" />
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
            
        <!-- START APP START -->
        <activity
            android:name="com.startapp.android.publish.ads.list3d.List3DActivity"
            android:theme="@android:style/Theme" />

        <activity
            android:name="com.startapp.android.publish.adsCommon.activities.OverlayActivity"
            android:configChanges="orientation|keyboardHidden|screenSize"
            android:theme="@android:style/Theme.Translucent" />

        <activity
            android:name="com.startapp.android.publish.adsCommon.activities.FullScreenActivity"
            android:configChanges="orientation|keyboardHidden|screenSize"
            android:theme="@android:style/Theme" />
        <!-- START APP END -->

        <!-- INMOBE START -->
        <activity
            android:name="com.inmobi.rendering.InMobiAdActivity"
            android:configChanges="keyboardHidden|orientation|keyboard|smallestScreenSize|screenSize|screenLayout"
            android:hardwareAccelerated="true"
            android:resizeableActivity="false"
            android:theme="@android:style/Theme.NoTitleBar"
            tools:ignore="UnusedAttribute" />
        <!-- INMOBE END -->       

#	Project Level Gradle
		repositories {
			maven {
				url "https://api.bitbucket.org/1.0/repositories/THEMKR/android-lib-advertisement/raw/releases"
				credentials {
					username 'THEMKR'
					password '<PASSWORD>'
				}
			}
		}

#	APP Level Gradle
        <!-- DEPENDENCY INCLUDE IN LIB -->
        // START APP
        implementation 'com.startapp:inapp-sdk:3.10.1'
        // AD MOB
        implementation 'com.google.android.gms:play-services-ads:16.0.0'
        // InMobi
        implementation 'com.inmobi.monetization:inmobi-ads:7.2.1'
        implementation 'com.google.android.gms:play-services-base:16.0.1'
        implementation 'com.google.android.gms:play-services-location:16.0.0'
        implementation 'com.google.android.gms:play-services-plus:16.0.0'
        implementation 'com.android.support:multidex:1.0.3'
        // FIREBASE LIB
        implementation 'com.lory.library:firebase:1.0.1'
        implementation 'com.google.code.gson:gson:2.2.4'
        
        <!-- SUPPORT MUST BE INCLUDE -->
        implementation 'com.android.support:support-v4:28.0.0'
        implementation 'com.squareup.picasso:picasso:2.5.2'
        implementation 'com.android.support:recyclerview-v7:28.0.0'
        implementation 'com.lory.library:advertisement:1.0.0'
        
        <!-- SUPPORT MY BE INCLUDE -->
        implementation 'com.google.firebase:firebase-database:16.0.4'
        implementation 'com.google.firebase:firebase-core:16.0.4'
        
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
              "version": 1,
              "adInfoList": [
                {
                  "adId": "",
                  "appId": "",
                  "adType": 0(BANNER),
                  "adProvider": Int-Id
                },
                {
                  "adId": "",
                  "appId": "",
                  "adType": 1(INTERSTITIAL),
                  "adProvider": Int-Id
                }
              ]
            }              