package com.noahgeren.trailangel.ui.common

import android.content.Context
import android.content.DialogInterface
import android.view.View
import androidx.appcompat.app.AlertDialog

object AlertBuilder {

    fun show(context: Context, title: String, message: String?,
             view: View?, positiveText: String, negativeText: String,
             positiveAction: DialogInterface.OnClickListener,
             negativeAction: DialogInterface.OnClickListener) {
        AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setView(view)
                .setPositiveButton(positiveText, positiveAction)
                .setNegativeButton(negativeText, negativeAction)
                .show()
    }

}