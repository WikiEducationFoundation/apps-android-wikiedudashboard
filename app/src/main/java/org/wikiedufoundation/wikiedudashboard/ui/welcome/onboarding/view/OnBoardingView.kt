
package org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.viewpager2.widget.ViewPager2
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.onboarding_view.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.data.preferences.SharedPrefs
import org.wikiedufoundation.wikiedudashboard.ui.welcome.WelcomeActivity
import org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding.OnBoardingPagerAdapter
import org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding.page.OnboardingPage
import org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding.setParallaxTransformation


@RequiresApi(Build.VERSION_CODES.LOLLIPOP)

class OnBoardingView @JvmOverloads
constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0, defStyleRes: Int = 0) :
  FrameLayout(context, attrs, defStyleAttr, defStyleRes) {

  private val numberOfPages by lazy { OnboardingPage.values().size }
  private  val prefManager: SharedPrefs
//  private val prefManager : OnBoardingPrefManager

  init {
    val view = LayoutInflater.from(context).inflate(R.layout.onboarding_view, this, true)
    setUpSlider(view)
    addingButtonsClickListeners()
    prefManager = SharedPrefs(view.context)
  }

  private fun setUpSlider(view: View) {
    with(slider) {
      adapter = OnBoardingPagerAdapter()
      setPageTransformer { page, position ->
        setParallaxTransformation(page, position)
      }

      addSlideChangeListener()

      val wormDotsIndicator = view.findViewById<WormDotsIndicator>(R.id.page_indicator)
      wormDotsIndicator.setViewPager2(this)

    }
  }


  private fun addSlideChangeListener() {

    slider.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
      override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        if (numberOfPages > 1) {
          val newProgress = (position + positionOffset) / (numberOfPages - 1)
          onboardingRoot.progress = newProgress
        }
      }
    })
  }

  private fun addingButtonsClickListeners() {
    nextBtn.setOnClickListener { navigateToNextSlide() }
    skipBtn.setOnClickListener {
      setFirstTimeLaunchToFalse()
      val intent = Intent(context, WelcomeActivity::class.java)
      context.startActivity(intent)
    }

    startBtn.setOnClickListener {
      setFirstTimeLaunchToFalse()
      val intent = Intent(context, WelcomeActivity::class.java)
      context.startActivity(intent)

    }
  }

  private fun setFirstTimeLaunchToFalse() {
    !prefManager.isFirstTimeLaunch
//    prefManager.isFirstTimeLaunch = false
  }

  private fun navigateToNextSlide() {
    val nextSlidePos: Int = slider?.currentItem?.plus(1) ?: 0
    slider?.setCurrentItem(nextSlidePos, true)
  }

  private fun navigateToMainACtivity() {

  }

}