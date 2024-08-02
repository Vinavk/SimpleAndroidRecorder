package com.example.jpanimated.UIScreens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jpanimated.AndroidAudioPlayer
import com.example.jpanimated.AndroidAudioRecorder
import com.example.jpanimated.Screens.Screens
import com.example.jpanimated.ViewModel.AudioViewModel
import java.io.File

@Composable
fun NavigationComponent(
    player: AndroidAudioPlayer,
    recorder: AndroidAudioRecorder,
    audiofile: File,
    viewmoel: AudioViewModel
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screens.HomeScreen.str) {
        composable(Screens.HomeScreen.str) {
            AudioScreen(player = player, recorder =recorder , audiofile =audiofile , viewmoel =viewmoel , navcontrol = navController)
        }
        composable(Screens.Recordings.str) {
            Recordings(viewmoel,recorder,player)
        }
    }
}