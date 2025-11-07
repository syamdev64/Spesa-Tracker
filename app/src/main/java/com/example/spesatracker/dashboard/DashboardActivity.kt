package com.example.spesatracker.dashboard

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.spesatracker.R
import com.example.spesatracker.base.BaseActivity
import com.example.spesatracker.databinding.DashboardactivityBinding
import com.example.spesatracker.sound.SoundManager
import com.example.spesatracker.utility.Utility
import com.qamar.curvedbottomnaviagtion.CurvedBottomNavigation


class DashboardActivity : BaseActivity() {
    private lateinit var binding: DashboardactivityBinding
    private lateinit var soundManager: SoundManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.dashboardactivity)
        Utility.setStatusBarColor(this, getBackgroundColor())

        soundManager = SoundManager.getInstance(this)
        soundManager.loadSound(R.raw.button_click)
        bottomnavigation()
        replaceFragment(HomeFragment())
    }

    private fun bottomnavigation() {
        binding.bottomNavigation.add(CurvedBottomNavigation.Model(1, "Home", R.drawable.home_icon))
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(
                2, "Chart", R.drawable.graph_icon
            )
        )
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(
                3, "Notes", R.drawable.notes_icon
            )
        )
        binding.bottomNavigation.add(
            CurvedBottomNavigation.Model(
                4, "Profile", R.drawable.profile_icon
            )
        )
        binding.bottomNavigation.show(1)
        binding.bottomNavigation.setOnClickMenuListener {
            when (it.id) {
                1 -> {
                    replaceFragment(HomeFragment())
                    soundManager.playSound(R.raw.button_click)
                }

                2 -> {
                    replaceFragment(ChartFragment())
                    soundManager.playSound(R.raw.button_click)
                }

                3 -> {
                    replaceFragment(NotesFragment())
                    soundManager.playSound(R.raw.button_click)
                }

                4 -> {
                    replaceFragment(AccountFragment())
                    soundManager.playSound(R.raw.button_click)

                }
            }

        }

    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        soundManager.release()
    }
}