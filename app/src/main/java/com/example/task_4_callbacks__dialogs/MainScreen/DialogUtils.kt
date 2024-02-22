package com.example.task_4_callbacks__dialogs.MainScreen

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.provider.Settings
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.task_4_callbacks__dialogs.CommonKeys
import com.example.task_4_callbacks__dialogs.ProjectConstants

import com.example.task_4_callbacks__dialogs.R

class DialogUtils(context: Context) {
    interface DialogActionListener {
        fun onCallButtonClicked()
        fun onMessageButtonClicked()
        fun onMapButtonClicked()

    }
    var dialogActionListener: DialogActionListener? = null


    private val settingPhoneDialogTitle = context.getString(R.string.phone_permission_required)
    private val settingPhoneDialogBody = context.getString(R.string.require_phone_permission)
    private val settingMessageDialogTitle = context.getString(R.string.message_permission_required)
    private val settingMessageDialogBody = context.getString(R.string.require_message_permission)
    private val settingGoogleMapDialogTitle = context.getString(R.string.location_permission_required)
    private val settingGoogleMapDialogBody = context.getString(R.string.require_location_permission)
    private val settingStorageDialogTitle = context.getString(R.string.storage_permission_required)
    private val settingStorageDialogBody = context.getString(R.string.require_storage_permission)
    private val goToSettings = context.getString(R.string.go_to_settings)
    private val cancel = context.getString(R.string.no)

    fun customPhoneDialog(activity: Activity) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_box_call)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val callNoButton = dialog.findViewById<Button>(R.id.callNoButton)
        val callYesButton = dialog.findViewById<Button>(R.id.callYesButton)

        val callTitleTextView = dialog.findViewById<TextView>(R.id.callTitle)

        val newDrawable = ContextCompat.getDrawable(activity, R.drawable.phone)
        newDrawable?.setBounds(0, 0, newDrawable.intrinsicWidth, newDrawable.intrinsicHeight)
        callTitleTextView.setCompoundDrawablesWithIntrinsicBounds(newDrawable, null, null, null)

        callNoButton.setOnClickListener {
            dialog.dismiss()
        }
        callYesButton.setOnClickListener {
            dialogActionListener?.onCallButtonClicked()
            dialog.dismiss()
        }
        dialog.show()
    }

    fun customMessageDialog(activity: Activity) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_box_message)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val messageNoButton = dialog.findViewById<Button>(R.id.messageNoButton)
        val messageYesButton = dialog.findViewById<Button>(R.id.messageYesButton)

        messageNoButton.setOnClickListener {
            dialog.dismiss()
        }
        messageYesButton.setOnClickListener {
            dialogActionListener?.onMessageButtonClicked()
            dialog.dismiss()
        }
        dialog.show()
    }

    fun customGoogleMapDialog(activity: Activity) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_box_map)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val mapNoButton = dialog.findViewById<Button>(R.id.mapNoButton)
        val mapYesButton = dialog.findViewById<Button>(R.id.mapYesButton)
        mapNoButton.setOnClickListener {
            dialog.dismiss()
        }
        mapYesButton.setOnClickListener {
            dialogActionListener?.onMapButtonClicked()
            dialog.dismiss()
        }
        dialog.show()
    }

    fun customStorageDialog(activity: Activity) {
        val dialog = Dialog(activity)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.custom_dialog_box_storage)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val storageYesButton = dialog.findViewById<Button>(R.id.storageOkayButton)

        storageYesButton.setOnClickListener {

            dialog.dismiss()
        }
        dialog.show()
    }

    fun settingDialogForPhone(activity: Activity)
    {
        AlertDialog.Builder(activity)
            .setTitle(settingPhoneDialogTitle)
            .setMessage(settingPhoneDialogBody)
            .setPositiveButton(goToSettings) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts(CommonKeys.PACKAGE, activity.packageName, null)
                intent.data = uri
                activity.startActivity(intent)
            }.setNegativeButton(cancel) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun settingDialogForMessage(activity: Activity)
    {
        AlertDialog.Builder(activity)
            .setTitle(settingMessageDialogTitle)
            .setMessage(settingMessageDialogBody)
            .setPositiveButton(goToSettings) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts(CommonKeys.PACKAGE, activity.packageName, null)
                intent.data = uri
                activity.startActivity(intent)
            }.setNegativeButton(cancel) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun settingDialogForMaps(activity: Activity)
    {
        AlertDialog.Builder(activity)
            .setTitle(settingGoogleMapDialogTitle)
            .setMessage(settingGoogleMapDialogBody)
            .setPositiveButton(goToSettings) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts(CommonKeys.PACKAGE, activity.packageName, null)
                intent.data = uri
                activity.startActivity(intent)
            }.setNegativeButton(cancel) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }

    fun settingDialogForStorage(activity: Activity)
    {
        AlertDialog.Builder(activity)
            .setTitle(settingStorageDialogTitle)
            .setMessage(settingStorageDialogBody)
            .setPositiveButton(goToSettings) { _, _ ->
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                val uri = Uri.fromParts(CommonKeys.PACKAGE, activity.packageName, null)
                intent.data = uri
                activity.startActivity(intent)
            }.setNegativeButton(cancel) { dialog, _ ->
                dialog.dismiss()
            }.show()
    }



}
