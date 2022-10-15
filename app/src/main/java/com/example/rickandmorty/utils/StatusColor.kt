package com.example.rickandmorty.utils

import android.app.Activity
import android.view.Window
import android.view.WindowManager
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.FragmentActivity

object StatusColor {
    fun Activity.changeStatusBarColor(color: Int, isLight: Boolean){
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = isLight
    }

}