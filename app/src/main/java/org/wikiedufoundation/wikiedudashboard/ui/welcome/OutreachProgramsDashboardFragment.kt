package org.wikiedufoundation.wikiedudashboard.ui.welcome

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.home.HomeActivity
import org.wikiedufoundation.wikiedudashboard.util.Urls
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [OutreachProgramsDashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OutreachProgramsDashboardFragment : Fragment() {

    private var mParam1: String? = null
    private var mParam2: String? = null

    private var cv_signup_wikipedia: CardView? = null
    private var cv_login_wikipedia: CardView? = null
    private var webView: WebView? = null
    private var ll_login_layout: LinearLayout? = null
    private var progressBar: ProgressBar? = null

    private var cookies: String? = null
    private var sharedPrefs: SharedPrefs? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mParam1 = it.getString(ARG_PARAM2)
            mParam2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_outreach_programs_dashboard, container, false)
        cv_signup_wikipedia = view.findViewById(R.id.cv_signup_wikipedia)
        cv_login_wikipedia = view.findViewById(R.id.cv_login_wikipedia)
        webView = view.findViewById(R.id.webView)
        ll_login_layout = view.findViewById(R.id.ll_login_layout)
        progressBar = view.findViewById(R.id.progressBar)

        val context: Context? = context
        sharedPrefs = SharedPrefs(context)
        setWebView()
        setOnClickListeners()
        return view
    }

    private fun setWebView() {
        webView?.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                progressBar?.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String) {
                Timber.d(url)
                if (url == "https://outreachdashboard.wmflabs.org/") {
                    proceedToLogin(url)
                } else {
                    super.onPageFinished(view, url)
                    webView?.visibility = View.VISIBLE
                }
                progressBar?.visibility = View.GONE
            }
        }
    }

    private fun proceedToLogin(url: String) {
        Toast.makeText(context, "Logged In", Toast.LENGTH_SHORT).show()
        cookies = CookieManager.getInstance().getCookie(url)
        Timber.d("All the cookies in a string: $cookies")
        sharedPrefs?.outreachDashboardCookies = cookies
        Urls.BASE_URL = Urls.OUTREACH_DASHBOARD_BASE_URL
        sharedPrefs?.cookies = cookies
        sharedPrefs?.outreachDashboardCookies = cookies
        sharedPrefs?.setLogin(true)
        startActivity(Intent(context, HomeActivity::class.java))
        activity?.finish()
    }

    private fun setOnClickListeners() {
        cv_login_wikipedia?.setOnClickListener {
            //                String url = "https://dashboard.wikiedu.org/users/auth/mediawiki";
            val url = "https://outreachdashboard.wmflabs.org/users/auth/mediawiki"
            webView?.loadUrl(url)
        }
        cv_signup_wikipedia?.setOnClickListener {
            //                String url = "https://dashboard.wikiedu.org/users/auth/mediawiki_signup";
            val url = "https://outreachdashboard.wmflabs.org/users/auth/mediawiki_signup"
            webView?.loadUrl(url)
        }
    }

    companion object {

        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WikiEducationDashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): OutreachProgramsDashboardFragment {
            val fragment = OutreachProgramsDashboardFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
} // Required empty public constructor
