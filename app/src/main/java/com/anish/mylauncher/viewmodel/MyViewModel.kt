package com.anish.mylauncher.viewmodel

import android.app.Application
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anish.mylauncher.model.AppInfo
import com.anish.mylauncher.model.ClickListener
import com.anish.mylauncher.view.Adapter


class MyViewModel(application: Application) : AndroidViewModel(application), ClickListener {

    val TAG = MyViewModel::class.java.name

    var adapter : Adapter = Adapter(this)
    val pkgManager: PackageManager by lazy {
        getApplication<Application>().getPackageManager()
    }
    var launchIntent_LiveData = MutableLiveData<Intent>()

    fun fetchAppDetails(){

        adapter.appInfos.clear()

        val i = Intent(Intent.ACTION_MAIN, null).let {
            it.addCategory(Intent.CATEGORY_LAUNCHER)
        }

        val resolveInfos = pkgManager.queryIntentActivities(i, 0)
        for (ri in resolveInfos) {
            var pckInfo = pkgManager.getPackageInfo(ri.activityInfo.packageName, 0)

            val appInfo = AppInfo(
                ri.loadLabel(pkgManager).toString(),
                ri.activityInfo.applicationInfo.packageName,
                ri.activityInfo.loadIcon(pkgManager),
                ri.activityInfo.name,
                pckInfo.versionCode,
                pckInfo.versionName
            )
            adapter.appInfos.add(appInfo)
        }
        adapter.appInfos.sortWith(compareBy{it.appName})
        adapter.notifyDataSetChanged()

    }

    override fun onItemClick(appInfo: AppInfo, pos: Int) {
        Log.i(TAG, "onItemClick: $appInfo $pos")

        launchIntent_LiveData.value = pkgManager.getLaunchIntentForPackage(appInfo.pkgName)!!

    }

}