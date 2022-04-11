package com.example.onboarding_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.airbnb.lottie.LottieAnimationView

class SliderAdapter(var context: Context) : PagerAdapter() {
    var layoutInflater: LayoutInflater? = null
    var imagesArray = intArrayOf(
        R.raw.first,
        R.raw.second,
        R.raw.third
    )
    var headingArray = intArrayOf(
        R.string.first_slide,
        R.string.second_slide,
        R.string.third_slide
    )
    var descriptionArray = intArrayOf(
        R.string.description,
        R.string.description,
        R.string.description
    )

    override fun getCount(): Int {
        return headingArray.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object` as ConstraintLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.sliding_layout, container, false)
        val lottieAnimationView: LottieAnimationView = view.findViewById(R.id.lottie)
        val lottieAnimationView2: LottieAnimationView = view.findViewById(R.id.lottie2)
        val lottieAnimationView3: LottieAnimationView = view.findViewById(R.id.lottie3)
        val heading = view.findViewById<TextView>(R.id.heading)
        val description = view.findViewById<TextView>(R.id.description)
        lottieAnimationView.setAnimation(imagesArray[position])
        lottieAnimationView2.setAnimation(imagesArray[position])
        lottieAnimationView3.setAnimation(imagesArray[position])
        heading.setText(headingArray[position])
        description.setText(descriptionArray[position])
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as ConstraintLayout)
    }
}