package com.example.task_4_callbacks__dialogs.MainScreen

import android.Manifest
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.AttributeSet
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.example.task_4_callbacks__dialogs.CommonKeys
import com.example.task_4_callbacks__dialogs.ProjectConstants
import com.example.task_4_callbacks__dialogs.R
import com.example.task_4_callbacks__dialogs.databinding.ActivityMainScreenBinding

class MainScreen : AppCompatActivity(), DialogUtils.DialogActionListener {
    private lateinit var binding: ActivityMainScreenBinding
    private lateinit var permissionUtils: PermissionUtils
    private lateinit var dialogUtils: DialogUtils

    private val callPermissionActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                dialogUtils.customPhoneDialog(this)
            } else {
                // Permission is denied
            }
        }
    private val messagePermissionActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                dialogUtils.customMessageDialog(this)
            } else {
                // Permission is denied
            }
        }
    private val mapsPermissionActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                dialogUtils.customGoogleMapDialog(this)
            } else {
                // Permission is denied
            }
        }
    private val storagePermissionActivityResultLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                dialogUtils.customStorageDialog(this)
            } else {
                // Permission is denied
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializingBinding()
        permissionUtils = PermissionUtils(this)
        dialogUtils = DialogUtils(this)
        dialogUtils.dialogActionListener = this
        clickListeners()
    }

    override fun onCallButtonClicked() {
        val phoneNumber = ProjectConstants.PHONE_NO
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel: $phoneNumber")
        startActivity(callIntent)
    }
    override fun onMessageButtonClicked() {
        val phoneNumber = ProjectConstants.PHONE_NO
        val smsIntent = Intent(Intent.ACTION_VIEW)
        smsIntent.data = Uri.parse("sms: $phoneNumber")
        startActivity(smsIntent)
    }
    override fun onMapButtonClicked() {
        val mapIntent = Intent(Intent.ACTION_VIEW,
            Uri.parse("google.navigation:q=30.3324,69.2467&mode=d"))
        mapIntent.setPackage("com.google.android.apps.maps")
        startActivity(mapIntent)
        if(mapIntent.resolveActivity(this.packageManager)!=null)
        {
            startActivity(mapIntent)
        }
    }

    private fun clickListeners() {
        binding.phoneCall.setOnClickListener(View.OnClickListener {
            if (permissionUtils.checkPhonePermission(this)) {
                dialogUtils.customPhoneDialog(this)
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this, Manifest.permission.CALL_PHONE)
                ) {
                    dialogUtils.settingDialogForPhone(this)
                } else {
                    callPermissionActivityResultLauncher.launch(Manifest.permission.CALL_PHONE)

                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.CALL_PHONE),
                        ProjectConstants.CALL_PERMISSION_REQUEST
                    )
                }
            }
        })

        binding.message.setOnClickListener(View.OnClickListener {
            if (permissionUtils.checkMessagePermission(this)) {
                dialogUtils.customMessageDialog(this)
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this, Manifest.permission.SEND_SMS)
                ) {
                    dialogUtils.settingDialogForMessage(this)

                } else {
                    messagePermissionActivityResultLauncher.launch(Manifest.permission.SEND_SMS)
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.SEND_SMS),
                        ProjectConstants.SMS_PERMISSION_REQUEST
                    )
                }
            }

        })

        binding.googleMaps.setOnClickListener(View.OnClickListener {
            if (permissionUtils.checkMapPermission(this)) {
                dialogUtils.customGoogleMapDialog(this)
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this, Manifest.permission.ACCESS_FINE_LOCATION)
                ) {
                    dialogUtils.settingDialogForMaps(this)
                } else {
                    mapsPermissionActivityResultLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                            ProjectConstants.LOCATION_PERMISSION_CODE
                        )
                    }
                }
            }
        })

        binding.storage.setOnClickListener(View.OnClickListener {
            if (permissionUtils.checkStoragePermission(this)) {
                dialogUtils.customStorageDialog(this)
            } else {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        this, Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                ) {
                    dialogUtils.settingDialogForStorage(this)
                } else {
                    storagePermissionActivityResultLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                        ProjectConstants.STORAGE_PERMISSION_CODE
                    )
                }
            }
        })
    }
    private fun initializingBinding() {
        binding = ActivityMainScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }
}