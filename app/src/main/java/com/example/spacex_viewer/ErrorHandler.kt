package com.example.spacex_viewer

import android.content.Context
import android.widget.Toast

class ErrorHandler(ctx: Context, errText: String) {
    private val errText: String = errText
    private val ctx: Context = ctx

    fun showError() {
        Toast.makeText(ctx, errText, Toast.LENGTH_LONG).show()
    }
}