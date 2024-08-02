package com.example.jpanimated

import java.io.File

interface AudioPlayer {

    fun playfile(file : File)
    fun stop()
}