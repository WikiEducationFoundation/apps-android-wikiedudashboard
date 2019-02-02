package org.wikiedufoundation.wikiedudashboard.welcome;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.wikiedufoundation.wikiedudashboard.R;
import org.wikiedufoundation.wikiedudashboard.helper.SharedPrefs;
import org.wikiedufoundation.wikiedudashboard.home.HomeActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link Welcome4Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Welcome4Fragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    @BindView(R.id.cv_signup_wikipedia)
    CardView cv_signup_wikipedia;

    @BindView(R.id.cv_login_wikipedia)
    CardView cv_login_wikipedia;

    @BindView(R.id.webView)
    WebView webView;

    @BindView(R.id.ll_login_layout)
    LinearLayout ll_login_layout;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private String cookies;
    private SharedPrefs sharedPrefs;

    public Welcome4Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Welcome4Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Welcome4Fragment newInstance(String param1, String param2) {
        Welcome4Fragment fragment = new Welcome4Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_welcome4, container, false);
        ButterKnife.bind(this, view);
        final Context context = getContext();
        cookies = "";
        sharedPrefs = new SharedPrefs(context);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Log.d("WEB_URL: ", url);
                if (url.equals("https://dashboard.wikiedu.org/")){
                    Toast.makeText(context, "Logged In", Toast.LENGTH_SHORT).show();
                    cookies = CookieManager.getInstance().getCookie(url);
                    Log.d("Cookies: ", "All the cookies in a string:" + cookies);
                    sharedPrefs.setCookies(cookies);
                    sharedPrefs.setLogin(true);
                    startActivity(new Intent(context, HomeActivity.class));
                    getActivity().finish();
                }else if(url.equals("https://outreachdashboard.wmflabs.org/")){
                    Toast.makeText(context, "Logged In", Toast.LENGTH_SHORT).show();
                    cookies = CookieManager.getInstance().getCookie(url);
                    Log.d("Cookies: ", "All the cookies in a string:" + cookies);
                    sharedPrefs.setCookies(cookies);
                    sharedPrefs.setLogin(true);
                    startActivity(new Intent(context, HomeActivity.class));
                    getActivity().finish();
                }else {
                    super.onPageFinished(view, url);
                    webView.setVisibility(View.VISIBLE);
                }
                progressBar.setVisibility(View.GONE);
            }
        });

        cv_login_wikipedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String url = "https://dashboard.wikiedu.org/users/auth/mediawiki";
                String url = "https://outreachdashboard.wmflabs.org/users/auth/mediawiki";
                webView.loadUrl(url);
            }
        });
        cv_signup_wikipedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String url = "https://dashboard.wikiedu.org/users/auth/mediawiki_signup";
                String url = "https://outreachdashboard.wmflabs.org/users/auth/mediawiki_signup";
                webView.loadUrl(url);
            }
        });
        return view;
    }

}
