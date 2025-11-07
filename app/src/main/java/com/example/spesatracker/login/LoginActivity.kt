package com.example.spesatracker.login

import android.content.Intent
import android.graphics.LinearGradient
import android.graphics.Shader
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.example.spesatracker.R
import com.example.spesatracker.dashboard.DashboardActivity
import com.example.spesatracker.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        mediaPlayer = MediaPlayer.create(this, R.raw.textpop)
        animateTitle(getString(R.string.appname))
        btnclick()
    }

    private fun btnclick() {

        binding.forgotpassword.setOnClickListener {
            val intent = Intent(this, ForgotActivity::class.java)
            startActivity(intent)
        }
        binding.loginbtn.setOnClickListener {
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

    }

    private fun animateTitle(text: String) {
        val popInAnimation = AnimationUtils.loadAnimation(this, R.anim.pop_in)
        val handler = Handler(Looper.getMainLooper())
        val titleContainer = binding.titleContainer

        titleContainer.removeAllViews()

        text.forEachIndexed { index, char ->
            val charTextView = TextView(this).apply {
                setText(char.toString())
                textSize = 32f
                setTextColor(
                    ContextCompat.getColor(
                        context,
                        android.R.color.white
                    )
                )
            }

            titleContainer.addView(charTextView)

            handler.postDelayed({
                charTextView.startAnimation(popInAnimation)
                playSound()
                handler.postDelayed({
                    applyMoneyGradient(charTextView)
                }, 200)

            }, (index * 120).toLong())
        }
    }

    private fun applyMoneyGradient(textView: TextView) {
        val textPaint = textView.paint
        val textWidth = textPaint.measureText(textView.text.toString())
        val moneyColors = intArrayOf(
            ContextCompat.getColor(this, R.color.money_green_light),
            ContextCompat.getColor(this, R.color.money_green_dark),
            ContextCompat.getColor(this, R.color.money_green_mid)
        )
        val moneyShader: Shader = LinearGradient(
            0f, 0f, textWidth, textView.textSize,
            moneyColors,
            null,
            Shader.TileMode.CLAMP
        )
        textView.paint.shader = moneyShader
        textView.invalidate()
    }

    private fun playSound() {
        mediaPlayer?.let {
            if (it.isPlaying) {
                it.seekTo(0)
            }
            it.start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}
