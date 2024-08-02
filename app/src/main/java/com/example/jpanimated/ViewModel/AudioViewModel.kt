package com.example.jpanimated.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.jpanimated.Room.AudioDataBases
import com.example.jpanimated.Room.AudioNote
import kotlinx.coroutines.launch
import java.io.File

class AudioViewModel(var db: AudioDataBases) : ViewModel() {


    fun saveaudiofiles(file : File,name: String){

        viewModelScope.launch {
            var ByteArray = audioFileToByteArray(file)
            var audionote = AudioNote(file = ByteArray!!, name = name)
            db.getdao().insertdata(audionote)
        }
    }

    fun deletedata(file: AudioNote){
        viewModelScope.launch {
            db.getdao().deletedata(file)
        }
    }

    fun audioFileToByteArray(file: File): ByteArray? {
        return file.inputStream().use { it.readBytes() }
    }

    val audioNotes: LiveData<List<AudioNote>> = db.getdao().getAudioNotes()


    private val _mutableAudioNotes = MutableLiveData<List<AudioNote>>()
    val mutableAudioNotes: LiveData<List<AudioNote>> get() = _mutableAudioNotes

    init {

        audioNotes.observeForever { notes ->
            _mutableAudioNotes.postValue(notes)
        }
    }


}