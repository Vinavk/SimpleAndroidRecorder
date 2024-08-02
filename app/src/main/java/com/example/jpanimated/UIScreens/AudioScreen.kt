package com.example.jpanimated.UIScreens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.example.jpanimated.AndroidAudioPlayer
import com.example.jpanimated.AudioRecorder
import com.example.jpanimated.R
import com.example.jpanimated.Screens.Screens
import com.example.jpanimated.ViewModel.AudioViewModel
import com.example.jpanimated.ui.theme.green_color
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AudioScreen(
    player: AndroidAudioPlayer,
    recorder: AudioRecorder,
    audiofile: File?,
    viewmoel: AudioViewModel,
    navcontrol: NavHostController
) {
    val context = LocalContext.current
    var showDialog by remember {
        mutableStateOf(false)
    }
    Box(
        modifier = Modifier
            .background(color = green_color)
            .fillMaxSize()
            .padding(2.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = {
                    audiofile?.let {
                        recorder.start(it)
                        Toast.makeText(context, "Recording started", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.record),
                        contentDescription = "Record"
                    )
                }
                IconButton(onClick = {
                    audiofile?.let {
                        recorder.stop()
                    }
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.stoprecord),
                        contentDescription = "Stop Record"
                    )
                }
                IconButton(onClick = {
                    audiofile?.let {
                        player.playfile(it)
                    }
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.playbutton),
                        contentDescription = "Play"
                    )
                }
                IconButton(onClick = {
                    player.stop()
                }) {
                    Image(
                        painter = painterResource(id = R.drawable.stop),
                        contentDescription = "Stop"
                    )
                }

                Spacer(modifier = Modifier.padding(30.dp))
                Box(modifier = Modifier.padding(10.dp)) {
                    Button(
                        modifier = Modifier
                            .height(50.dp)
                            .width(100.dp),
                        onClick = {
                           showDialog = true

                        }
                    ) {
                        Text(text = "Save", fontSize = 18.sp)
                    }
                }
            }
            Spacer(modifier = Modifier.padding(30.dp))
            Box(modifier = Modifier.padding(10.dp)) {
                Button(
                    modifier = Modifier
                        .height(50.dp)
                        .width(100.dp),
                    onClick = {
                        navcontrol.navigate(Screens.Recordings.str)
                    }
                ) {
                    Text(text = "recordings", fontSize = 16.sp)
                }
            }

        }
    }

    if(showDialog){

        var name by remember {
            mutableStateOf("")
        }
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text(text = audiofile?.isAbsolute.toString()) },
            text = {
                OutlinedTextField(value = name, onValueChange = {
                    name = it
                })
            },
            confirmButton = {
                TextButton(onClick = {
                    showDialog = false
                    audiofile?.let {
                        viewmoel.saveaudiofiles(it,name)
                    }
                    Toast.makeText(context, "Saved", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "OK")
                }
            })


    }


}