package com.example.spesatracker.dashboard

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.spesatracker.R

class ChartFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backgroundColor = getBackgroundColor(view)
        setStatusBarColor(backgroundColor)

    }

    private fun getBackgroundColor(view: View): Int {
        // Get the background color of the view
        val background = view.background
        return if (background != null && background is ColorDrawable) {
            background.color
        } else {
            Color.TRANSPARENT // Default to transparent if no background is set
        }
    }

    private fun setStatusBarColor(color: Int) {
        // Set the status bar color
        activity?.window?.statusBarColor = color

        // Adjust status bar icons based on color brightness
        if (isColorDark(color)) {
            activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        } else {
            activity?.window?.decorView?.systemUiVisibility = 0
        }
    }

    private fun isColorDark(color: Int): Boolean {
        val darkness = 1 - (0.299 * Color.red(color) +
                0.587 * Color.green(color) +
                0.114 * Color.blue(color)) / 255
        return darkness >= 0.5
    }


}