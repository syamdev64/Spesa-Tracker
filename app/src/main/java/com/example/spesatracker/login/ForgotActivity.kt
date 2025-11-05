package com.example.spesatracker.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.spesatracker.databinding.ActivityForgotBinding

class ForgotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityForgotBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForgotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sendbtn()
    }

    private fun sendbtn() {
        binding.otpsendbtn.setOnClickListener(
            {
                val intent = Intent(this, ForgotOtpVerifyActivity::class.java)
                startActivity(intent)
            }
        )
    }
}