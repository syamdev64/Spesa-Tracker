package com.example.spesatracker.sound

import android.annotation.SuppressLint
import android.content.Context
import android.media.AudioAttributes
import android.media.SoundPool
import android.util.Log

class SoundManager private constructor(private val context: Context) {
    private val soundPool: SoundPool
    private val sounds: MutableMap<Int, Int> = mutableMapOf()

    // Keep track of loaded sounds
    private val loadedSounds: MutableSet<Int> = mutableSetOf()

    init {
        val audioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_MEDIA)
            .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
            .build()

        soundPool = SoundPool.Builder()
            .setMaxStreams(1)
            .setAudioAttributes(audioAttributes)
            .build()

        // **THIS IS THE KEY FIX**
        // Set a listener that will be called when a sound has finished loading.
        soundPool.setOnLoadCompleteListener { _, sampleId, status ->
            if (status == 0) {
                // status 0 means success
                loadedSounds.add(sampleId)
                Log.d("SoundManager", "Sound loaded successfully: $sampleId")
            } else {
                Log.e("SoundManager", "Failed to load sound, status: $status")
            }
        }
    }

    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var INSTANCE: SoundManager? = null

        fun getInstance(context: Context): SoundManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: SoundManager(context).also { INSTANCE = it }
            }
        }
    }

    fun loadSound(soundResId: Int) {
        val soundId = soundPool.load(context, soundResId, 1)
        sounds[soundResId] = soundId
    }

    fun playSound(soundResId: Int) {
        // Get the sound ID from the resource ID
        val soundId = sounds[soundResId]

        // Check if the sound ID exists and is in the set of loaded sounds
        if (soundId != null && loadedSounds.contains(soundId)) {
            soundPool.play(soundId, 1f, 1f, 0, 0, 1f)
        } else {
            Log.w("SoundManager", "Sound not loaded yet for resource ID: $soundResId")
        }
    }

    fun release() {
        soundPool.release()
        INSTANCE = null // Clear the instance
    }
}
