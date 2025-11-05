package com.example.spesatracker.login

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent.ACTION_DOWN
import android.view.KeyEvent.KEYCODE_DEL
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.spesatracker.databinding.ActivityForgotOtpVerifyBinding

class ForgotOtpVerifyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotOtpVerifyBinding
    private val otpFields = mutableListOf<EditText>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotOtpVerifyBinding.inflate(layoutInflater)
        setContentView(binding.root)
        otpFields.add(binding.otpEditText1)
        otpFields.add(binding.otpEditText2)
        otpFields.add(binding.otpEditText3)
        otpFields.add(binding.otpEditText4)
        setupOtpListeners()

    }

    private fun setupOtpListeners() {
        for (i in otpFields.indices) {
            // 1. TextWatcher for automatically moving focus forward
            otpFields[i].addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    if (!s.isNullOrEmpty() && i < otpFields.size - 1) {
                        otpFields[i + 1].requestFocus()
                    }
                }

                override fun afterTextChanged(s: Editable?) {}
            })

            otpFields[i].setOnKeyListener { _, keyCode, event ->
                if (keyCode == KEYCODE_DEL && event.action == ACTION_DOWN) {
                    if (i > 0 && otpFields[i].text.isEmpty()) {
                        otpFields[i - 1].requestFocus()
                        otpFields[i - 1].setText("")
                        return@setOnKeyListener true
                    }
                }
                return@setOnKeyListener false
            }
        }
    }}