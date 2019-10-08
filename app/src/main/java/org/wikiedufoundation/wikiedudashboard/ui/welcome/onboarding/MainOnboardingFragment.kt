package org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.wikiedufoundation.wikiedudashboard.R

/**
* This holds the OnBoardingView and launches the view
*/
class MainOnboardingFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_onboarding, container, false)
    }

}
