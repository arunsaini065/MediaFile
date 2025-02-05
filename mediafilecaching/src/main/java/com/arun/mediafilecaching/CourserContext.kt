package com.arun.mediafilecaching

import android.content.Context
import android.util.Log

class CourseContext {
    companion object {

        private var appContext: Context? = null

        @Synchronized
        @JvmStatic
        fun bindContext(context: Context?) {
            if (context != null) {
                val var1 = context.applicationContext
                appContext = var1 ?: context
            }
        }

        @Synchronized
        @JvmStatic
        fun unbindContext(context: Context?) {
            if (context != null) {
                val var1 = context.applicationContext
                if (appContext === var1) {
                    appContext = null
                }
            }
        }

        @get:Synchronized
        @JvmStatic
        val context: Context?
            get() {
                if (appContext == null) {
                    Log.d("@Arun", ": null")
                }
                return appContext
            }
    }

}
