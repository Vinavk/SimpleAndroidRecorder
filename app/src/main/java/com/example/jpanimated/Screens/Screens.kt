package com.example.jpanimated.Screens

sealed class Screens(var str : String) {

    data object HomeScreen :Screens("HomeScreen")
    data object Recordings :Screens("Recordings")

}