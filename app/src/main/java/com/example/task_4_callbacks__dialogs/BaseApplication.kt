package com.example.task_4_callbacks__dialogs

import android.app.Application
import android.content.Context

class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        mContext=this
    }
    companion object{
        private lateinit var mContext:Context
        fun getContext():Context
        {
            return mContext
        }
    }
}