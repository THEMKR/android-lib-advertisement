package activity

import android.Manifest
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.ads.MobileAds
import com.lory.library.advertisement.AdvertisementLib
import com.lory.library.advertisement.R
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.Tracer
import com.lory.library.advertisement.utils.Utils
import com.lory.library.ui.controller.AppPermissionController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AppPermissionController.OnAppPermissionControllerListener {

    companion object {
        private const val TAG: String = "MKR" + ".MainActivity"
    }

    private var appPermissionController: AppPermissionController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MobileAds.initialize(this, Utils.getMetaDataString(this, Constants.MetaDataKeys.DEFAULT_AD_PROVIDER_APP_ID))
        var permissions: Array<String> = arrayOf(
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.RECEIVE_BOOT_COMPLETED,
                Manifest.permission.BLUETOOTH,
                Manifest.permission.INTERNET,
                Manifest.permission.ACCESS_WIFI_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE
        )
        appPermissionController = AppPermissionController(this, permissions, this)
        appPermissionController?.initializedAppPermission()
        button_interstitial.setOnClickListener(object : View.OnClickListener {
            override fun onClick(p0: View?) {
                Tracer.debug(TAG, "INTERSTITIAL onClick: ")
                AdvertisementLib.showInterstitialAd(this@MainActivity)
            }
        })
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        appPermissionController?.onRequestPermissionsResult(requestCode, permissions.toList().toTypedArray(), grantResults)
    }

    override fun onAppPermissionControllerListenerHaveAllRequiredPermission() {
        Tracer.debug(TAG, "onAppPermissionControllerListenerHaveAllRequiredPermission: ")
        AdvertisementLib.initialize(this)
        AdvertisementLib.showBannerAd(this, banner_ad_view)
    }
}