package com.bharat.weatherapp.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.WindowManager
import com.bharat.weatherapp.R
import java.lang.IllegalArgumentException

class DialogProgress(val context: Context) {
    val dialog = Dialog(context)

    fun showProgress(){
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_progress)
        val window = dialog.window
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCanceledOnTouchOutside(false)

        val lp = WindowManager.LayoutParams()
        if (dialog.window != null) {
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            dialog.window!!.attributes = lp
        }

        dialog.show()
    }

    fun cancelProgress(){
        try {
            dialog.cancel()
        }catch (e: IllegalArgumentException){}
    }

}