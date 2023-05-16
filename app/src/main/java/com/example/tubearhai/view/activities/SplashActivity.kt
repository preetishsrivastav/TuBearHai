package com.example.tubearhai.view.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.tubearhai.R
import com.example.tubearhai.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {
    private lateinit var splashBinding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

        val animation = AnimationUtils.loadAnimation(this,R.anim.anim)
        splashBinding.tvAppName.animation=animation
        splashBinding.ivSplash.animation=animation

        animation.setAnimationListener(object: Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
//
            }

            override fun onAnimationEnd(animation: Animation?) {
             Handler(Looper.getMainLooper()).postDelayed({
                 startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                 finish()
             },2000)
            }

            override fun onAnimationRepeat(animation: Animation?) {
//
            }


        })


    }
}