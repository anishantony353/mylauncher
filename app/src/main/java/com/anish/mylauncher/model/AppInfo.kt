package com.anish.mylauncher.model

import android.graphics.drawable.Drawable

data class AppInfo(val appName:String, val pkgName:String, val icon:Drawable,
                   val mainClassName:String, val verCode:Int, val verName:String)
