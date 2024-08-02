package com.example.jpanimated

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class AndroidAudioPlayer(private val context: Context) : AudioPlayer {
    private var player: MediaPlayer? = null
    override fun playfile(file: File) {
        MediaPlayer.create(context, file.toUri()).apply {
            player = this
            player!!.start()
        }
    }

    override fun stop() {
        player?.stop()
    }
}