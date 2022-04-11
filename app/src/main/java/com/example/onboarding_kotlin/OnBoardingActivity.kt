package com.example.onboarding_kotlin

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.OnPageChangeListener

class OnBoardingActivity : AppCompatActivity() {
    var viewPager: ViewPager? = null
    var dotsLayout: LinearLayout? = null

    var btn: Button? = null

    var sliderAdapter: SliderAdapter? = null
    lateinit var dots: Array<TextView?>

    var animation: Animation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)
        viewPager = findViewById(R.id.slider)
        dotsLayout = findViewById(R.id.dots)
        btn = findViewById(R.id.get_started_btn)
        addDots(0)
        with(viewPager) { this?.addOnPageChangeListener(changeListener) }

        //Call Adapter
        sliderAdapter = SliderAdapter(this)
        with(viewPager) { this?.setAdapter(sliderAdapter) }
        btn?.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(
                    this@OnBoardingActivity,
                    MainActivity::class.java
                )
            )
        })
    }

    private fun addDots(position: Int) {
        dots = arrayOfNulls(3)
        dotsLayout!!.removeAllViews()
        for (i in dots.indices) {
            dots[i] = TextView(this)
            dots[i]!!.text = Html.fromHtml("&#8226;")
            dots[i]!!.textSize = 35f
            dotsLayout!!.addView(dots[i])
        }
        if (dots.size > 0) {
            dots[position]!!.setTextColor(resources.getColor(R.color.purple_200))
        }
    }

    var changeListener: OnPageChangeListener = object : OnPageChangeListener {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
        }

        override fun onPageSelected(position: Int) {
            addDots(position)
            if (position == 0) {
                btn!!.visibility = View.INVISIBLE
            } else if (position == 1) {
                btn!!.visibility = View.INVISIBLE
            } else {
                animation =
                    AnimationUtils.loadAnimation(this@OnBoardingActivity, R.anim.slide_animation)
                btn!!.animation = animation
                btn!!.visibility = View.VISIBLE
            }
        }

        override fun onPageScrollStateChanged(state: Int) {}
    }
}