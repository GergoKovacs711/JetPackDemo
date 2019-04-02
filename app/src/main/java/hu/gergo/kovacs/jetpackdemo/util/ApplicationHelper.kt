package hu.gergo.kovacs.jetpackdemo.util

import android.app.Application
import android.content.Context

/**
 * @author Gergo Kovacs
 *
 * @version 1.0
 *
 * @date 2019.03.30.
 */


class ApplicationHelper : Application() {

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }

    companion object {
        private var INSTANCE: ApplicationHelper? = null

        val context: Context
            get() = INSTANCE!!.applicationContext
    }
}
