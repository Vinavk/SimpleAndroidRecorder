package com.example.jpanimated.UIScreens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.livedata.observeAsState


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.jpanimated.AndroidAudioPlayer
import com.example.jpanimated.AndroidAudioRecorder
import com.example.jpanimated.R
import com.example.jpanimated.Room.AudioNote

import com.example.jpanimated.ViewModel.AudioViewModel
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


@Composable
fun Recordings(
    viewmoel: AudioViewModel,
    recorder: AndroidAudioRecorder,
    player: AndroidAudioPlayer
) {


    val list by viewmoel.mutableAudioNotes.observeAsState()

    Box(modifier = Modifier.fillMaxSize()) {

        list?.let {

            LazyColumn(content = {

                itemsIndexed(it) { index, item ->

                    ItemViews(item, recorder, player, viewmoel)

                }

            })

        }

    }

}

lateinit var audioFile: File

@Composable
fun ItemViews(
    item: AudioNote,
    recorder: AndroidAudioRecorder,
    player: AndroidAudioPlayer,
    viewmoel: AudioViewModel
) {
    val context = LocalContext.current

    LaunchedEffect(item.file) {
        audioFile = convertByteArrayToAudio(context, item.file)
    }

    OutlinedCard(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.padding(10.dp))

        /*  IconButton(onClick = {
              audioFile?.let {
                  recorder.start(it)
              }
          }) {
              Image(
                  painter = painterResource(id = R.drawable.record),
                  contentDescription = "Record"
              )
          }
          IconButton(onClick = {
              audioFile?.let {
                  recorder.stop()
              }
          }) {
              Image(
                  painter = painterResource(id = R.drawable.stoprecord),
                  contentDescription = "Stop Record"
              )
          }*/

        Row(
            modifier = Modifier.padding(start = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {


            IconButton(onClick = {
                audioFile?.let {
                    player.playfile(it)
                }
            }) {
                Image(
                    painter = painterResource(id = R.drawable.playbutton),
                    contentDescription = "Play"
                )
            }

            Spacer(modifier = Modifier.padding(start = 20.dp))
            IconButton(onClick = {
                player.stop()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.stop),
                    contentDescription = "Stop"
                )
            }

            Spacer(modifier = Modifier.padding(10.dp))
            Text(text = item.name)

            IconButton(onClick = {
                viewmoel.deletedata(item)


            }) {

                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete")

            }
        }
        Spacer(modifier = Modifier.padding(10.dp))

    }
}


private fun convertByteArrayToAudio(context: Context, byteArray: ByteArray): File {
    val fileName = "audio_${System.currentTimeMillis()}.mp3"
    val directory = File(context.filesDir, "audio")
    if (!directory.exists()) {
        directory.mkdirs()
    }

    val outputFile = File(directory, fileName)
    try {
        FileOutputStream(outputFile).use { fos ->
            fos.write(byteArray)
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return outputFile
}


