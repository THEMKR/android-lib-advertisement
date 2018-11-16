package activity

import android.content.Context
import android.view.View
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.lory.library.advertisement.BuildConfig
import com.lory.library.advertisement.utils.Constants
import com.lory.library.advertisement.utils.Tracer
import com.lory.library.advertisement.utils.Utils

class AdUtils : AdListener() {
    companion object {
        private val TAG: String = BuildConfig.BASE_TAG + ".AdUtils";
        private var adUtils: AdUtils? = null

        /**
         * Method to get the Instance of Ad Utils
         */
        fun getInstance(): AdUtils {
            if (adUtils == null) {
                adUtils = AdUtils()
            }
            return adUtils!!
        }
    }

    private var mInterstitialAd: InterstitialAd? = null

    /**
     * Method to initialize the interstitial Ad
     * @param context
     */
    fun init(context: Context) {
        Tracer.debug(TAG, "init: ");
        if (mInterstitialAd == null) {
            mInterstitialAd = InterstitialAd(context)
            mInterstitialAd?.adUnitId = "ca-app-pub-8713916254370167/9462717089"
            mInterstitialAd?.loadAd(adRequest)
            mInterstitialAd?.adListener = this
        }
    }


    /**
     * Request object for the ad
     *
     * @return
     */
    private val adRequest: AdRequest
        get() {
            Tracer.debug(TAG, "getAdRequest()")
            return AdRequest.Builder().addTestDevice("7D7D0BB53322C0DB49F2F2CCE8550FA0").build()
        }

    /**
     * To check weather the Ad is Ready to shown or not
     */
    val isAdReady: Boolean
        get() {
            return mInterstitialAd?.isLoaded ?: false
        }

    /**
     * Method to show the Banner Ad
     * @param adView
     * @param adViewContainer
     */
    fun showAdBanner(adView: AdView, adViewContainer: View) {
        Tracer.debug(TAG, "showBannerAd: ")
        adView.adListener = object : AdListener() {
            override fun onAdFailedToLoad(i: Int) {
                super.onAdFailedToLoad(i)
                Tracer.debug(TAG, "showBannerAd()")
                if (adViewContainer != null) {
                    adViewContainer.visibility = View.GONE
                }
            }

            override fun onAdLoaded() {
                super.onAdLoaded()
                if (adViewContainer != null) {
                    adViewContainer.visibility = View.VISIBLE
                }
            }
        }
        adView.loadAd(adRequest)
    }

    /**
     * Method to show the Interstitial Ad
     * @param context
     * @param  onAdUtilsListener
     */
    fun showInterstitialAd(context: Context) {
        Tracer.debug(TAG, "showInterstitialAd()")
        if (isAdReady) {
            mInterstitialAd?.show()
            mInterstitialAd = null
        }
        init(context)
    }

    override fun onAdClicked() {
        super.onAdClicked()
        Tracer.debug(TAG, "onAdClicked: ");
    }

    override fun onAdImpression() {
        super.onAdImpression()
        Tracer.debug(TAG, "onAdImpression: ");
    }

    override fun onAdClosed() {
        super.onAdClosed()
        Tracer.debug(TAG, "onAdClosed()")
    }

    override fun onAdFailedToLoad(i: Int) {
        super.onAdFailedToLoad(i)
        Tracer.debug(TAG, "onAdFailedToLoad() $i")
        mInterstitialAd = null
    }

    override fun onAdLeftApplication() {
        super.onAdLeftApplication()
        Tracer.debug(TAG, "onAdLeftApplication()")
    }

    override fun onAdOpened() {
        super.onAdOpened()
        Tracer.debug(TAG, "onAdOpened()")
    }

    override fun onAdLoaded() {
        super.onAdLoaded()
        Tracer.debug(TAG, "onAdLoaded()")
    }
}