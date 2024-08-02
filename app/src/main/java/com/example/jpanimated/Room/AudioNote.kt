package com.example.jpanimated.Room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "AudioNotes")
data class AudioNote(

    @PrimaryKey(autoGenerate = true)
    val id: Int= 0,
    val name: String,

    var file: ByteArray
)
