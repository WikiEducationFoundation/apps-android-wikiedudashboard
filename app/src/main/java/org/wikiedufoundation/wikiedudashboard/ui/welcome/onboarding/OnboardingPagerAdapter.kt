package org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_onboarding_page.view.*
import org.wikiedufoundation.wikiedudashboard.R
import org.wikiedufoundation.wikiedudashboard.ui.welcome.onboarding.page.OnboardingPage

/*
* This OnBoardingPagerAdapter is used
* for managing the onboarding views
* */
class OnBoardingPagerAdapter(private val onBoardingPageList:Array<OnboardingPage> = OnboardingPage.values())
    : RecyclerView.Adapter<PagerViewHolder>() {


    //Here the item_on_boarding page is inflated
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): PagerViewHolder {
        return LayoutInflater.from(parent.context).inflate(
            PagerViewHolder.LAYOUT, parent, false
        ).let { PagerViewHolder(it) }
    }

    //This get the size of the items in the OnBoardingPage
    override fun getItemCount() = onBoardingPageList.size

    //This binds the views to the viewholder
    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(onBoardingPageList[position])
    }
}

// This holds the views in the adapter
class PagerViewHolder(private val root: View) : RecyclerView.ViewHolder(root) {
    fun bind(onBoardingPage: OnboardingPage) {
        val res = root.context.resources
        root.titleTv?.text = res.getString(onBoardingPage.titleResource)
        root.subTitleTv?.text = res.getString(onBoardingPage.subTitleResource)
        root.descTV?.text = res.getString(onBoardingPage.descriptionResource)
        root.img.setImageResource(onBoardingPage.logoResource)
    }

    companion object {
        @LayoutRes
        val LAYOUT = R.layout.item_onboarding_page
    }
}