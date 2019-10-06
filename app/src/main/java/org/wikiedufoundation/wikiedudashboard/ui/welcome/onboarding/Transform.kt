package org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding

import android.view.View
import kotlinx.android.synthetic.main.item_onboarding_page.view.*

fun setParallaxTransformation(page: View, position: Float){
    page.apply {
        val parallaxView = this.img
        when {
            position < -1 -> // [-Infinity,-1)
                // This page is way off-screen to the left.
                alpha = 1f
            position <= 1 -> { // [-1,1]
                parallaxView.translationX = -position * (width / 2) //Half the normal speed
            }
            else -> // (1,+Infinity]
                // This page is way off-screen to the right.
                alpha = 1f
        }
    }

}