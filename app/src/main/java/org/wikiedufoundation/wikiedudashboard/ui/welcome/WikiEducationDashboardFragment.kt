package org.wikiedufoundation.wikiedudashboard.ui.welcome

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.CookieManager
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_wiki_edu_dashboard.*
import androidx.fragment.app.Fragment
import org.koin.android.ext.android.inject
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.home.HomeActivity
import org.wikiedufoundation.wikiedudashboard.util.Urls
import timber.log.Timber

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * Use the [WikiEducationDashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WikiEducationDashboardFragment : Fragment() {

    private val sharedPrefs: SharedPrefs by inject()

    private var mParam1: String? = null
    private var mParam2: String? = null

    private lateinit var cookies: String


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

        return inflater.inflate(R.layout.fragment_wiki_edu_dashboard, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setWebView()
        setOnClickListeners()
    }
    private fun setWebView() { 
      /** Enable JavaScript execution to display all web page content.
     *  This enables users logging in for the first time to complete
     *  the additional account set-up screens displayed after the
     *  user clicks on the OAuth screen "Allow" button
     */
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView, url: String, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                clWiki.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }

            override fun onPageFinished(view: WebView?, url: String) {
                Timber.d(url)
                if (url == "https://dashboard.wikiedu.org/") {
                    proceedToLogin(url)
                } else {
                    super.onPageFinished(view, url)
                    clWiki.visibility = View.GONE
                    webView.visibility = View.VISIBLE
                }
                progressBar.visibility = View.GONE
            }
        }
    }


    private fun proceedToLogin(url: String) {
        Toast.makeText(context, "Logged In", Toast.LENGTH_SHORT).show()
        cookies = CookieManager.getInstance().getCookie(url)
        Timber.d("All the cookies in a string: $cookies")
        sharedPrefs.outreachDashboardCookies = cookies
        Urls.BASE_URL = Urls.WIKIEDU_DASHBOARD_BASE_URL
        sharedPrefs.cookies = cookies
        sharedPrefs.wikiEduDashboardCookies = cookies
        sharedPrefs.setLogin(true)
        startActivity(Intent(context, HomeActivity::class.java))
        activity?.finish()
    }

    private fun setOnClickListeners() {
        cvLoginWikipedia.setOnClickListener {
            val url = "https://dashboard.wikiedu.org/users/auth/mediawiki"
            progressBar.visibility = View.VISIBLE
            webView.loadUrl(url)
        }
        cvSignUpWikipedia.setOnClickListener {
            val url = "https://dashboard.wikiedu.org/users/auth/mediawiki_signup"
            progressBar.visibility = View.VISIBLE
            webView.loadUrl(url)
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
        fun newInstance(param1: String, param2: String): WikiEducationDashboardFragment {
            val fragment = WikiEducationDashboardFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
} // Required empty public constructor
