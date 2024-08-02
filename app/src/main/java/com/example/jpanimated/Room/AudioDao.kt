package com.example.jpanimated.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AudioDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     suspend fun insertdata(data : AudioNote)

    @Delete
   suspend fun deletedata(data : AudioNote)

    @Query("SELECT * FROM AudioNotes")
   fun getAudioNotes() : LiveData<List<AudioNote>>

}