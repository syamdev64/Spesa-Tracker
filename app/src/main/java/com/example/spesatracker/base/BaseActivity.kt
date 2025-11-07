package com.example.spesatracker.base

import android.R
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 1. Enable edge-to-edge display (your theme also does this, but being explicit is safe)
        WindowCompat.setDecorFitsSystemWindows(window, false)

        // **THE FIX**: Forcefully set the status bar to be transparent and use light appearance.
        // This command runs after the theme is applied and acts as a final override.
        window.statusBarColor = Color.TRANSPARENT // Force transparency
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.isAppearanceLightStatusBars = true // Force dark icons

        // This is the padding logic, which is still necessary.
        val contentView: View = findViewById(android.R.id.content)
        ViewCompat.setOnApplyWindowInsetsListener(contentView) { view, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(
                left = insets.left,
                top = insets.top,
                right = insets.right,
                bottom = insets.bottom
            )
            WindowInsetsCompat.CONSUMED
        }

    }

    protected fun getBackgroundColor(): Int {
        val rootView = window.decorView.findViewById<View>(R.id.content)
        val background = rootView.background
        return if (background is ColorDrawable) {
            background.color
        } else {
            Color.TRANSPARENT
        }
    }
}