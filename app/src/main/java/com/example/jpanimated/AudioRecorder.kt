package com.example.jpanimated

import java.io.File

interface AudioRecorder {
    fun start(outputfile : File)

    fun stop()
}