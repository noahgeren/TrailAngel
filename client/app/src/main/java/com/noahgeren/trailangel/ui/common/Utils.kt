package com.noahgeren.trailangel.ui.common

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.content.SharedPreferences
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView

object Utils {

    fun setupSubmitActionListener(input: EditText, action: Int, btn: Button) {
        input.setOnEditorActionListener { _, i, _ ->
            if(i == action) {
                btn.performClick()
            }
            false
        }
    }

    fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager = activity.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        var view = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
        view.clearFocus()
    }

    fun setBasicTextObserver(textViewMap: Map<LiveData<String>, TextView>, viewLifecycleOwner: LifecycleOwner) {
        textViewMap.forEach {
            it.key.observe(viewLifecycleOwner, { value -> it.value.text = value })
        }
    }

    fun showAlert(context: Context, title: String, message: String?,
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

    fun <T : RecyclerView.ViewHolder> T.onClick(event: (itemView: View, position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(it, adapterPosition, itemViewType)
        }
        return this
    }
}