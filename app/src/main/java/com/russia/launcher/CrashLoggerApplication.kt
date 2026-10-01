package com.russia.launcher

import android.app.Application
import java.io.File
import java.io.PrintWriter
import java.io.StringWriter

class CrashLoggerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val defaultHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler { thread, throwable ->
            try {
                val sw = StringWriter()
                throwable.printStackTrace(PrintWriter(sw))
                val logFile = File(getExternalFilesDir(null), "crash_log.txt")
                logFile.writeText(sw.toString())
            } catch (e: Exception) {
                // ignore
            }
            defaultHandler?.uncaughtException(thread, throwable)
        }
    }
}