
package org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding.page

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import org.wikiedufoundation.wikiedudashboard.R

/**
* This model set the page items and contents
* */
enum class OnboardingPage(@StringRes val titleResource: Int,
                          @StringRes val subTitleResource: Int,
                          @StringRes val descriptionResource: Int,
                          @DrawableRes val logoResource: Int) {

  Screen1(R.string.screen1_title, R.string.sub1_description, R.string.main_description1, R.drawable.ic_monitor),
  Screen2(R.string.screen2_title, R.string.sub2_description, R.string.main_description2, R.drawable.ic_track_programs),


}