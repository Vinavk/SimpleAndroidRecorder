package com.example.jpanimated

import android.content.Context
import android.media.MediaRecorder
import android.os.Build
import kotlinx.coroutines.CoroutineScope
import java.io.File
import java.io.FileOutputStream
class AndroidAudioRecorder(private val context: Context) : AudioRecorder {

    private var recorder: MediaRecorder? = null

    private fun createRecorder(): MediaRecorder {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            MediaRecorder(context)
        } else {
            MediaRecorder()
        }
    }

    override fun start(outputFile: File) {
        recorder = createRecorder().apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(FileOutputStream(outputFile).fd)

            try {
                prepare()
                start()
            } catch (e: Exception) {
                e.printStackTrace()
                releaseRecorder()
            }
        }
    }

    override fun stop() {
        recorder?.let {
            try {
                it.stop()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                it.reset()
                releaseRecorder()
            }
        }
    }

    private fun releaseRecorder() {
        recorder?.release()
        recorder = null
    }
}