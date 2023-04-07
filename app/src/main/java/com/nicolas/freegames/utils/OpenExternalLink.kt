package com.nicolas.freegames.utils

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import  androidx.activity.result.ActivityResult

object OpenExternalLink {

    fun open(
        url: String,
        launcher: ManagedActivityResultLauncher<Intent, ActivityResult>
    ) {
        val uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, uri)
        val chooserIntent = Intent.createChooser(intent, "Abrir com...")
        launcher.launch(chooserIntent)
    }
}