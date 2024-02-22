package com.example.task_4_callbacks__dialogs.MainScreen

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.task_4_callbacks__dialogs.CommonKeys
import com.example.task_4_callbacks__dialogs.ProjectConstants

class PermissionUtils(context: Context) {
    fun checkPhonePermission(activity: Activity,): Boolean{
     return ContextCompat.checkSelfPermission(
         activity,Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED
    }
    fun checkMessagePermission(activity: Activity,): Boolean{
        return ContextCompat.checkSelfPermission(
            activity,Manifest.permission.SEND_SMS)==PackageManager.PERMISSION_GRANTED
    }
    fun checkStoragePermission(activity: Activity,): Boolean{
        return ContextCompat.checkSelfPermission(
            activity,Manifest.permission.READ_EXTERNAL_STORAGE)==PackageManager.PERMISSION_GRANTED
    }
    fun checkMapPermission(activity: Activity,): Boolean{
        return ContextCompat.checkSelfPermission(
            activity,Manifest.permission.ACCESS_FINE_LOCATION)==PackageManager.PERMISSION_GRANTED
    }
}