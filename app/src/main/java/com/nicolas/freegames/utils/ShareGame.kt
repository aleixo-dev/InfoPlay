package com.nicolas.freegames.utils

import android.content.Context
import androidx.core.app.ShareCompat

object ShareGame {

    /**
     * share application if it has been published on google play store, otherwise it doesn't work.
     */
    fun shareMyApplication(context: Context) {
        ShareCompat.IntentBuilder(context)
            .setType("text/plain")
            .setChooserTitle("Compartilhar Aplicativo em")
            .setText("http://play.google.com/store/apps/details?id=" + context.packageName)
            .startChooser()
    }
}