/*
 * Copyright (c) 2022 gparap
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package gparap.apps.horoscope.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import gparap.apps.horoscope.MainActivity
import gparap.apps.horoscope.R
import gparap.apps.horoscope.utils.AppConstants

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //animate the zodiac signs image
        val zodiacsImage = findViewById<ImageView>(R.id.imageViewZodiac)
        val anim = AnimationUtils.loadAnimation(this, R.anim.anim_splash)
        anim.interpolator = LinearInterpolator()
        anim.duration = AppConstants.SPLASH_ANIMATION_DURATION
        zodiacsImage.startAnimation(anim)

        //splash and goto main activity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, AppConstants.SPLASH_SCREEN_DURATION)
    }
}