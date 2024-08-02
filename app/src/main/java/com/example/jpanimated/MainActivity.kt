package com.example.jpanimated

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.example.jpanimated.Room.AudioDataBases
import com.example.jpanimated.UIScreens.NavigationComponent
import com.example.jpanimated.ViewModel.AudioViewModel
import com.example.jpanimated.ui.theme.JpAnimatedTheme
import java.io.File

class MainActivity : ComponentActivity() {
    private lateinit var player: AndroidAudioPlayer
    private lateinit var recorder: AudioRecorder
    private lateinit var db: AudioDataBases
    private lateinit var viewmoel: AudioViewModel
    private var audiofile: File? = null

    //pop us to save with name, name migration in dao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        player = AndroidAudioPlayer(applicationContext)
        recorder = AndroidAudioRecorder(applicationContext)
        db = AudioDataBases.getinstances(applicationContext)
        viewmoel = AudioViewModel(db)

        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.RECORD_AUDIO), 0)

        setContent {
            JpAnimatedTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    audiofile = File(cacheDir, "audiomp3")

                    NavigationComponent(player, recorder as AndroidAudioRecorder,
                        audiofile!!,viewmoel)
                }
            }
        }
    }
}


/*

@Composable
fun AudioScreen(
    player: AndroidAudioPlayer,
    recorder: AudioRecorder,
    audiofile: File?,
    viewmoel: AudioViewModel,
    navcontrol: NavHostController
) {
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
                            audiofile?.let {
                                viewmoel.saveaudiofiles(it)
                            }
                        }
                    ) {
                        Text(text = "Save", fontSize = 18.sp)
                    }
                }
            }
        }
    }
}*/
